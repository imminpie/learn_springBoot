package com.condingrecipe.member.repository;

import com.condingrecipe.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 이메일로 회원정보를 조회하는 메소드 정의
    // (select * from member_table where member_email = ?)
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
