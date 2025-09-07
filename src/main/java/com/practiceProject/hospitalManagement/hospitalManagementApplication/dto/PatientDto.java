package com.practiceProject.hospitalManagement.hospitalManagementApplication.dto;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.type.BloodGroupType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class PatientDto {
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @Email
    private String email;

    @ToString.Exclude
    private LocalDate birthDate;

    private String gender;

    private BloodGroupType bloodGroup;

}
