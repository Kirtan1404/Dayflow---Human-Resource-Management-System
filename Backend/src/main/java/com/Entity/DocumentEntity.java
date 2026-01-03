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
public class DocumentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String documentType; // Aadhar, Resume, Offer Letter
    String filePath;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    EmployeeEntity employee;
}
