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
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody // view, template ... 등 화면 말고 문자를 반환하겠다
    public String logDemo(HttpServletRequest request) {
        MyLogger myLogger = myLoggerProvider.getObject(); // 생성하여 반환

        String requestURL = request.getRequestURL().toString(); // .toString?
        myLogger.setRequestURL(requestURL); // Spring Interceptor, Subway Filter 를 활용하자

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";

    }

}
