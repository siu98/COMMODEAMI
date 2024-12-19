package com.siuuuuu.commodeami.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.siuuuuu.commodeami.common.exception.CommonException;
import com.siuuuuu.commodeami.common.exception.ExceptionDTO;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;

@Data
@Slf4j
public class ResponseDTO<T> {

    @JsonIgnore
    private HttpStatus httpStatus;

    @NonNull
    private boolean success;

    @Nullable
    private T data;

    @Nullable
    private ExceptionDTO error;

    // 기본 생성자
    public ResponseDTO() {
    }

    // 모든 필드를 받는 생성자
    public ResponseDTO(HttpStatus httpStatus, boolean success, @Nullable T data, @Nullable ExceptionDTO error) {
        this.httpStatus = httpStatus;
        this.success = success;
        this.data = data;
        this.error = error;
    }


    public static <T> ResponseDTO<T> ok(T data) {
        return new ResponseDTO<>(
                HttpStatus.OK,
                true,
                data,
                null
        );
    }

    //필기. 에러 발생시의 메세지(Ad)
    public static ResponseDTO<Object> fail(@NotNull CommonException e) {
        return new ResponseDTO<>(
                e.getErrorCode().getHttpStatus(),
                false,
                null,
                ExceptionDTO.of(e.getErrorCode())
        );
    }


}
