package de.szut.lf8_project.customer.dto;

import lombok.Data;

@Data
public class CustomerGetDto {
    private int customerId;
    private String name;
    private String surname;
    private int age;
}
