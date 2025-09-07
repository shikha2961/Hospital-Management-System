package com.practiceProject.hospitalManagement.hospitalManagementApplication.contoller;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.Security.AuthService;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.LoginRequestDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.LoginResponseDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.RefreshTokenRequestDto;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.dto.SignupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody LoginRequestDto signupRequestDto){
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> validateRefreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequestDto));
    }
}
