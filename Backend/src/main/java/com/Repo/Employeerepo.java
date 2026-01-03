package com.Repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.EmployeeEntity;
import java.util.List;
import java.util.Optional;


public interface Employeerepo extends JpaRepository<EmployeeEntity, UUID> {

	Optional<EmployeeEntity> findByEmail(String email);

}
