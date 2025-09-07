package com.practiceProject.hospitalManagement.hospitalManagementApplication.repository;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}