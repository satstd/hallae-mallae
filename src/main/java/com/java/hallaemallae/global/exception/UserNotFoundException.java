package com.java.hallaemallae.global.exception;

import com.java.hallaemallae.global.common.ErrorCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(ErrorCode code) {
        super(code);
    }
}
