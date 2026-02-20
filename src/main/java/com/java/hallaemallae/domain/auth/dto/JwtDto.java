package com.java.hallaemallae.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;

public class JwtDto {

    @Getter
    @RequiredArgsConstructor
    public static class RefreshToken {
        private final String id;
        private final ResponseCookie token;

        public String getTokenString() {
            return token.toString();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String accessToken;
    }

}
