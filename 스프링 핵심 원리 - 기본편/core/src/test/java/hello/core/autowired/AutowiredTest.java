package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); // 바로 빈 등록된다
    }

    static class TestBean {

        // required = true
//        @Autowired(required = true) // -> 예외터진다 (Member 는 Bean 으로 등록되어있지 않기 때문)
//        public void setNoBean(Member noBean1) { // Bean이 아닌 Member
//            System.out.println("noBean1 = " + noBean1);
//        }

        // required = false
        @Autowired(required = false) // 의존관계가 없으면 -> 메소드 호출이 아예 안된다!
        public void setNoBean1(Member noBean1) { // Bean이 아닌 Member
            System.out.println("noBean1 = " + noBean1);
        }

        // @Nullable
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) { // Bean이 아닌 Member
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional<Member>
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) { // Bean이 아닌 Member
            System.out.println("noBean3 = " + noBean3);
        }

    }

}
