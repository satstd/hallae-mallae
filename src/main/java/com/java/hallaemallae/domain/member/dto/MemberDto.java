package com.java.hallaemallae.domain.member.dto;

import com.java.hallaemallae.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Long seq;
        private String loginId;
        private String password;
        private String nickname;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long seq;
        private String loginId;
        private String nickname;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Update {
        private Long seq;
        private String loginId;
        private String password;
        private String nickname;
    }

    public static Response of(Member member) {
        return Response.builder()
                .seq(member.getMemberSeq())
                .loginId(member.getLoginId())
                .nickname(member.getNickname())
                .build();
    }
}
