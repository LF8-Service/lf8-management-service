package de.szut.lf8_project.projekt;

import de.szut.lf8_project.employee.EmployeeController;
import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.EmployeesId;
import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;
import de.szut.lf8_project.projekt.dto.ProjektCreateDto;
import de.szut.lf8_project.projekt.dto.ProjektGetDto;
import de.szut.lf8_project.projekt.dto.ProjektUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/service/projekt")

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
    @PostMapping
    public ProjektGetDto create(@RequestBody @Valid ProjektCreateDto projektCreateDto) {
        ProjektEntity projektEntity = this.projektMapper.mapProjektCreateDtoToProjekt(projektCreateDto);
        //   if(checkEmployeesIdInOneProjekt(projektEntity)) {
        projektEntity = this.service.create(projektEntity);
        return this.projektMapper.mapProjektToProjektGetDto(projektEntity);
        // }
        //else {
        //  throw new BadRequestException("Employees don't exist");
        //}
    }

    private boolean checkEmployeesIdInOneProjekt(ProjektEntity projekt) {
        List<EmployeeEntity> employees = employeeController.getAllEmployees();
        boolean isResponsableEmployeeExist =  employeeController.checkEmployeeIdInList(projekt.getResponsableEmployee().getId(), employees);
        if (!isResponsableEmployeeExist) {
            return false;
        }
        boolean isCustomerEmployeeExist = employeeController.checkEmployeeIdInList(projekt.getCustomerEmployee().getId(), employees);
        if (!isCustomerEmployeeExist) {
            return false;
        }
        for (EmployeesId employeesId : projekt.getEmployees()) {
            boolean isIdExist = employeeController.checkEmployeeIdInList(employeesId.getId(), employees);
            if (!isIdExist) {
                return false;
            }
        }
        return true;
    }



    @Operation(summary = "delivers a list of projekts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of projekts",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjektGetDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping
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
    @GetMapping("/{id}")
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
    @DeleteMapping("/{id}")
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
                    content = @Content)})
    @PostMapping("/{id}")
    public ProjektGetDto updateProjekt(@RequestBody @Valid ProjektUpdateDto projektUpdateDto, @PathVariable long id) {
        ProjektEntity projektEntity = this.projektMapper.mapProjektUpdateDtoToProjekt(projektUpdateDto,id);
        //   if(checkEmployeesIdInOneProjekt(projektEntity)) {
        projektEntity = this.service.update(projektEntity,id);
        return this.projektMapper.mapProjektToProjektGetDto(projektEntity);
        // }
        //else {
        //  throw new BadRequestException("Employees don't exist");
        //}
    }

}
