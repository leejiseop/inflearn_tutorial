package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // ORDINAL(1, 2, 3 ... 밀리면 큰일난다!), STRING(무조건 이거로)
    private DeliveryStatus status; // READY, COMP
}
// 강의 pdf 틀어놓고 비교 분석하기