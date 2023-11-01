package de.szut.lf8_project.employee;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lastName;
    private String firstName;
    private String street;
    private String postcode;
    private String city;
    private String phone;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SkillSet> skillSet = new ArrayList<>();
}
