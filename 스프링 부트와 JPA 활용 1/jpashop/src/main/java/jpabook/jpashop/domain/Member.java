package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // 나는 연관관계 주인이 아니에요 거울이에요 -> order 테이블에 있는 member(member_id) 필드에 의해서 맵핑 된거야
//    여기에 값을 넣는다고 order 테이블에 있는 member 필드에 영향을 주지 않는다?
    private List<Order> orders = new ArrayList<>();
}
