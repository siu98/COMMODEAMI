package com.siuuuuu.commodeami.user.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siuuuuu.commodeami.user.command.aggregate.entity.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistRequestVO {

    @JsonProperty("user_name")
    @NotNull
    private String userName;

    @JsonProperty("password")
    @NotNull
    private String password;

    @JsonProperty("email")
    @NotNull
    private String email;

    @JsonProperty("profile")
    private String profile;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("birth_date")
    private Date birthDate;

    @JsonProperty("gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
