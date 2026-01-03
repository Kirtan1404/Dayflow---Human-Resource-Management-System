package com.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Entity.PayrollEntity;
import com.Repo.Employeerepo;
import com.Repo.Payrepo;
import com.Entity.EmployeeEntity;
//import com.Repository.PayrollRepository;
//import com.Repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final Payrepo payrollRepository;
    private final Employeerepo employeeRepository;

    // 1️⃣ Create / Update Payroll (Admin / HR)
    @PostMapping("/save")
    public ResponseEntity<PayrollEntity> savePayroll(
            @RequestParam UUID employeeId,
            @RequestBody PayrollEntity request) {

        EmployeeEntity employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Calculate net salary
        double basic = request.getBasicSalary() != null ? request.getBasicSalary() : 0;
        double allowance = request.getAllowances() != null ? request.getAllowances() : 0;
        double deduction = request.getDeductions() != null ? request.getDeductions() : 0;

        request.setNetSalary(basic + allowance - deduction);
        request.setEmployee(employee);

        PayrollEntity saved = payrollRepository.save(request);
        return ResponseEntity.ok(saved);
    }

 // 2️⃣ Get Payroll by Employee ID (Employee / Admin)
    @GetMapping("/employeefind")
    public ResponseEntity<PayrollEntity> getPayrollByEmployee(
            @RequestParam UUID employeeId) {

        EmployeeEntity employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        PayrollEntity payroll = payrollRepository.findByEmployee(employee)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));

        return ResponseEntity.ok(payroll);
    }

    // 3️⃣ Get All Payrolls (Admin / HR)
    @GetMapping("/all")
    public ResponseEntity<List<PayrollEntity>> getAllPayrolls() {
        return ResponseEntity.ok(payrollRepository.findAll());
    }
}
