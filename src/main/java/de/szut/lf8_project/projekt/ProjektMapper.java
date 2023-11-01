package de.szut.lf8_project.projekt;

import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.EmployeesId;


import de.szut.lf8_project.projekt.dto.GetAllEmployeesByProjektIdDto;
import de.szut.lf8_project.projekt.dto.ProjektCreateDto;
import de.szut.lf8_project.projekt.dto.ProjektGetDto;
import de.szut.lf8_project.projekt.dto.ProjektUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjektMapper {

    public ProjektGetDto mapProjektToProjektGetDto(ProjektEntity entity) {
        ProjektGetDto newDto = new ProjektGetDto();
        newDto.setProjektId(entity.getProjektId());
        newDto.setDescription(entity.getDescription());
        newDto.setCustomerId(entity.getCustomerId());

        EmployeesId responsableEmployee = new EmployeesId();
        responsableEmployee.setId(entity.getResponsableEmployee().getId());
        newDto.setResponsableEmployee(responsableEmployee);

        EmployeesId customerEmployee = new EmployeesId();
        customerEmployee.setId(entity.getCustomerEmployee().getId());
        newDto.setCustomerEmployee(customerEmployee);

        for (int i=0;i<entity.getEmployees().toArray().length;i++) {
            EmployeesId employeesId = new EmployeesId();
            employeesId.setId(entity.getEmployees().get(i).getId());
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

        EmployeesId responsableEmployee = new EmployeesId();
        responsableEmployee.setId(dto.getResponsableEmployee().getId());
        newProjekt.setResponsableEmployee(responsableEmployee);

        EmployeesId customerEmployee = new EmployeesId();
        customerEmployee.setId(dto.getCustomerEmployee().getId());
        newProjekt.setCustomerEmployee(customerEmployee);

        for (int i=0;i<dto.getEmployees().toArray().length;i++) {
            EmployeesId employeesId = new EmployeesId();
            employeesId.setId(dto.getEmployees().get(i).getId());
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
        newProjekt.setDescription(dto.getDescription());

        EmployeesId responsableEmployee = new EmployeesId();
        responsableEmployee.setId(dto.getResponsableEmployee().getId());
        newProjekt.setResponsableEmployee(responsableEmployee);

        EmployeesId customerEmployee = new EmployeesId();
        customerEmployee.setId(dto.getCustomerEmployee().getId());
        newProjekt.setCustomerEmployee(customerEmployee);

        for (int i=0;i<dto.getEmployees().toArray().length;i++) {
            EmployeesId employeesId = new EmployeesId();
            employeesId.setId(dto.getEmployees().get(i).getId());
            newProjekt.getEmployees().add(employeesId);
        }
        newProjekt.setComment(dto.getComment());
        newProjekt.setEndDate_planned(dto.getEndDate_planned());
        newProjekt.setEndDate_actual(dto.getEndDate_actual());
        return newProjekt;
    }
}
