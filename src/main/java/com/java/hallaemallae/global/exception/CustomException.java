package com.java.hallaemallae.global.exception;

import com.java.hallaemallae.global.common.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode != null ? errorCode.getMessage() : null);
        this.errorCode = errorCode;
    }
}
