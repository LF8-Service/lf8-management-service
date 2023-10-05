package de.szut.lf8_project.projekt.dto;

import de.szut.lf8_project.coworker.CoworkerEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProjektCreateDto {

    @NotBlank(message = "Description is mandatory")
    @Size(max = 100, message = "Description must not exceed 100 characters")
    private String description;

    @NotBlank(message = "Responsable Coworker is mandatory")
    @Size(max = 50, message = "Responsable Coworker must not exceed 50 characters")
    private CoworkerEntity responsableCoworker;

    private List<CoworkerEntity> coworkers;
    @NotBlank(message = "Customer ID is mandatory")
    @Size(max = 10, message = "Customer ID must not exceed 10 characters")
    private int customerId;

    @NotBlank(message = "Customer Coworker is mandatory")
    @Size(max = 50, message = "Customer Coworker must not exceed 50 characters")
    private CoworkerEntity customerCoworker;

    private String comment;
    @NotBlank(message = "Start Date is mandatory")
    @Size(max = 10, message = "Start Date must not exceed 10 characters")
    private LocalDate startDate;
    @NotBlank(message = "Planned End Date is mandatory")
    @Size(max = 10, message = "Planned End Date must not exceed 10 characters")
    private LocalDate endDate_planned;
    private LocalDate endDate_actual;

}
