package de.szut.lf8_project.coworker;

import de.szut.lf8_project.projekt.ProjektEntity;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "coworker")
public class CoworkerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coworkerId;
    private String name;
    private String surname;
    private int age;
    @ManyToMany (fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL,
            mappedBy = "coworker")
    private List<ProjektEntity> projektList;
    private String qualifikation ;
}
