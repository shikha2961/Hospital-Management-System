package com.practiceProject.hospitalManagement.hospitalManagementApplication.contoller;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.DoctorRequestDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.DoctorResponseDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.PatientDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.service.DoctorService;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.service.PatientService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final DoctorService doctorService;
    private final PatientService patientService;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PostMapping("/onBoardDoctor")
    public ResponseEntity<DoctorResponseDto> onBoardNewDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.onBoardNewDoctor(doctorRequestDto));
    }

}
