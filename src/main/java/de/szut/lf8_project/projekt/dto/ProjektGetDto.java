package de.szut.lf8_project.projekt.dto;

import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.EmployeesId;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProjektGetDto {
    private long projektId;

    private String description;

    private EmployeesId responsableEmployee;

    private List<EmployeesId> employees = new ArrayList<>();

    private long customerId;

    private EmployeesId customerEmployee;

    private String comment;

    private LocalDate startDate;

    private LocalDate endDate_planned;

    private LocalDate endDate_actual;


}

