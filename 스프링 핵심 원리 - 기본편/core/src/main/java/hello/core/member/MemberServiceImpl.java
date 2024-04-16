package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired // MemberRepository에 맞는 타입을 가져와서 자동으로 의존관계 주입
    public MemberServiceImpl(MemberRepository memberRepository) { // 딱 추상화(인터페이스)에만 의존한다!
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    // test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
