package de.szut.lf8_project.qualification;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="qualification")
public class Qualification {
    @Id
    String skill;
}
