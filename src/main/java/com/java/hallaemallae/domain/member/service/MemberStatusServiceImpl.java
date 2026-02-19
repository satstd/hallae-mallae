package com.java.hallaemallae.domain.member.service;

import com.java.hallaemallae.domain.member.dto.MemberDto;
import com.java.hallaemallae.domain.member.dto.MemberStatusDto;
import com.java.hallaemallae.domain.member.entity.Member;
import com.java.hallaemallae.domain.member.entity.MemberStatus;
import com.java.hallaemallae.domain.member.repository.MemberStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberStatusServiceImpl implements MemberStatusService {
    private final MemberStatusRepository memberStatusRepository;
    private final MemberService memberService;

    @Override
    public MemberStatus getMemberStatus(Long memberSeq) {
        return memberStatusRepository.getMemberStatusByMemberSeq(memberSeq);
    }

    @Override
    public MemberStatus getMemberStatus(String memberLoginId) {
        return memberStatusRepository.getMemberStatusByMember_LoginId(memberLoginId);
    }

    @Override
    public MemberStatus saveMemberStatus(MemberDto.Request memberDto) {
        Member member = memberService.getMemberBySeq(memberDto.getSeq());

        MemberStatus status = MemberStatus.builder()
                .memberSeq(member.getMemberSeq())
                .member(member)
                .reactionAverage(0.0D)
                .totalGames(0L)
                .totalWins(0L)
                .build();
        return memberStatusRepository.save(status);
    }

    @Override
    public MemberStatus updateMemberStatus(MemberStatusDto.Update statusDto) {
        MemberStatus memberStatus = getMemberStatus(statusDto.getMemberSeq());

        memberStatus.changeTotalGames(statusDto.getTotalGames());
        memberStatus.changeTotalWins(statusDto.getTotalWins());
        memberStatus.changeReactionAverage(calculateReactionAverage(statusDto, memberStatus));
        return memberStatus;
    }

    private Double calculateReactionAverage(MemberStatusDto.Update statusDto, MemberStatus memberStatus) {
        Long totalGames = memberStatus.getTotalGames();//기존 게임 횟수
        Double reactionAverage = memberStatus.getReactionAverage();//기존 반응 속도
        Double originValue = totalGames * reactionAverage;//반응속도 전체 총합
        double added = originValue + statusDto.getReactionAverage();//기존값 + 새로 추가된 값

        //추가된 게임 시행 수 + 기존 게임 시행수로 평균 구하기
        return added / (totalGames + statusDto.getTotalGames());
    }
}
