package de.szut.lf8_project.coworker.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CoworkerCreateDto {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 30, message = "Name must not exceed 30 characters")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    @Size(max = 30, message = "Surname must not exceed 30 characters")
    private String surname;
    @NotBlank(message = "Age is mandatory")
    @Size(max = 3, message = "Age must not exceed 3 characters")
    private int age;
}
