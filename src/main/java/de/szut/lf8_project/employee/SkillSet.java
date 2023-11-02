package de.szut.lf8_project.employee;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Für neue Employee Version
 */
@Data
@Entity
public class SkillSet {
    private String skill;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
