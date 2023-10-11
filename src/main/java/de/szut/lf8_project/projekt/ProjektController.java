package de.szut.lf8_project.projekt;

import com.github.dockerjava.api.exception.BadRequestException;
import de.szut.lf8_project.coworker.CoworkerEntity;
import de.szut.lf8_project.coworker.CoworkerRole;
import de.szut.lf8_project.coworker.dto.GetAllCoworkersByProjektIdDto;
import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;
import de.szut.lf8_project.projekt.dto.ProjektCreateDto;
import de.szut.lf8_project.projekt.dto.ProjektGetDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/service/projekt")

public class ProjektController {
    private final ProjektService service;
    private final ProjektMapper projektMapper;

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
            @ApiResponse(responseCode = "404", description = "Head Coworker or Customer were not found",
                    content = @Content)})
    @PostMapping
    public ProjektGetDto create(@RequestBody @Valid ProjektCreateDto projektCreateDto) {
        ProjektEntity projektEntity = this.projektMapper.mapProjektCreateDtoToProjekt(projektCreateDto);
        checkCoworkersQualificationInOneProject(projektEntity.getProjektId());
        projektEntity = this.service.create(projektEntity);
        return this.projektMapper.mapProjektToProjektGetDto(projektEntity);
    }

    public void checkCoworkersQualificationInOneProject(long projectId){
        var entity = this.service.readById(projectId);
        if(!entity.getResponsableCoworker().getQualification().equals(CoworkerRole.projekt_head.toString())){
            throw new BadRequestException("Project head doesn't have this qualification");
        }
        if(!entity.getCustomerCoworker().getQualification().equals(CoworkerRole.customer_coworker.toString())){
            throw new BadRequestException("Customer Coworker doesn't have this qualification");
        }

        for(CoworkerEntity coworker:entity.getCoworkers()){
            if(!coworker.getQualification().equals(CoworkerRole.developer.toString())){
                throw new BadRequestException("Developer doesn't have this qualification");
            }
        }
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

    @Operation(summary = "find projekt by description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of projekts who have the given description",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjektGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "qualification description does not exist",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/findByDescription")
    public List<ProjektGetDto> findAllEmployeesByQualification(@RequestParam String description) {
        return this.service
                .findByDescription(description)
                .stream()
                .map(e -> this.projektMapper.mapProjektToProjektGetDto(e))
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}/coworker")
    public ResponseEntity<GetAllCoworkersByProjektIdDto> getAllCoworkers(@PathVariable final Long id){
        final var entity = this.service.readById(id);
        final GetAllCoworkersByProjektIdDto dto = this.projektMapper.mapProjektToAllCoworkersByProjektIdDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
