package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 조회기능 시 jpa 성능 최적화 (데이터 변경 안됨)
// @Transactional 붙여야 lazy loading, open session in view 등 가능
// jakarta, spring 둘 다 있지만 spring 의 @Transactional 이 기능이 더 많다
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional // 얘는 데이터 변경이 필요하니까 readOnly 없이 따로 붙임 (readOnly = false)
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원 있나 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // throws exception
        // 동시가입 방지 : DB에 멤버 이름을 unique 건다
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
