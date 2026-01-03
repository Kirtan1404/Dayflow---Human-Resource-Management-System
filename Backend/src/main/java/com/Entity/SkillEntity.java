package com.Entity;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SkillEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String skillName;        // Java, Spring Boot, React
    String proficiency;      // Beginner, Intermediate, Expert

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    EmployeeEntity employee;
}
