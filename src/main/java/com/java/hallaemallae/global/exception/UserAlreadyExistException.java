package com.java.hallaemallae.global.exception;

import com.java.hallaemallae.global.common.ErrorCode;

public class UserAlreadyExistException extends CustomException {
    public UserAlreadyExistException(ErrorCode code) {
        super(code);
    }
}
