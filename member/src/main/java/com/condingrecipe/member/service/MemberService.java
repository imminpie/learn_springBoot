package com.condingrecipe.member.service;

import com.condingrecipe.member.dto.MemberDTO;
import com.condingrecipe.member.entity.MemberEntity;
import com.condingrecipe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();

        // Entity 가 여러 개 담긴 객체를 DTO 로 변환
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);

        // Entity 객체 → DTO 변환 후 리턴
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(myEmail);
        if (byMemberEmail.isPresent()) {
            return MemberDTO.toMemberDTO(byMemberEmail.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        // JpaRepository 의 save() 메소드는 id 가 없다면 insert 를 실행하고, 없으면 update 를 실행한다.
        // DTO → Entity 객체로 변환
        MemberEntity memberEntity = MemberEntity.toUpdateMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }
}
