package com.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeaveRequestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String leaveType; // PAID, SICK, UNPAID

    String startDate;
    String  endDate;

    String reason;

    String status; // PENDING, APPROVED, REJECTED

    String adminComment;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    EmployeeEntity employee;
}
