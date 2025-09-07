package com.practiceProject.hospitalManagement.hospitalManagementApplication.service;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Insurance;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Patient;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.repository.InsuranceRepository;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("patient not found with id: " + patientId));
        patient.setInsurance(insurance);
        insurance.setPatient(patient); // bidirectional consistency maintainenance

        return patient;
    }

    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        patient.setInsurance(null);
        return patient;
    }
}
