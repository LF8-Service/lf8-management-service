package de.szut.lf8_project.projekt.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "projekt")
public class ProjektEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projektId;

    private String description;

    private long responsableEmployeeId;
    @ElementCollection
    private List<Long> employees = new ArrayList<>();

    private long customerId;

    private long customerEmployeeId;

    private String comment;

    private LocalDate startDate;

    private LocalDate endDate_planned;

    private LocalDate endDate_actual;

}

