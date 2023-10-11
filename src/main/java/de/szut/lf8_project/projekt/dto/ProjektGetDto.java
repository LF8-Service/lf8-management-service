package de.szut.lf8_project.projekt.dto;

import de.szut.lf8_project.coworker.CoworkerEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProjektGetDto {
    private long projektId;

    private String description;

    private CoworkerEntity responsableCoworker;

    private List<CoworkerEntity> coworkers;

    private long customerId;

    private CoworkerEntity customerCoworker;

    private String comment;

    private LocalDate startDate;

    private LocalDate endDate_planned;

    private LocalDate endDate_actual;


}

