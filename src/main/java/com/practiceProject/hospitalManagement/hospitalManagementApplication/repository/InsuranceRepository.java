package com.practiceProject.hospitalManagement.hospitalManagementApplication.repository;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}