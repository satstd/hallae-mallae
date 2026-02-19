package com.java.hallaemallae.domain.member.service;

import com.java.hallaemallae.domain.member.dto.MemberDto;
import com.java.hallaemallae.domain.member.entity.Member;

public interface MemberService {
    Member saveMember(MemberDto.Request dto);
    Member getMemberBySeq(Long seq);
    Member getMemberByLoginId(String loginId);
    Member updateMember(MemberDto.Update dto);
    Boolean existByLoginId(String loginId);
    void deleteMember(MemberDto.Request dto);
}
