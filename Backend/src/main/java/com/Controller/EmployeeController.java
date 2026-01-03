package com.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//import com.Dto.EmployeeDetailsRequest;
import com.Entity.EmployeeEntity;
import com.Repo.Employeerepo;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private Employeerepo employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    // ----------------------------------
    // SIGNUP
    // ----------------------------------
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody EmployeeEntity em) {

        if (employeeRepository.findByEmail(em.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmail(em.getEmail());
        employee.setPassword(passwordEncoder.encode(em.getPassword()));
        employee.setRole(em.getRole());
        employee.setJoinDate(LocalDate.now());
        employee.setProfileImage(null);

        employeeRepository.save(employee);
        return ResponseEntity.ok(employee);
    }

    // ----------------------------------
    // LOGIN
    // ----------------------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody EmployeeEntity em) {

        EmployeeEntity employee = employeeRepository.findByEmail(em.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(em.getPassword(), employee.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }

        return ResponseEntity.ok(employee);
    }

    // ----------------------------------
    // GET PROFILE
    // ----------------------------------
    @GetMapping("/me")
    public ResponseEntity<EmployeeEntity> getProfile(
            @RequestParam UUID employeeId) {

        return ResponseEntity.ok(
                employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new RuntimeException("Employee not found"))
        );
    }

    // ----------------------------------
    // ADD / UPDATE DETAILS
    // employeeId → RequestParam
    // details → RequestBody
    // ----------------------------------
    @PostMapping("/add-details")
    public ResponseEntity<EmployeeEntity> addDetails(

            @RequestParam UUID employeeId,
            @RequestBody EmployeeEntity request
    ) {

        EmployeeEntity employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setPhone(request.getPhone());
        employee.setLocation(request.getLocation());
        employee.setDesignation(request.getDesignation());
        employee.setDepartment(request.getDepartment());
        employee.setSkills(request.getSkills());
        employee.setAchievements(request.getAchievements());

        employeeRepository.save(employee);
        return ResponseEntity.ok(employee);
    }

    // ----------------------------------
    // UPLOAD PROFILE PHOTO (SEPARATE API)
    // ----------------------------------
    @PostMapping("/upload-photo")
    public ResponseEntity<?> uploadPhoto(
            @RequestParam UUID employeeId,
            @RequestParam MultipartFile photo) throws IOException {

        EmployeeEntity employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Map uploadResult = cloudinary.uploader().upload(
                photo.getBytes(),
                ObjectUtils.asMap("folder", "dayflow/profile-photos")
        );

        employee.setProfileImage(uploadResult.get("secure_url").toString());
        employeeRepository.save(employee);

        return ResponseEntity.ok("Profile photo uploaded successfully");
    }
}
