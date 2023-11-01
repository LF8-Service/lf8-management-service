package de.szut.lf8_project.projekt;

import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.EmployeesId;
import de.szut.lf8_project.hello.HelloEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjektService {
    private final ProjektRepository repository;

    public ProjektService(ProjektRepository repository) {
        this.repository = repository;
    }

    public ProjektEntity create(ProjektEntity entity) {
        return this.repository.save(entity);
    }

    public List<ProjektEntity> readAll() {
        return this.repository.findAll();
    }

    public ProjektEntity readById(long id) {
        Optional<ProjektEntity> optionalQualification = this.repository.findById(id);
        if (optionalQualification.isEmpty()) {
            return null;
        }
        return optionalQualification.get();
    }


    public void delete(ProjektEntity entity) {
        this.repository.delete(entity);
    }
    public ProjektEntity update(ProjektEntity projekt, long id){
        ProjektEntity updatedProjekt = readById(projekt.getProjektId());

        updatedProjekt.setProjektId(id);
        updatedProjekt.setDescription(projekt.getDescription());

        EmployeesId responsableEmployee = new EmployeesId();
        responsableEmployee.setId(projekt.getResponsableEmployee().getId());
        updatedProjekt.setResponsableEmployee(responsableEmployee);

        EmployeesId customerEmployee = new EmployeesId();
        customerEmployee.setId(projekt.getCustomerEmployee().getId());
        updatedProjekt.setCustomerEmployee(responsableEmployee);

        updatedProjekt.getEmployees().clear();
        List<EmployeesId> newEmployee = projekt.getEmployees();
        for(EmployeesId employeesId: newEmployee){
            updatedProjekt.getEmployees().add(employeesId);
        }

        updatedProjekt.setComment(projekt.getComment());
        updatedProjekt.setStartDate(projekt.getStartDate());
        updatedProjekt.setEndDate_planned(projekt.getEndDate_planned());
        updatedProjekt.setEndDate_actual(projekt.getEndDate_actual());
        return this.repository.save(updatedProjekt);
    }
}
