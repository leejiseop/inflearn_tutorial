package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
<<<<<<< Updated upstream

=======
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
>>>>>>> Stashed changes
public class OrderServiceImpl implements OrderService{

//    필드 주입
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

<<<<<<< Updated upstream
=======
//    lombok 의 @RequiredArgsConstructor 로 대체가능
    @Autowired
>>>>>>> Stashed changes
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { // 뭐든 인터페이스에 맞는 구현체를 넣어주겠지!
        this.memberRepository = memberRepository; // 오로지 인터페이스에만 의존!
        this.discountPolicy = discountPolicy; // DIP 원칙을 지킨다
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // discountPolicy 의 단일책임원칙!
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
<<<<<<< Updated upstream
=======
    
    // test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }
>>>>>>> Stashed changes
}
