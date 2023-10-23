package com.busanit.jpashop.service;

import com.busanit.jpashop.entity.Member;
import com.busanit.jpashop.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor    // 생성자 의존성 주입
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public Member saveMember(Member member) {
        // 중복 회원 검증
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        // 가입을 위해 입력한 멤버가 존재한다면
        if (findMember != null) {
            // 예외 발생
            throw new IllegalStateException("가입된 회원입니다.");
        }
    }

}
