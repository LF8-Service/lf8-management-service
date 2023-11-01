package de.szut.lf8_project.employee;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="employeesId")
public class EmployeesId {
    @Id
    long id;
}
