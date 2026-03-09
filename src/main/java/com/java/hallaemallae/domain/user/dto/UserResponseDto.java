package com.java.hallaemallae.domain.user.dto;

import com.java.hallaemallae.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private String nickname;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static UserResponseDto of(User user) {
        return new UserResponseDto(
                user.getUserId(),
                user.getUsername(),
                user.getNickname(),
                user.getCreatedDate(),
                user.getLastModifiedDate()
                );
    }
}
