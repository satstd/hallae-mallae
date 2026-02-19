package com.java.hallaemallae.domain.member.service;

import com.java.hallaemallae.domain.member.dto.MemberDto;
import com.java.hallaemallae.domain.member.dto.MemberStatusDto;
import com.java.hallaemallae.domain.member.entity.MemberStatus;

public interface MemberStatusService {
    MemberStatus getMemberStatus(Long memberSeq);
    MemberStatus getMemberStatus(String memberId);
    MemberStatus saveMemberStatus(MemberDto.Request memberDto);
    MemberStatus updateMemberStatus(MemberStatusDto.Update statusDto);
}
