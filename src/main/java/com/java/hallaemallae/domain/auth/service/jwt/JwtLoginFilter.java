package com.java.hallaemallae.domain.auth.service.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.hallaemallae.domain.auth.dto.AuthDto;
import com.java.hallaemallae.domain.auth.dto.JwtDto;
import com.java.hallaemallae.domain.auth.service.JwtService;
import com.java.hallaemallae.domain.member.dto.AuthDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;
    private final JwtService jwtService;// jwt 생성 서비스

    public JwtLoginFilter(AuthenticationManager manager, JwtService jwtService, ObjectMapper objectMapper) {
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
        setAuthenticationManager(manager);
        setFilterProcessesUrl("/login");//로그인시 사용할 경로
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            AuthDto.Request authRequest
                    = objectMapper.readValue(request.getInputStream(), AuthDto.Request.class);
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(authRequest.getMemberId(), authRequest.getPassword());
            return getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new AuthenticationServiceException("Failed to authenticate user", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException {
        AuthDetails principal = (AuthDetails) authResult.getPrincipal();

        String at = jwtService.createAccessToken(principal);
        JwtDto.RefreshToken rt = jwtService.createRefreshToken(principal.getMemberSeq());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.addHeader(HttpHeaders.SET_COOKIE, rt.getTokenString());

        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        Map.of("accessToken", at)
                )
        );
    }
}
