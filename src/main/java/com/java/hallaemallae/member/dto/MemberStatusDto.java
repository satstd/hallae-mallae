package com.java.hallaemallae.member.dto;

import com.java.hallaemallae.member.entity.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberStatusDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private Long memberSeq;
        private Long totalGames;
        private Long totalWins;
        private Double reactionAverage;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Update {
        private Long memberSeq;
        private Long totalGames;
        private Long totalWins;
        private Double reactionAverage;
    }

    public static Response of(MemberStatus memberStatus) {
        return Response.builder()
                .memberSeq(memberStatus.getMemberSeq())
                .totalGames(memberStatus.getTotalGames())
                .totalWins(memberStatus.getTotalWins())
                .reactionAverage(memberStatus.getReactionAverage())
                .build();
    }
}
