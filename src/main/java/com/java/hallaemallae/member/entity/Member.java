package com.java.hallaemallae.member.entity;

import com.java.hallaemallae.util.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberSeq;

    @Column(unique=true)
    private String loginId;
    private String password;
    private String nickname;

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeNickname(String password) {
        this.nickname = password;
    }
}
