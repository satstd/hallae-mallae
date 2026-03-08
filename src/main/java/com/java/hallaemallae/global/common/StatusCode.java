package com.java.hallaemallae.global.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StatusCode {
    /*
        200 OK
     */
    OK(2000, HttpStatus.OK, "요청이 성공했습니다."),
    UPDATED(2001, HttpStatus.OK, "리소스가 성공적으로 업데이트되었습니다."),
    DELETED(2002, HttpStatus.OK, "리소스가 성공적으로 삭제되었습니다."),

    /*
       201 Created
     */
    CREATED(2003, HttpStatus.CREATED, "리소스가 성공적으로 생성되었습니다."),

    /*
       204 No Content
     */
    NO_CONTENT(2004, HttpStatus.NO_CONTENT, "성공적으로 처리되었으나 반환할 내용이 없습니다.");
		
		private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

    public int getStatusCode() {
        return httpStatus.value();
    }
}