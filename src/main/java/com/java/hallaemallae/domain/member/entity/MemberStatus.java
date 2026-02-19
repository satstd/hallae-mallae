package com.java.hallaemallae.domain.member.entity;

import com.java.hallaemallae.util.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PROTECTED)
public class MemberStatus extends BaseTimeEntity {

    @Id
    private Long memberSeq;

    @MapsId
    @OneToOne
    @JoinColumn(name = "memberSeq")
    private Member member;

    private Long totalGames;
    private Long totalWins;
    private Double reactionAverage;

    public void changeTotalGames(Long totalGames) {
        this.totalGames += totalGames;
    }

    public void changeTotalWins(Long totalWins) {
        this.totalWins += totalWins;
    }

    public void changeReactionAverage(Double reactionAverage) {
        this.reactionAverage = reactionAverage;
    }
}
