package com.siuuuuu.commodeami.common.exception;

import lombok.Data;

// 에러 응답 형식(코드, 메세지)
@Data
public class ExceptionDTO {
    private final Integer code;
    private final String message;
    public ExceptionDTO(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static ExceptionDTO of(ErrorCode errorCode){
        return new ExceptionDTO(errorCode);
    }
}
