package com.java.hallaemallae.member.service;

import com.java.hallaemallae.member.dto.MemberDto;
import com.java.hallaemallae.member.entity.Member;

public interface MemberService {
    Member saveMember(MemberDto.Request dto);
    Member getMemberBySeq(Long seq);
    Member getMemberByLoginId(String loginId);
    Member updateMember(MemberDto.Update dto);
    Boolean existByLoginId(String loginId);
    void deleteMember(MemberDto.Request dto);
}
