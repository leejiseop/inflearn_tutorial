package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody // view, template ... 등 화면 말고 문자를 반환하겠다
    public String logDemo(HttpServletRequest request) {
//        MyLogger myLogger = myLoggerProvider.getObject(); // 생성하여 반환

        String requestURL = request.getRequestURL().toString(); // .toString?

        System.out.println("myLogger.getClass() = " + myLogger.getClass()); // 결과 : 가짜객체
        myLogger.setRequestURL(requestURL); // 기능 호출 시 진짜 객체 생성
        System.out.println("myLogger = " + myLogger);
        System.out.println("myLogger = " + myLogger.getClass());
        // Spring Interceptor, Subway Filter 를 활용하자

        /*
        * 스프링 컨테이너가 초기화될 때, ScopedProxyMode.TARGET_CLASS 설정을 사용하면,
        * 스프링은 원본 클래스를 상속받는 프록시 클래스의 인스턴스를 생성한다.
        * 이 프록시 클래스는 원본 클래스의 모든 메서드를 오버라이드하고,
        * 오버라이드된 각 메서드에는 실제 원본 빈의 메서드를 호출하는 위임 로직이 포함되어 있다.
        * 생성된 프록시 객체는 스프링 빈으로 스프링 컨테이너에 등록되고,
        * HTTP 요청이 있을 때, 클라이언트가 프록시 객체의 메서드를 호출하면,
        * 메서드 내의 위임 로직이 활성화되어
        * 실제 해당 HTTP 요청에 맞는 원본 빈의 해당 메서드를 (찾아) 실행한다.
        * 만약, 해당 HTTP 요청에 맞는 실제 원본 빈이 스프링 컨테이너에 없으면,
        * 위임 로직은 새로운 원본 빈 인스턴스를 생성하여 스프링 컨테이너에 등록한 후,
        * 그 인스턴스의 메서드를 실행한다.
        * */

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";

    }

}
