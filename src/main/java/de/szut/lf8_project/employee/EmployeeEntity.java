package de.szut.lf8_project.employee;

import de.szut.lf8_project.qualification.Qualification;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String lastName;
    String firstName;
    String Street;
    String postcode;
    String city;
    String phone;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL)
    List<Qualification> skillSet = new ArrayList<>();
}
