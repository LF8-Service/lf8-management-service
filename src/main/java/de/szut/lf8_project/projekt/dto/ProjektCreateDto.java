package de.szut.lf8_project.projekt.dto;

import de.szut.lf8_project.coworker.CoworkerEntity;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProjektCreateDto {

    @NotBlank(message = "Description is mandatory")
    @Size(max = 100, message = "Description must not exceed 100 characters")
    private String description;

    @NotNull
    private CoworkerEntity responsableCoworker;

    private List<CoworkerEntity> coworkers;
    @Max(value = 10000, message = "Customer ID must not exceed 10 characters")
    private long customerId;

    @NotNull
    private CoworkerEntity customerCoworker;

    private String comment;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate_planned;
    private LocalDate endDate_actual;

}
