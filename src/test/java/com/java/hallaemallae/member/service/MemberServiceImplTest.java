package com.java.hallaemallae.member.service;

import com.java.hallaemallae.domain.member.dto.MemberDto;
import com.java.hallaemallae.domain.member.entity.Member;
import com.java.hallaemallae.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Test
    @Transactional
    void saveMember() {

        MemberDto.Request dto = MemberDto.Request.builder()
                .loginId("loginId")
                .password("password")
                .nickname("nickname")
                .build();

        Member member = memberService.saveMember(dto);
        assertThat(member.getNickname()).isEqualTo(dto.getNickname());
    }

    @Test
    @Transactional
    void updateMember() {
        MemberDto.Request dto = MemberDto.Request.builder()
                .loginId("loginId")
                .password("password")
                .nickname("nickname")
                .build();

        Member member = memberService.saveMember(dto);


        member.changeNickname("newNickname");
        assertThat(member.getNickname()).isEqualTo("newNickname");

        String password = member.getPassword();
        member.changePassword("newPassword");
        assertThat(member.getPassword()).isNotEqualTo(password);

    }

    @Test
    @Transactional
    void existMemberTest() {
        MemberDto.Request dto = MemberDto.Request.builder()
                .loginId("loginId")
                .password("password")
                .nickname("nickname")
                .build();

        memberService.saveMember(dto);

        assertThat(memberService.existByLoginId(dto.getLoginId())).isTrue();
    }

    @Test
    @Transactional
    void deleteMemberTest() {
        MemberDto.Request dto = MemberDto.Request.builder()
                .loginId("loginId")
                .password("password")
                .nickname("nickname")
                .build();
        memberService.saveMember(dto);

        memberService.deleteMember(dto);

        assertThat(memberService.existByLoginId(dto.getLoginId())).isFalse();
    }
}