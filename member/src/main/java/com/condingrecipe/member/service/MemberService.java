package com.condingrecipe.member.service;

import com.condingrecipe.member.dto.MemberDTO;
import com.condingrecipe.member.entity.MemberEntity;
import com.condingrecipe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다면
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                // 비밀번호 일치
                // Entity 객체 → DTO 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                return null;
            }

        } else {
            // 조회 결과가 없다면
            return null;
        }
    }
}
