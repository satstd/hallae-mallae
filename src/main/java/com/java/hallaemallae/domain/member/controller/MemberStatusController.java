package com.java.hallaemallae.domain.member.controller;

import com.java.hallaemallae.domain.member.dto.MemberDto;
import com.java.hallaemallae.domain.member.dto.MemberStatusDto;
import com.java.hallaemallae.domain.member.service.MemberStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberStatusController {
    private final MemberStatusService memberStatusService;

    @PostMapping
    public ResponseEntity<MemberStatusDto.Response> saveMemberStatus(MemberDto.Request memberDto) {
        return ResponseEntity.ok().body(
                MemberStatusDto.of(memberStatusService.saveMemberStatus(memberDto))
        );
    }

    @PatchMapping
    public ResponseEntity<MemberStatusDto.Response> updateMemberStatus(MemberStatusDto.Update statusDto) {
        return ResponseEntity.ok().body(
                MemberStatusDto.of(memberStatusService.updateMemberStatus(statusDto))
        );
    }
}
