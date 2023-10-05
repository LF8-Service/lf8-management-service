package de.szut.lf8_project.coworker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CoworkerGetDto {
    private int coworkerId;
    private String name;
    private String surname;
    private long age;
    private String qualification;
}
