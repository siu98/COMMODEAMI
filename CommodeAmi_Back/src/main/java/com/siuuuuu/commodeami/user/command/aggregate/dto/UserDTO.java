package com.siuuuuu.commodeami.user.command.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siuuuuu.commodeami.user.command.aggregate.entity.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class UserDTO {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("profile")
    private String profile;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("birth_date")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
