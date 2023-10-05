package de.szut.lf8_project.coworker;

import de.szut.lf8_project.projekt.ProjektEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoworkerService {

    private final CoworkerRepository repository;

    public CoworkerService(CoworkerRepository repository) {
        this.repository = repository;
    }

    public CoworkerEntity create(CoworkerEntity entity) {
        return this.repository.save(entity);
    }

    public List<CoworkerEntity> readAll() {
        return this.repository.findAll();
    }
}
