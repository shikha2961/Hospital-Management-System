package com.practiceProject.hospitalManagement.hospitalManagementApplication.Security;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.LoginRequestDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.LoginResponseDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.RefreshTokenRequestDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.SignupResponseDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.RefreshToken;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.User;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.repository.RefreshTokenRepository;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String accessToken = authUtil.generateAccessToken(user);

        String refreshToken = authUtil.generateRefreshToken(user);

        return new LoginResponseDto(accessToken, user.getId(), refreshToken);
    }

    public SignupResponseDto signup(LoginRequestDto signupRequestDto){
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        System.out.println("user: " +  user);
        if(user != null){
            throw new IllegalArgumentException("User already exists..");
        }
        user = userRepository.save(User.builder()
                .username(signupRequestDto.getUsername())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build()
        );
        return new SignupResponseDto(user.getId(), user.getUsername());
    }

    public String refreshToken(RefreshTokenRequestDto refreshTokenRequestDto){
        User user = userRepository.findById(refreshTokenRequestDto.getId()).orElseThrow();
        RefreshToken refreshToken = refreshTokenRepository.findByUserInfoId(refreshTokenRequestDto.getId()).orElseThrow();
        if(refreshToken.getExpirationDateTime().isAfter(LocalDateTime.now())){
            return authUtil.generateAccessToken(user);
        }
        return "";
    }
    /*
    User comes with refreshToken and Id
    1. Check refreshTokenValidity
    2. if refreshToken is valid - then log in the user again without taking user information from usr.

     */
}
