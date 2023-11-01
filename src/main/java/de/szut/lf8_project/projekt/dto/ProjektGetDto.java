package de.szut.lf8_project.projekt.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProjektGetDto {
    private long projektId;

    private String description;

    private Long responsableEmployeeId;

    private List<Long> employees = new ArrayList<>();

    private long customerId;

    private Long customerEmployeeId;

    private String comment;

    private LocalDate startDate;

    private LocalDate endDate_planned;

    private LocalDate endDate_actual;


}

