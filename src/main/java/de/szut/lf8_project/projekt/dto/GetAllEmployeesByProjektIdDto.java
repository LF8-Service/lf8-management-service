package de.szut.lf8_project.projekt.dto;

import de.szut.lf8_project.employee.EmployeeEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class GetAllEmployeesByProjektIdDto {
    long projektId;
    List<EmployeeEntity> employees = new ArrayList<>();
}
