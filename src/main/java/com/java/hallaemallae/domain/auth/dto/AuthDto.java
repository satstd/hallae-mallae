package com.java.hallaemallae.domain.auth.dto;

import lombok.*;

public class AuthDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String memberId;
        private  String password;
    }
}
