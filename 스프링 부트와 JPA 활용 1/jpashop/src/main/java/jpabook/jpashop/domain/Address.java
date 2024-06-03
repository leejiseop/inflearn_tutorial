package jpabook.jpashop.domain;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable // 생략가능?
@Getter @Setter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() { // jpa 는 리플렉션이나 프록시를 사용한다. 이때 기본 생성자가 필요함
    } // 근데 public 으로 해놓으면 위험하니 대신 protected 로 해두면
    // 딱 보고 아 jpa 때문에 만든거구나 함부로 new 하면 안되겠네 함

    public Address(String city, String street, String zipcode) { // 이게 public 이네!
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
