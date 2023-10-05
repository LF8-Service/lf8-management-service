package de.szut.lf8_project.coworker.dto;

import lombok.Data;

import javax.validation.constraints.Max;
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
    @Max(value = 3, message = "Age must not exceed 3 characters")
    private long age;
    @NotBlank(message = "Qualification is mandatory")
    @Size(max = 30, message = "Qualification must not exceed 30 characters")
    private String qualification;
}
