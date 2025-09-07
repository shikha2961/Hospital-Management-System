package com.practiceProject.hospitalManagement.hospitalManagementApplication.repository;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
