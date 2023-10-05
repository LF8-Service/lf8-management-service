package de.szut.lf8_project.coworker;

import de.szut.lf8_project.coworker.dto.CoworkerCreateDto;
import de.szut.lf8_project.coworker.dto.CoworkerGetDto;

public class CoworkerMapper {
    public CoworkerGetDto mapToGetDto(CoworkerEntity entity) {
        return new CoworkerGetDto(entity.getCoworkerId(), entity.getName(), entity.getSurname(), entity.getAge(),
                entity.getQualifikation());
    }

    public CoworkerEntity mapCreateDtoToEntity(CoworkerCreateDto dto) {
        var entity = new CoworkerEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setQualifikation(dto.getQualification());

        return entity;
    }

}
