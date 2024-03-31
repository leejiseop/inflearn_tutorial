package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // AppConfig의 @Bean 달려있는 객체들을 Spring 컨테이너에서 관리하게 된다
        // Bean이라 적힌 메서드들을 '호출'하여 '반환된 객체'를 Spring Container에 등록
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // Bean은 기본적으로 메서드 이름으로 등록된다
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println(("find member = " + findMember.getName()));
    }
}
