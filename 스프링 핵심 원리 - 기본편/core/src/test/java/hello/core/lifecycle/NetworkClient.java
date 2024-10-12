package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);

    }

    public void call(String message) {
        System.out.println("call: " + url + " message=" + message);

    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    // 의존관계 주입이 끝나면 호출
    @PostConstruct // 애노테이션 방식을 권장. 스프링에 종속되지 않음
    // 외부 라이브러리에는 사용불가...
    // 외부 라이브러리에 사용하려면  @Bean(initMethod = "...", ...) 사용
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");

    }

    // 빈이 종료될 때 호출
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
