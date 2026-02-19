package com.java.hallaemallae.domain.member.controller;

import com.java.hallaemallae.domain.member.dto.MemberDto;
import com.java.hallaemallae.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDto.Response> saveMember(@RequestBody MemberDto.Request dto) {
        return ResponseEntity.ok().body(MemberDto.of(memberService.saveMember(dto)));
    }

    @PatchMapping
    public ResponseEntity<MemberDto.Response> updateMember(MemberDto.Update dto) {
        return ResponseEntity.ok().body(MemberDto.of(memberService.updateMember(dto)));
    }
}
