package com.practiceProject.hospitalManagement.hospitalManagementApplication.contoller;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.AppointmentResponseDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.DoctorResponseDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.User;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.service.AppointmentService;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsForDoctor(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(appointmentService.getAppointmentsByUserId(user.getId()));
    }
}
