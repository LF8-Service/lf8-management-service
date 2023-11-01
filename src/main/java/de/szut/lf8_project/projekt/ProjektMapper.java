package de.szut.lf8_project.projekt;


import de.szut.lf8_project.projekt.dto.ProjektCreateDto;
import de.szut.lf8_project.projekt.dto.ProjektGetDto;
import de.szut.lf8_project.projekt.dto.ProjektUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class ProjektMapper {

    public ProjektGetDto mapProjektToProjektGetDto(ProjektEntity entity) {
        ProjektGetDto newDto = new ProjektGetDto();
        newDto.setProjektId(entity.getProjektId());
        newDto.setDescription(entity.getDescription());
        newDto.setCustomerId(entity.getCustomerId());
        newDto.setResponsableEmployeeId(entity.getResponsableEmployeeId());
        newDto.setCustomerEmployeeId(entity.getCustomerEmployeeId());
        for (Long employeesId:entity.getEmployees()) {
            newDto.getEmployees().add(employeesId);
        }
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
        newProjekt.setResponsableEmployeeId(dto.getResponsableEmployeeId());
        newProjekt.setCustomerEmployeeId(dto.getCustomerEmployeeId());
        for (Long employeesId:dto.getEmployees()) {
            newProjekt.getEmployees().add(employeesId);
        }
        newProjekt.setComment(dto.getComment());
        newProjekt.setStartDate(dto.getStartDate());
        newProjekt.setEndDate_planned(dto.getEndDate_planned());
        newProjekt.setEndDate_actual(dto.getEndDate_actual());
        return newProjekt;
    }
    public ProjektEntity mapProjektUpdateDtoToProjekt(ProjektUpdateDto dto, long id) {
        ProjektEntity newProjekt = new ProjektEntity();

        newProjekt.setProjektId(id);
        try {
            newProjekt.setDescription(dto.getDescription());
        }
        catch (NullPointerException ignored){}
        try {
            newProjekt.setResponsableEmployeeId(dto.getResponsableEmployeeId());
        }
        catch (NullPointerException ignored){}
        try {
            newProjekt.setCustomerEmployeeId(dto.getCustomerEmployeeId());
        }
        catch (NullPointerException ignored){}
       try {
           for (Long employeesId:dto.getEmployees()) {
               newProjekt.getEmployees().add(employeesId);
           }
        }
        catch (NullPointerException ignored){}
        try {
            newProjekt.setComment(dto.getComment());
        }
        catch (NullPointerException ignored){}
        try {
            newProjekt.setEndDate_planned(dto.getEndDate_planned());
        }
        catch (NullPointerException ignored){}
        try {
            newProjekt.setEndDate_actual(dto.getEndDate_actual());
        }
        catch (NullPointerException ignored){}
        return newProjekt;
    }
}
