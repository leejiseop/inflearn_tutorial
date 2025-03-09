package hello.core;

import hello.core.member.Member;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootTest
class CoreApplicationTests {

	@Test
	void contextLoads() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig AppConfigBean = ac.getBean(AppConfig.class);
		MemberService memberService = ac.getBean("memberService", MemberService.class);
		System.out.println("AppConfigBean = " + AppConfigBean);
		System.out.println("memberService = " + memberService);
	}

}