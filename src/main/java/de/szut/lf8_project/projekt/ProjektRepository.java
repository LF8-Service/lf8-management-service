package de.szut.lf8_project.projekt;


import de.szut.lf8_project.projekt.entity.ProjektEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjektRepository extends JpaRepository<ProjektEntity, Long> {
    List<ProjektEntity> findByDescription(String description);
}
