package com.java.hallaemallae.member.repository;

import com.java.hallaemallae.member.entity.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStatusRepository extends JpaRepository<MemberStatus, Long> {
    MemberStatus getMemberStatusByMemberSeq(Long memberSeq);

    MemberStatus getMemberStatusByMember_LoginId(String memberLoginId);
}
