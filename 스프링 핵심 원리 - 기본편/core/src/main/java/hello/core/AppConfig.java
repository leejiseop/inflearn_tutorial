package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// AppConfig는 공연 기획자다.. 필요한 객체들을 여기서 할당.. 배우들은 대본에 따른 연기만 고민하면 묀다
public class AppConfig { // 애플리케이션의 전체 동작 방식을 구성하기 위해 구현 객체를 생성하고 연결하는 책임을 가진 별도의 클래스
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository()); // 객체 주입은 밖에서 (생성자 주입)
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
