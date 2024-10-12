package hello.core.singleton;

public class SingletonService {

    // jvm 뜰때 객체 생성후 참조를 넣어둔다
    // static -> class 레벨에서 딱 하나만 존재하게 된다
    // stateless 하여야한다. 가급적 읽기만 가능. 값 수정 불가.
    public static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // 다른 곳에서 함부로 new를 쓰지 못하도록 생성자를 private으로 막는다
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글통 객체 로직 호출");
    }

}
