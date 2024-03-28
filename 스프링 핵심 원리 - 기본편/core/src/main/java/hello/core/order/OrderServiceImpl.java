package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

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
}
