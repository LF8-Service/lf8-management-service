package de.szut.lf8_project.projekt.dto;

import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.EmployeesId;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProjektUpdateDto {

        private String description;

        private EmployeesId responsableEmployee;
        private EmployeesId customerEmployee;

        private List<EmployeesId> employees = new ArrayList<>();

        private String comment;

        private LocalDate endDate_planned;

        private LocalDate endDate_actual;

}
