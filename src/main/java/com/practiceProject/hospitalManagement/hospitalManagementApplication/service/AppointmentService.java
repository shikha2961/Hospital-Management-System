package com.practiceProject.hospitalManagement.hospitalManagementApplication.service;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.AppointmentResponseDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Appointment;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Doctor;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Patient;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.repository.AppointmentRepository;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.repository.DoctorRepository;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null){
            throw new IllegalArgumentException("Appointment is already there with this id");
        }

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        // To maintain Consistency
        patient.getAppointments().add(appointment);

        appointmentRepository.save(appointment);

        return appointment;
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);

        // To maintain bidirectional consistency
        doctor.getAppointments().add(appointment);

        return appointment;
    }

    public List<AppointmentResponseDto> getAppointmentsByUserId(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }
}
