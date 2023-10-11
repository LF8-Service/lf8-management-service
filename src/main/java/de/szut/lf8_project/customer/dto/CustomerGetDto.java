package de.szut.lf8_project.customer.dto;

import lombok.Data;

@Data
public class CustomerGetDto {
    private long customerId;
    private String name;
    private String surname;
    private long age;
}
