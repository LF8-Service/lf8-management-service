package de.szut.lf8_project.projekt;

import de.szut.lf8_project.coworker.CoworkerEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "projekt")
public class ProjektEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projektId;

    private String description;

    @OneToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL)
    private CoworkerEntity responsableCoworker;

    @OneToMany(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL)
    private List<CoworkerEntity> coworkers;

    private int customerId;

    @OneToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL)
    private CoworkerEntity customerCoworker;

    private String comment;

    private LocalDate startDate;

    private LocalDate endDate_planned;

    private LocalDate endDate_actual;

}

