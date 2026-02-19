package com.java.hallaemallae.exception;

public class MemberLoginIdDuplicatedException extends RuntimeException {
    public MemberLoginIdDuplicatedException() {}
    public MemberLoginIdDuplicatedException(String message) {
        super(message);
    }
}
