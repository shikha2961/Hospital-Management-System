package com.practiceProject.hospitalManagement.hospitalManagementApplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Normalized;

@Data
@ToString
public class DoctorResponseDto {
    private Long id;
    @NotBlank(message = "name is required")
    private String name;
    private String specialization;
    @Email
    private String email;
}
