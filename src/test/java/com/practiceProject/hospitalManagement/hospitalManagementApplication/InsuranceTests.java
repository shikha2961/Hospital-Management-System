package com.practiceProject.hospitalManagement.hospitalManagementApplication;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Appointment;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Insurance;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Patient;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.service.AppointmentService;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTests {
    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testInsurance(){
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2027, 12, 31))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(patient);

        var newPatient = insuranceService.disaccociateInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }

    @Test
    public void testCreateAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 11, 01, 14, 00))
                .reason("fever")
                .build();

        Appointment appointment1 = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 12, 02, 13, 30))
                .reason("fever")
                .build();

        Appointment appointment2 = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 10, 02, 15, 00))
                .reason("fever")
                .build();

        appointmentService.createNewAppointment(appointment, 1L, 1L);
        appointmentService.createNewAppointment(appointment1, 1L, 1L);
        appointmentService.createNewAppointment(appointment2, 1L, 1L);

//        System.out.println(newAppointment);

        var updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(1L, 3L);

        System.out.println(updatedAppointment);
    }
}
