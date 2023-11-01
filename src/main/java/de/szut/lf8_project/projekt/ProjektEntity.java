package de.szut.lf8_project.projekt;

import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.EmployeesId;
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
    @ManyToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL)
    private EmployeesId responsableEmployee;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL)
    private List<EmployeesId> employees = new ArrayList<>();

    private long customerId;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL)
    private EmployeesId customerEmployee;

    private String comment;

    private LocalDate startDate;

    private LocalDate endDate_planned;

    private LocalDate endDate_actual;

}

