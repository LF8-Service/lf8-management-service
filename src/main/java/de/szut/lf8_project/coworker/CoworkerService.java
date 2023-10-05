package de.szut.lf8_project.coworker;

public class CoworkerService {

    private final CoworkerRepository repository;

    public CoworkerService(CoworkerRepository repository) {
        this.repository = repository;
    }

    public CoworkerEntity create(CoworkerEntity entity) {
        return this.repository.save(entity);
    }
}
