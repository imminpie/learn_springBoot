package com.condingrecipe.member.service;

import com.condingrecipe.member.dto.MemberDTO;
import com.condingrecipe.member.entity.MemberEntity;
import com.condingrecipe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    // 생성자 주입
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // 조건: Entity 객체로 넘겨준다.

        // 1. DTO → Entity 객체로 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        // 2. Repository 의 save 메소드를 호출한다.
        memberRepository.save(memberEntity); // save: JPA 가 제공하는 메소드 → insert
    }
}
