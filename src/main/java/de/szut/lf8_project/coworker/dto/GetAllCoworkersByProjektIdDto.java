package de.szut.lf8_project.coworker.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class GetAllCoworkersByProjektIdDto {
    private long projektId;
    private String description;
    private List<CoworkerGetDto> allCoworkers;
}
