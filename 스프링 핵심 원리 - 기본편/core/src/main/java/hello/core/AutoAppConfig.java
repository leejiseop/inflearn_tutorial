package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan ( // @Component 있는건 쫙 빈으로 등록해줄건데, 그 중에서 뺄 것 지정
        // 기존 AppConfig, 테스트의 Config 등과 충돌 방지 목적
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
// 기존 AppConfig 와는 달리, 따로 의존관계 주입하는 과정이 없는데 어떻게하냐?
// 각 컴포넌트 생성자에 Autowired 적용




}
