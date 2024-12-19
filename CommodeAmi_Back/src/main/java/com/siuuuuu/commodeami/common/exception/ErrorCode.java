package com.siuuuuu.commodeami.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 에러 상태 별 메세지
@Getter
@AllArgsConstructor
public enum ErrorCode {

    WRONG_ENTRY_POINT(40000, HttpStatus.BAD_REQUEST, "잘못된 접근입니다");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}
