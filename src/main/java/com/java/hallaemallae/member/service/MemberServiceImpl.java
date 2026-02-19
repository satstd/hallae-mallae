package com.java.hallaemallae.member.service;

import com.java.hallaemallae.exception.MemberLoginIdDuplicatedException;
import com.java.hallaemallae.member.dto.MemberDto;
import com.java.hallaemallae.member.entity.Member;
import com.java.hallaemallae.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member saveMember(MemberDto.Request dto) {
        if (existByLoginId(dto.getLoginId())) {
            throw new MemberLoginIdDuplicatedException("이미 존재하는 아이디입니다.");
        }

        Member member = Member.builder()
                .nickname(dto.getNickname())
                .loginId(dto.getLoginId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        return memberRepository.save(member);
    }

    @Override
    public Member getMemberBySeq(Long seq) {
        return memberRepository.getMemberByMemberSeq(seq);
    }

    @Override
    public Member getMemberByLoginId(String loginId) {
        return memberRepository.getMemberByLoginId(loginId);
    }

    @Override
    public Member updateMember(MemberDto.Update dto) {
        Member member = getMemberBySeq(dto.getSeq());
        member.changePassword(passwordEncoder.encode(dto.getPassword()));
        member.changeNickname(dto.getPassword());
        return member;
    }

    @Override
    public Boolean existByLoginId(String loginId) {
        return memberRepository.existsByLoginId(loginId);
    }

    @Override
    @Transactional
    public void deleteMember(MemberDto.Request dto) {
        Member member = getMemberByLoginId(dto.getLoginId());
        if (member == null) {
            return;
        }
        memberRepository.delete(member);
    }
}
