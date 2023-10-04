package de.szut.lf8_project.customer;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String name;
    private String surname;
    private int age;
}
