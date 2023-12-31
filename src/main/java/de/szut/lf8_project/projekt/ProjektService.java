package de.szut.lf8_project.projekt;

import de.szut.lf8_project.projekt.entity.ProjektEntity;
import org.springframework.stereotype.Service;

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
        ProjektEntity updatedProjekt = readById(id);

        updatedProjekt.setProjektId(id);
        try {
            if(!projekt.getDescription().isEmpty()) {
                updatedProjekt.setDescription(projekt.getDescription());
            }
        }
        catch (NullPointerException ignored){}
        try {
            if(projekt.getResponsableEmployeeId()!=0) {
                updatedProjekt.setResponsableEmployeeId(projekt.getResponsableEmployeeId());
            }
        }
        catch (NullPointerException ignored){}
        try {
            if(projekt.getCustomerEmployeeId()!=0) {
                updatedProjekt.setCustomerEmployeeId(projekt.getCustomerEmployeeId());
            }
        }
        catch (NullPointerException ignored){}
        try {
            if(!projekt.getEmployees().isEmpty()) {
                updatedProjekt.getEmployees().clear();
                for (Long employeesId : projekt.getEmployees()) {
                    updatedProjekt.getEmployees().add(employeesId);
                }
            }
        }
        catch (NullPointerException ignored){}
        try {
            if(!projekt.getComment().isEmpty()) {
                updatedProjekt.setComment(projekt.getComment());
            }
        }
        catch (NullPointerException ignored){}
        try {
            if(!projekt.getComment().isEmpty()) {
                updatedProjekt.setEndDate_planned(projekt.getEndDate_planned());
            }
        }
        catch (NullPointerException ignored){}
        try {
            if(!projekt.getComment().isEmpty()) {
                updatedProjekt.setEndDate_actual(projekt.getEndDate_actual());
            }
        }
        catch (NullPointerException ignored){}
        return this.repository.save(updatedProjekt);
    }
}
