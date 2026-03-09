package com.java.hallaemallae.domain.user.dto;

import com.java.hallaemallae.domain.user.service.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {
    private final Long userId;
    private final String username;
    private final String password;
    private final String nickname;
    private final Role role;

    public UserDetail(Long userId, String username, String password, String nickname, Role role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public Long getUserId() {
        return userId;
    }
}
