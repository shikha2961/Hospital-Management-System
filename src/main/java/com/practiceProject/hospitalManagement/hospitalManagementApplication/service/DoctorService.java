package com.practiceProject.hospitalManagement.hospitalManagementApplication.service;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.DoctorRequestDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.DoctorResponseDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.Doctor;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.User;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.repository.AppointmentRepository;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.repository.DoctorRepository;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public List<DoctorResponseDto> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class)).toList();
    }

    @Transactional
    public DoctorResponseDto onBoardNewDoctor(DoctorRequestDto doctorRequestDto){
        User user = userRepository.findById(doctorRequestDto.getId()).orElseThrow();

        if(doctorRepository.existsById(doctorRequestDto.getId())){
            throw new IllegalArgumentException("doctor exists already");
        }

        Doctor doctor = Doctor.builder()
                .name(doctorRequestDto.getName())
                .specialization(doctorRequestDto.getSpecialization())
                .email(doctorRequestDto.getEmail())
                .user(user)
                .build();

        return modelMapper.map(doctorRepository.save(doctor), DoctorResponseDto.class);
    }

}
