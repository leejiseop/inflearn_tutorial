package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);

        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);

        DiscountPolicy discountPolicy = bean.getDiscountPolicy();
        System.out.println("discountPolicy = " + discountPolicy);


    }

}
