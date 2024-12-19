package com.siuuuuu.commodeami.user.command.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Table(name="TBL_USER")
@Entity
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="profile")
    private String profile;

    @Column(name="nickname")
    private String nickname;

    @Column(name="birth_date")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private Gender gender;

    @CreationTimestamp // 자동으로 현재 시간 설정
    @Column(name="created_at")
    private LocalDateTime createdAt;
}
