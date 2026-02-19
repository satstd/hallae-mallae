package com.java.hallaemallae.member.repository;

import com.java.hallaemallae.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByLoginId(String loginId);

    Member getMemberByMemberSeq(Long memberSeq);

    void deleteByMemberSeq(Long seq);

    Member getMemberByLoginId(String loginId);
}
