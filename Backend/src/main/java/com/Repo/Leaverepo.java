package com.Repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.EmployeeEntity;
import com.Entity.LeaveRequestEntity;
import java.util.List;


public interface Leaverepo extends JpaRepository<LeaveRequestEntity, UUID> {

	List<LeaveRequestEntity> findByEmployee(EmployeeEntity employee);
	
	
}
