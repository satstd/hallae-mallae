package com.java.hallaemallae.domain.user.entity;

import com.java.hallaemallae.domain.user.dto.UserRequestDto;
import com.java.hallaemallae.domain.user.service.Role;
import com.java.hallaemallae.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private User(String username,
                 String password,
                 String nickname,
                 Role role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public static User createUser(UserRequestDto dto, PasswordEncoder encoder) {
        return new User(
                dto.getUsername(),
                encoder.encode(dto.getPassword()),
                dto.getNickname(),
                Role.ROLE_USER
        );
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
