package jpabook.jpashop.domain.Item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // SINGLE_TABLE, TABLE_PER_CLASS, JOINED;
@DiscriminatorColumn(name = "dtype") // A, B, M
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>(); // NPE 방지, hibernate 최적화

    //==비즈니스 로직==//
    //==도메인 주도 설계==//
    // 객체지향적으로 생각해보면.. 데이터를 가지고 있는 쪽에 비즈니스 메서드가 있어야 응집력이 좋다
    // 가지고 있는 stockQuantity 를 직접 사용하기 때문

    // 무작정 setter 사용하는것이 아니라 내부에서 이렇게 비즈니스 로직을 거쳐서 값을 변경해야 한다
    // 이게 객체지향적
    
    // 재고 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    // 재고 감소
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
