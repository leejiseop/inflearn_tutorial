package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id") // DB 컬럼 FK
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // DB 컬럼 FK
    private Order order;

    private int orderPrice; // 주문 가격

    private int count; // 주문 수량

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    public void cancel() {
        item.addStock(count); // getItem().addStock(count); 원래는 이렇게
    }

    //==조회 로직==//

    // 주문상품 전체 가격 조회
    public int getTotalPrice() {
        return getOrderPrice() * getCount(); // https://www.inflearn.com/questions/20180/orderitem-%EA%B4%80%EB%A0%A8
    }
}
