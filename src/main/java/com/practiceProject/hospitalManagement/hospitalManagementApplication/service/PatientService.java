package com.practiceProject.hospitalManagement.hospitalManagementApplication.service;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.PatientDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Patient;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<PatientDto> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patient -> modelMapper.map(patient, PatientDto.class)).toList();
    }

    @Transactional
    public PatientDto getPatientById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow();
        return modelMapper.map(patient, PatientDto.class);
    }

    @Transactional
    public void removePatientById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow();
        patientRepository.delete(patient);
    }
}
