package de.szut.lf8_project.coworker.dto;

import lombok.Data;

@Data
public class CoworkerGetDto {
    private int coworkerId;
    private String name;
    private String surname;
    private int age;
}
