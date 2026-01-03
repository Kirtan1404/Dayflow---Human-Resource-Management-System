package com.Entity;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    String location;
    String role ;

    String department;
    String designation;

    LocalDate joinDate;
    String profileImage;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    List<AttendanceEntity> attendanceList;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    List<LeaveRequestEntity> leaveRequests;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    List<DocumentEntity> documents;

    @OneToOne(mappedBy = "employee")
    PayrollEntity payroll;
    
    @ElementCollection
    private List<String> skills;

    @ElementCollection
    private List<String> achievements;

    // ✅ THIS WAS MISSING
//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    List<SkillEntity> skills;
//
//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    List<AchievementEntity> achievements;
}
