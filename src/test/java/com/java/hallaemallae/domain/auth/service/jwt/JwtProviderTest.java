package com.java.hallaemallae.domain.auth.service.jwt;

import com.java.hallaemallae.domain.member.dto.AuthDetails;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class JwtProviderTest {

    @Test
    void createToken() {
        JwtProvider jwtProvider = new JwtProvider("wnorfhbnosdujbvuiobauwoijnfjokdnsjklvn");
        AuthDetails authDetails = new AuthDetails(1L, "", "", "");
        String token = jwtProvider.createToken(authDetails);
        assertThat(token).isNotNull();
    }
}