package com.siuuuuu.commodeami.user.command.application.controller;

import com.siuuuuu.commodeami.common.ResponseDTO;
import com.siuuuuu.commodeami.user.command.aggregate.dto.UserDTO;
import com.siuuuuu.commodeami.user.command.aggregate.vo.PwdChangeRequestVO;
import com.siuuuuu.commodeami.user.command.aggregate.vo.RegistRequestVO;
import com.siuuuuu.commodeami.user.command.application.service.AppUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class AppUserController {

    private final AppUserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppUserController(AppUserService userService,
                             ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    // 회원 가입
    @PostMapping("/regist")
    public ResponseDTO<?> registNewUser(@RequestBody RegistRequestVO requestVO) {

        UserDTO newUser = modelMapper.map(requestVO, UserDTO.class);
        userService.registUser(newUser);

        return ResponseDTO.ok(newUser);
    }

    // 비밀번호 수정
    @PutMapping("/password/{userId}")
    public ResponseDTO<?> updatePassword(@PathVariable("userId") Long userId,
                                         @Valid @RequestBody PwdChangeRequestVO request) {
        log.info("비밀번호 수정 요청 들어옴");
        userService.updatePassword(userId, request.getCurrentPwd(), request.getNewPwd());

        return ResponseDTO.ok("비밀번호가 변경되었습니다.");
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseDTO<?> loggout() {

        return ResponseDTO.ok("로그아웃 되었습니다.");
    }
}
