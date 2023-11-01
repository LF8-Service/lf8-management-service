package de.szut.lf8_project.projekt.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProjektUpdateDto {

        private String description;

        private Long responsableEmployeeId;
        private Long customerEmployeeId;

        private List<Long> employees = new ArrayList<>();

        private String comment;

        private LocalDate endDate_planned;

        private LocalDate endDate_actual;

}
