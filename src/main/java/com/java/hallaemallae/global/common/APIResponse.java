package com.java.hallaemallae.global.common;

import io.swagger.v3.oas.annotations.media.Schema;
import com.java.hallaemallae.global.exception.CustomException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@Schema(description="API 응답 공통 포맷")
public class APIResponse<T> {
    @Schema(description="응답 코드")
    private int code;

    @Schema(description="응답 메시지")
    private String message;

    @Schema(description="데이터")
    private T data;

    // 성공
    public static <T> APIResponse<T> success(){
        return new APIResponse<>(StatusCode.OK.getStatusCode(), StatusCode.OK.getMessage(), null);
    }

    public static <T> APIResponse<T> success(T data) {
        return new APIResponse<>(StatusCode.OK.getStatusCode(), StatusCode.OK.getMessage(), data);
    }

    public static <T> APIResponse<T> success(StatusCode code, T data) {
        return new APIResponse<>(code.getStatusCode(), code.getMessage(), data);
    }

    public static <T> APIResponse<T> success(StatusCode code, String message, T data) {
        return new APIResponse<>(code.getStatusCode(), message, data);
    }

    public static <T> APIResponse<T> fail(ErrorCode code){
        return new APIResponse<>(code.getStatusCode(), code.getMessage(), null);
    }

    public static <T> APIResponse<T> fail(ErrorCode code, String message) {
        return new APIResponse<>(code.getStatusCode(), message, null);
    }

    public static <T> APIResponse<T> fail(CustomException e) {
        ErrorCode code = e.getErrorCode();
        if (code == null) {
            return new APIResponse<>(ErrorCode.INTERNAL_SERVER_ERROR.getStatusCode(),
                    ErrorCode.INTERNAL_SERVER_ERROR.getMessage(), null);
        }
        return new APIResponse<>(code.getStatusCode(), code.getMessage(), null);
    }

    public static <T> APIResponse<T> create(int code, String message, T data) {
        return new APIResponse<>(code, message, data);
    }

    public static <T> APIResponse<T> create() {
        return new APIResponse<>(StatusCode.CREATED.getStatusCode(), StatusCode.CREATED.getMessage(), null);
    }

    public static <T> APIResponse<T> create(T data) {
        return new APIResponse<>(StatusCode.CREATED.getStatusCode(), StatusCode.CREATED.getMessage(), data);
    }
}
