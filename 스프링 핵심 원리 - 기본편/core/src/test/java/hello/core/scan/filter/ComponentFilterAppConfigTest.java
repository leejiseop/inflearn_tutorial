package hello.core.scan.filter;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(
            NoSuchBeanDefinitionException.class,
            () -> ac.getBean("beanB", BeanB.class)
        );
    }


    @Configuration
    @ComponentScan(
            /*
            * FilterType
            * type = FilterType.ANNOTATION 기본값(생략 가능) - 이 어노테이션이 달려있는 클래스를 지정하겠다
            * type = ASSIGNABLE_TYPE 클래스 명으로 직접 지정하겠다
            * type = ASPECTJ AspectJ 패턴 사용
            * type = REGEX 정규 표현식
            * type = CUSTOM TypeFilter 라는 인터페이스 구현하여 처리
            * */

            // @Component 면 충분하기 때문에, 쓸 일 거의 없음
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            // 가끔 사용
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }



}
