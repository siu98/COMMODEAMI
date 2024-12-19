package com.siuuuuu.commodeami.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommonException  extends RuntimeException {
    private final ErrorCode errorCode;

    // 에러 발생시 ErrorCode별 메세자
    @Override
    public String getMessage() {
        return this.errorCode.getMessage();
    }
}
