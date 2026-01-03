package com.Entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(unique = true, nullable = false)
    String email;

    String password;

    @Column(nullable = false)
    String role; // EMPLOYEE / ADMIN

    Boolean isActive = true;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

//    @OneToOne(mappedBy = "user")
//    EmployeeEntity employee;
}
