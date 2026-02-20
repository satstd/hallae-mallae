package com.java.hallaemallae.domain.auth.service.jwt;

import com.java.hallaemallae.domain.auth.dto.JwtDto;
import com.java.hallaemallae.domain.member.dto.AuthDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider {
    private final Key key;
    private final long MIN = 1000L * 60; // 1분
    private final long HOUR = 1000L * 60 * 60; // 1시간
    private final long DAY = 86400L * 1000; // 1일

    public JwtProvider(@Value("${jwt.secret}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // 토큰 생성
    public String createToken(AuthDetails authDetails) {
        return Jwts.builder()
                .setSubject(String.valueOf(authDetails.getMemberSeq()))
                .claim("role", authDetails.getRole())
                .claim("username", authDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + MIN * 5))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 유효성 검증
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }


    public JwtDto.RefreshToken createRefreshToken() {
        String tokenId = UUID.randomUUID().toString();
        String token = Jwts.builder()
                .setSubject(tokenId)
                .claim("type", "refresh_token")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + DAY * 30))// 30일
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new JwtDto.RefreshToken(tokenId, parseCookie(token));
    }

    private static ResponseCookie parseCookie(String token) {
        return ResponseCookie.from("refresh_token", token)
                .maxAge(Duration.ofDays(30))
                .httpOnly(true)//개발자 콘솔에서 읽지 못하게
                .secure(false)//https 필수 옵션인데 지금 없으니 일단 false
                .sameSite("None")//요청부와 응답부 도메인이 같아야하는가?
                .path("/refresh")//이 경로로 사작하는 요청에만 이 쿠키를 자동으로 포함 시킴
                .build();
    }

}
