package com.java.hallaemallae.domain.auth.service;

import com.java.hallaemallae.domain.auth.dto.JwtDto;
import com.java.hallaemallae.domain.auth.service.jwt.JwtProvider;
import com.java.hallaemallae.domain.member.dto.AuthDetails;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProvider jwtProvider;
    private final StringRedisTemplate redisTemplate;


    //RT 사용 후 삭제 용도
    public void deleteToken(String token) {
        String tokenId = jwtProvider.parseClaims(token).getSubject();
        redisTemplate.delete(tokenId);
    }

    //요청 받은 RT가 서버에 있는지 확인
    public String findAuthSeqByRefreshTokenId(String token) {
        Claims claims = getClaims(token);
        String tokenId = claims.getSubject();

        if (tokenId == null) {
            throw new IllegalArgumentException("Token ID not found");
        }

        String value = redisTemplate.opsForValue().get(tokenId);//사용자의 인증 정보 pk

        if (value == null) {
            throw new IllegalArgumentException("Token not found");
        }

        deleteToken(token);
        return value;
    }

    public Claims getClaims(String token) {
        return jwtProvider.parseClaims(token);
    }


    //액세스 토큰 생성
    public String createAccessToken(AuthDetails authDetails) {
        return jwtProvider.createToken(authDetails);
    }

    //리프레시 토큰 생성 및 저장
    public JwtDto.RefreshToken createRefreshToken(Long userSeq) {
        JwtDto.RefreshToken dto = jwtProvider.createRefreshToken();//rt 생성
        redisTemplate.opsForValue().set(dto.getId(), String.valueOf(userSeq), Duration.ofDays(30));//uuid -> authSeq
        return dto;
    }
}
