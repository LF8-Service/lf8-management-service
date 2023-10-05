package de.szut.lf8_project.projekt;

import de.szut.lf8_project.coworker.CoworkerEntity;
import de.szut.lf8_project.coworker.dto.CoworkerGetDto;
import de.szut.lf8_project.coworker.dto.GetAllCoworkersByProjektIdDto;
import de.szut.lf8_project.projekt.dto.ProjektCreateDto;
import de.szut.lf8_project.projekt.dto.ProjektGetDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjektMapper {

    public ProjektGetDto mapProjektToProjektGetDto(ProjektEntity entity) {
        ProjektGetDto newDto = new ProjektGetDto();
        newDto.setProjektId(entity.getProjektId());
        newDto.setDescription(entity.getDescription());
        newDto.setCustomerId(entity.getCustomerId());

        CoworkerEntity responsableCoworker = new CoworkerEntity();
        responsableCoworker.setCoworkerId(entity.getResponsableCoworker().getCoworkerId());
        responsableCoworker.setName(entity.getResponsableCoworker().getName());
        responsableCoworker.setSurname(entity.getResponsableCoworker().getSurname());
        responsableCoworker.setAge(entity.getResponsableCoworker().getAge());

        newDto.setResponsableCoworker(responsableCoworker);

        CoworkerEntity customerCoworker = new CoworkerEntity();
        customerCoworker.setCoworkerId(entity.getResponsableCoworker().getCoworkerId());
        customerCoworker.setName(entity.getResponsableCoworker().getName());
        customerCoworker.setSurname(entity.getResponsableCoworker().getSurname());
        customerCoworker.setAge(entity.getResponsableCoworker().getAge());

        newDto.setCustomerCoworker(entity.getCustomerCoworker());

        newDto.setComment(entity.getComment());
        newDto.setStartDate(entity.getStartDate());
        newDto.setEndDate_planned(entity.getEndDate_planned());
        newDto.setEndDate_actual(entity.getEndDate_actual());
        return newDto;
    }

    public ProjektEntity mapProjektCreateDtoToProjekt(ProjektCreateDto dto) {
        ProjektEntity newProjekt = new ProjektEntity();
        newProjekt.setDescription(dto.getDescription());
        newProjekt.setCustomerId(dto.getCustomerId());

        CoworkerEntity responsableCoworker = new CoworkerEntity();
        responsableCoworker.setCoworkerId(dto.getResponsableCoworker().getCoworkerId());
        responsableCoworker.setName(dto.getResponsableCoworker().getName());
        responsableCoworker.setSurname(dto.getResponsableCoworker().getSurname());
        responsableCoworker.setAge(dto.getResponsableCoworker().getAge());

        newProjekt.setResponsableCoworker(responsableCoworker);

        CoworkerEntity customerCoworker = new CoworkerEntity();
        customerCoworker.setCoworkerId(dto.getResponsableCoworker().getCoworkerId());
        customerCoworker.setName(dto.getResponsableCoworker().getName());
        customerCoworker.setSurname(dto.getResponsableCoworker().getSurname());
        customerCoworker.setAge(dto.getResponsableCoworker().getAge());

        newProjekt.setCustomerCoworker(dto.getCustomerCoworker());

        newProjekt.setCustomerCoworker(dto.getCustomerCoworker());
        newProjekt.setComment(dto.getComment());
        newProjekt.setStartDate(dto.getStartDate());
        newProjekt.setEndDate_planned(dto.getEndDate_planned());
        newProjekt.setEndDate_actual(dto.getEndDate_actual());
        return newProjekt;
    }

    public GetAllCoworkersByProjektIdDto mapProjektToAllCoworkersByProjektIdDto(ProjektEntity projekt){
        GetAllCoworkersByProjektIdDto dto = new GetAllCoworkersByProjektIdDto();
        dto.setDescription(projekt.getDescription());
        List<CoworkerGetDto> allCoworkers = new ArrayList<>();
        for (CoworkerEntity coworker: projekt.getCoworkers()) {
            CoworkerGetDto cDto = new CoworkerGetDto(coworker.getCoworkerId(), coworker.getName(), coworker.getSurname(),
                    coworker.getAge(), coworker.getQualifikation());
            cDto.setCoworkerId(coworker.getCoworkerId());
            cDto.setName(coworker.getName());
            cDto.setSurname(coworker.getSurname());
            cDto.setAge(coworker.getAge());
            allCoworkers.add(cDto);
        }
        dto.setAllCoworkers(allCoworkers);
        return dto;
    }
}
