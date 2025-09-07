package com.practiceProject.hospitalManagement.hospitalManagementApplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime expirationDateTime;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User userInfo;
}
