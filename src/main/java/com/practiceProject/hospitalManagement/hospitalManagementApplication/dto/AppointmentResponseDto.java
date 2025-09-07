package com.practiceProject.hospitalManagement.hospitalManagementApplication.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class AppointmentResponseDto {
    private Long id;
    private LocalDateTime appointmentTime;
    private DoctorResponseDto doctor;

}
