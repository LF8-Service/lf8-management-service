package de.szut.lf8_project.projekt.entity;

import lombok.Data;

import java.util.List;

@Data
public class ProjektsEmployees {
    private long projektId;
    private List<Long> employeeIds;
}
