package de.szut.lf8_project.coworker;

import de.szut.lf8_project.coworker.dto.CoworkerCreateDto;
import de.szut.lf8_project.coworker.dto.CoworkerGetDto;
import de.szut.lf8_project.projekt.ProjektEntity;
import de.szut.lf8_project.projekt.dto.ProjektGetDto;
import org.springframework.stereotype.Service;

@Service
public class CoworkerMapper {
    public CoworkerGetDto mapToGetDto(CoworkerEntity entity) {
        CoworkerGetDto coworkerGetDto = new CoworkerGetDto();
        coworkerGetDto.setCoworkerId(entity.getCoworkerId());
        coworkerGetDto.setName(entity.getName());
        coworkerGetDto.setSurname(entity.getSurname());
        coworkerGetDto.setAge(entity.getAge());
        coworkerGetDto.setQualification(entity.getQualification());

        return coworkerGetDto;
    }

    public CoworkerEntity mapCreateDtoToEntity(CoworkerCreateDto dto) {
        var entity = new CoworkerEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setQualification(dto.getQualification());

        return entity;
    }
}
