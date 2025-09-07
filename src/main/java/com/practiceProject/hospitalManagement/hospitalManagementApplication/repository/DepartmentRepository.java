package com.practiceProject.hospitalManagement.hospitalManagementApplication.repository;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}