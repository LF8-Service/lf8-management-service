package de.szut.lf8_project.coworker;

import com.github.dockerjava.api.exception.BadRequestException;
import de.szut.lf8_project.coworker.dto.CoworkerCreateDto;
import de.szut.lf8_project.coworker.dto.CoworkerGetDto;
import de.szut.lf8_project.hello.dto.HelloGetDto;
import de.szut.lf8_project.projekt.dto.ProjektGetDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/service/coworker")
public class CoworkerController {
    private final CoworkerService service;
    private final CoworkerMapper mapper;

    public CoworkerController(CoworkerService service, CoworkerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "creates a new coworker with its id and message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created hello",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CoworkerGetDto.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PostMapping
    public CoworkerGetDto create(@RequestBody @Valid CoworkerCreateDto coworkerCreateDto) {
        CoworkerEntity coworker = this.mapper.mapCreateDtoToEntity(coworkerCreateDto);
        if(!isCoworkerQualificationValid(coworker.getQualification())){
            throw new BadRequestException("The qualification is not valid");
        }
        coworker = this.service.create(coworker);
        return this.mapper.mapToGetDto(coworker);
    }
    public boolean isCoworkerQualificationValid(String qualification){
        return qualification.equals(CoworkerRole.customer_coworker.toString())||
                qualification.equals(CoworkerRole.projekt_head.toString())||
                qualification.equals(CoworkerRole.developer.toString());
    }
    @Operation(summary = "delivers a list of projekts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of projekts",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CoworkerGetDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping
    public List<CoworkerGetDto> findAll() {
        return this.service
                .readAll()
                .stream()
                .map(e -> this.mapper.mapToGetDto(e))
                .collect(Collectors.toList());
    }
}
