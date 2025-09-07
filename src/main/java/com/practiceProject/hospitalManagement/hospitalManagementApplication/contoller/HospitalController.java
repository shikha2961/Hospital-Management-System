package com.practiceProject.hospitalManagement.hospitalManagementApplication.contoller;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.DoctorResponseDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/public")
public class HospitalController {
    private final DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
}
