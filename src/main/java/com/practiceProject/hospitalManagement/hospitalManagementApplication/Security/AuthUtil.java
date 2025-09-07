package com.practiceProject.hospitalManagement.hospitalManagementApplication.Security;

import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.RefreshToken;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.entity.User;
import com.practiceProject.hospitalManagement.hospitalManagementApplication.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthUtil {
//    @Value("${jwt.secretKey")
    private final String jwtSecretKey = Jwts.SIG.HS256.key().toString();
    private final RefreshTokenRepository refreshTokenRepository;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public String generateRefreshToken(User user){
        RefreshToken refreshToken = refreshTokenRepository.findByUserInfoId(user.getId()).orElse(null);
        if(refreshToken != null && refreshToken.getExpirationDateTime().isAfter(LocalDateTime.now())){
            return refreshToken.getToken();
        }
        RefreshToken newRefreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expirationDateTime(LocalDateTime.now().plusMinutes(15))
                .userInfo(user)
                .build();
        refreshTokenRepository.save(newRefreshToken);
        return newRefreshToken.getToken();
    }
}
