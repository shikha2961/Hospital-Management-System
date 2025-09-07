package com.practiceProject.hospitalManagement.hospitalManagementApplication.contoller;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.PatientDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientById(id));
    }

}
