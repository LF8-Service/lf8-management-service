package de.szut.lf8_project.projekt;

import com.github.dockerjava.api.exception.BadRequestException;
import de.szut.lf8_project.employee.EmployeeController;
import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;
import de.szut.lf8_project.projekt.dto.ProjektCreateDto;
import de.szut.lf8_project.projekt.dto.ProjektGetDto;
import de.szut.lf8_project.projekt.dto.ProjektUpdateDto;
import de.szut.lf8_project.projekt.entity.ProjektEntity;
import de.szut.lf8_project.projekt.entity.ProjektsEmployees;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/service")

public class ProjektController {
    private final ProjektService service;
    private final ProjektMapper projektMapper;
    private final EmployeeController employeeController = new EmployeeController();

    public ProjektController(ProjektService service, ProjektMapper mappingService) {
        this.service = service;
        this.projektMapper = mappingService;
    }

    @Operation(summary = "creates a new projekt with its parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created projekt",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjektGetDto.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Head employee or Customer employee were not found",
                    content = @Content)})
    @PostMapping("/projekt")
    public ProjektGetDto create(@RequestBody @Valid ProjektCreateDto projektCreateDto) {
        ProjektEntity projektEntity = this.projektMapper.mapProjektCreateDtoToProjekt(projektCreateDto);
        if (employeeController.checkEmployeesIdInOneProjekt(projektEntity)) {
            projektEntity = this.service.create(projektEntity);
            return this.projektMapper.mapProjektToProjektGetDto(projektEntity);
        } else {
            throw new BadRequestException("Employees don't exist");
        }
    }

    @Operation(summary = "delivers a list of projekts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of projekts",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjektGetDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/projekt")
    public List<ProjektGetDto> findAll() {
        return this.service
                .readAll()
                .stream()
                .map(e -> this.projektMapper.mapProjektToProjektGetDto(e))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a projekt by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "project was received",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjektGetDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @GetMapping("/projekt/{id}")
    public ProjektGetDto getProjektById(@PathVariable long id) {
        var entity = this.service.readById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("ProjektEntity not found on id = " + id);
        } else {
            return this.projektMapper.mapProjektToProjektGetDto(entity);
        }
    }

    @Operation(summary = "deletes a projekt by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "delete successful"),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @DeleteMapping("/projekt/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteProjektById(@PathVariable long id) {
        var entity = this.service.readById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("ProjektEntity not found on id = " + id);
        } else {
            this.service.delete(entity);
        }
    }

    @Operation(summary = "Update a projekt by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "project was received",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjektGetDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Employees don't exist",
                    content = @Content)})
    @PostMapping("/projekt/{id}")
    public ProjektGetDto updateProjekt(@RequestBody @Valid ProjektUpdateDto projektUpdateDto, @PathVariable long id) {
        ProjektEntity projektEntity = this.projektMapper.mapProjektUpdateDtoToProjekt(projektUpdateDto, id);
        System.out.println(projektUpdateDto.getDescription());
        System.out.println(projektEntity.getDescription());
        ProjektEntity updatedProjekt  = this.service.update(projektEntity, id);
         if (employeeController.checkEmployeesIdInOneProjekt(updatedProjekt)) {
            return this.projektMapper.mapProjektToProjektGetDto(projektEntity);
        } else {
            throw new BadRequestException("Employees don't exist");
        }
    }


    public List<ProjektsEmployees> getEmployeesInProjekts() {
        List<ProjektGetDto> projektsDto = findAll();
        List<ProjektsEmployees> projektsEmployeesList = new ArrayList<>();
        for (ProjektGetDto projektGetDto : projektsDto) {

            ProjektsEmployees projektsEmployees = new ProjektsEmployees();
            projektsEmployees.setProjektId(projektGetDto.getProjektId());

            projektsEmployees.setEmployeeIds(new ArrayList<>());

            projektsEmployees.getEmployeeIds().add(projektGetDto.getResponsableEmployeeId());
            projektsEmployees.getEmployeeIds().add(projektGetDto.getCustomerEmployeeId());

            for (long employeeId = 0; employeeId < projektGetDto.getEmployees().toArray().length; employeeId++) {
                boolean isTheSameId = Objects.equals(employeeId, projektGetDto.getResponsableEmployeeId()) || Objects.equals(employeeId, projektGetDto.getCustomerEmployeeId());
                if (isTheSameId) {
                    employeeId++;
                } else {
                    projektsEmployees.getEmployeeIds().add(projektGetDto.getEmployees().get((int) employeeId));
                }
            }
            projektsEmployeesList.add(projektsEmployees);
        }
        return projektsEmployeesList;
    }

    @Operation(summary = "Find all employee projekts by employee's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "projects was received",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjektGetDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @GetMapping("/employee/{employeeId}")
    public List<Long> findEmployeeInProjekts(@PathVariable long employeeId) {
        List<Long> projektsId = new ArrayList<>();
        List<ProjektsEmployees> projektsEmployees = getEmployeesInProjekts();
        for (int i = 0; i < projektsEmployees.toArray().length; i++) {
            for (int k = 0; k < projektsEmployees.get(i).getEmployeeIds().toArray().length; k++) {
                boolean isEmployeeInProjekt = projektsEmployees.get(i).getEmployeeIds().get(k).equals(employeeId);
                if (isEmployeeInProjekt) {
                    projektsId.add(projektsEmployees.get(i).getProjektId());
                    break;
                }
            }
        }
        return projektsId;
    }

}
