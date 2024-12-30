package com.siuuuuu.commodeami.user.command.aggregate.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PwdChangeRequestVO {

    private String currentPwd;

    @NotEmpty
    @Size(min = 8, max = 20, message = "최소 8자 이상 20자 이하로 구성해야합니다.")
    @Pattern(
            regexp = "^(?!.*(.)\\1{2}).*$",
            message = "동일한 문자를 3번 이상 연속해서 사용할 수 없습니다."
    )
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$",
            message = "비밀번호는 영문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private String newPwd;
}
