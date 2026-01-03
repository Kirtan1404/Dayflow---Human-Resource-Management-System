package com.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendanceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    LocalDate date;

    LocalDateTime checkIn;
    LocalDateTime checkOut;

    String status; // PRESENT, ABSENT, HALF_DAY, LEAVE

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    EmployeeEntity employee;
}
