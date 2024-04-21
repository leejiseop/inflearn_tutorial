package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan (
        // @Component 있는건 쫙 빈으로 등록해줄건데, 그 중에서 뺄 것 지정
        // 기존 AppConfig, 테스트의 Config 등과 충돌 방지 목적
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

//        basePackageClasses = AutoAppConfig.class,
//        basePackages = "hello.core.member"

        // 원래는 이게 없으면 모든 패키지를 다 탐색한다.. 오래걸림
        // 지정하지 않으면 -> @ComponentScan 의 패키지 및 하위 패키지를 탐색
        // 설정 정보 클래스위 위치를 프로젝트 최상단에 두면 좋다
        // 어차피 @SpringBootApplication 내부에 @ComponentScan 이 있다
)
public class AutoAppConfig {
// 기존 AppConfig 와는 달리, 따로 의존관계 주입하는 과정이 없는데 어떻게하냐?
// 각 컴포넌트 생성자에 Autowired 적용

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }



}
