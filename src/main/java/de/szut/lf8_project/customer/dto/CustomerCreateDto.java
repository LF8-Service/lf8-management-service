package de.szut.lf8_project.customer.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerCreateDto {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 30, message = "Name must not exceed 30 characters")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    @Size(max = 30, message = "Surname must not exceed 30 characters")
    private String surname;
    @NotNull
    @Max(value = 100, message = "Age must not exceed 3 characters")
    private long age;
}
