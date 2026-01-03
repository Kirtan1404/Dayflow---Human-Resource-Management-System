package com.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.DTO.LeaveActionRequest;
import com.Entity.EmployeeEntity;
import com.Entity.LeaveRequestEntity;
import com.Repo.Employeerepo;
//import com.Repo.LeaveRequestRepo;
import com.Repo.Leaverepo;

@RestController
@RequestMapping("/leaves")
@CrossOrigin("*")
public class LeaveController {

    @Autowired
    private Leaverepo leaveRequestRepo;

    @Autowired
    private Employeerepo employeeRepo;

    // ----------------------------------
    // APPLY LEAVE (Employee)
    // ----------------------------------
    @PostMapping("/apply")
    public ResponseEntity<?> applyLeave(

            @RequestParam UUID employeeId,
            @RequestBody LeaveRequestEntity leaveRequest
    ) {

        EmployeeEntity employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Force backend values (security)
       
        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus("PENDING");
//        leaveRequest.setId(null); // avoid override

        leaveRequestRepo.save(leaveRequest);

        return ResponseEntity.ok("Leave request submitted successfully");
    }

    // ----------------------------------
    // VIEW MY LEAVES
    // ----------------------------------
    @GetMapping("/my-leaves")
    public ResponseEntity<?> myLeaves(
            @RequestParam UUID employeeId) {
    	
    	Optional<EmployeeEntity> op  = employeeRepo.findById(employeeId);
    	
    	if(op.isEmpty())
    	{
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found id");
    	}
    	

        return ResponseEntity.ok(
        		op.get().getLeaveRequests() 
        );
    }


    // ----------------------------------
    // VIEW ALL LEAVES (Admin / HR)
    // ----------------------------------
    @GetMapping("/all")
    public ResponseEntity<List<LeaveRequestEntity>> allLeaves() {
        return ResponseEntity.ok(leaveRequestRepo.findAll());
    }

    // ----------------------------------
    // APPROVE / REJECT (Admin)
    // ----------------------------------
    @PostMapping("/action")
    public ResponseEntity<String> approveReject(@RequestBody LeaveActionRequest request) {

//    	UUID id = UUID.fromString(request.getLeaveId());
    	System.out.println("leaeiidd-->"+request.getLeaveId());
        LeaveRequestEntity leave = leaveRequestRepo.findById(request.getLeaveId())
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        // Admin-controlled fields only
        leave.setStatus(request.getStatus()); // APPROVED or REJECTED
        leave.setAdminComment(request.getAdminComment());

        leaveRequestRepo.save(leave);

        return ResponseEntity.ok("Leave " + request.getStatus() + " successfully");
    }

}
