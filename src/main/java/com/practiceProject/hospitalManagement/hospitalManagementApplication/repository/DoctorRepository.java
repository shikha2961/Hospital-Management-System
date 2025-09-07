package com.practiceProject.hospitalManagement.hospitalManagementApplication.repository;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}