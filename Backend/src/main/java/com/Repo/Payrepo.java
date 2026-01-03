package com.Repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.EmployeeEntity;
import com.Entity.PayrollEntity;
import java.util.List;
import java.util.Optional;


public interface Payrepo extends JpaRepository<PayrollEntity, UUID> {

	Optional<PayrollEntity> findByEmployee(EmployeeEntity employee);
	
}
