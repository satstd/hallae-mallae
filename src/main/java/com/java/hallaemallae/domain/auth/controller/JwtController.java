package com.java.hallaemallae.domain.auth.controller;

import com.java.hallaemallae.domain.auth.dto.JwtDto;
import com.java.hallaemallae.domain.auth.service.JwtService;
import com.java.hallaemallae.domain.member.dto.AuthDetails;
import com.java.hallaemallae.domain.member.entity.Member;
import com.java.hallaemallae.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtController {
    private final JwtService jwtService;
    private final MemberService memberService;

    @PostMapping("refresh")
    public ResponseEntity<JwtDto.Response> refresh(@CookieValue("refresh_token") String refreshToken) {
        //토큰 조회
        String authSeq = jwtService.findAuthSeqByRefreshTokenId(refreshToken);

        //사용자 정보 조회
        Member member = memberService.getMemberBySeq(Long.parseLong(authSeq));

        AuthDetails authDetails =
                new AuthDetails(member.getMemberSeq(), member.getLoginId(),
                        member.getPassword(), member.getRole().name());
        //at, rt 생성
        String at = jwtService.createAccessToken(authDetails);
        JwtDto.RefreshToken rt = jwtService.createRefreshToken(member.getMemberSeq());


        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, rt.getTokenString())
                .body(new JwtDto.Response(at));
    }
}
