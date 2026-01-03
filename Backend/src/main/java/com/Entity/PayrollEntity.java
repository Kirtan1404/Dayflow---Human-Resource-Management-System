package com.Entity;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PayrollEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    Double basicSalary;
    Double allowances;
    Double deductions;
    Double netSalary;

    String month;
    Integer year;

    @OneToOne
    @JoinColumn(name = "employee_id")
    EmployeeEntity employee;
}
