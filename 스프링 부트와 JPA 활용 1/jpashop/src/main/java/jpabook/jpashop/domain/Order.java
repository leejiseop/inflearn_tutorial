package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // order 는 예약어 때문에 안됨
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 막는 대신 생성 메서드 사용
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // Foreign key 이름이 된다. 내가 주인이다!
    private Member member;

    // 엔티티
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // cascade: persist 를 한번에
    // Order 를 persist 하면 연결된 orderItems 도 persist 된다
    // 프라이빗하고, 같은 라이프 사이클로 관리되는 경우에만... 남용은 X
    private List<OrderItem> orderItems = new ArrayList<>();

    // Foreign key 는 양쪽 어디에나 둬도 된다... 주로 사용하는 곳에 Foreign key 를 둔다
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // Hibernate 가 알아서 날짜 설정

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    //==연관관계 편의 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this); // 따로따로 안해도 되도록 양쪽에 같이 넣어준다
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==// -> 이름에 의도를 표현할 수 있다
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//

    // 주문 취소
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//

    // 전체 주문 가격 조회
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice(); // 주문 수량 곱해줘야함
        }
        return totalPrice;
    }
}
