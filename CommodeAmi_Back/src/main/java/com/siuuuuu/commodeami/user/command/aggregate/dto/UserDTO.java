package com.siuuuuu.commodeami.user.command.aggregate.dto;

import com.siuuuuu.commodeami.user.command.aggregate.entity.Gender;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class UserDTO {

    private Long userId;
    private String userName;
    private String email;
    private String password;
    private String profile;
    private String nickname;
    private Date birthDate;
    private Gender gender;
    private LocalDateTime createdAt;
}
