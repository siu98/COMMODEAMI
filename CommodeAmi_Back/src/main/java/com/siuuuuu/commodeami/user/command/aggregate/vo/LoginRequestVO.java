package com.siuuuuu.commodeami.user.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestVO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
