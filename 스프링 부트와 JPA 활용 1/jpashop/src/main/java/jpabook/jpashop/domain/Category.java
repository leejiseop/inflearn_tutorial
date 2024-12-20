package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable( // 다대다를 일대다 - 다대일로 풀어내는 중간 테이블
            name = "category_item", // 이라는 테이블로 연결할건데
            joinColumns = @JoinColumn(name = "category_id"), // category_item 의 category_id
            inverseJoinColumns = @JoinColumn(name = "item_id")) // category_item 의 item 쪽
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // 셀프로 양방향 연관관계를 걸었다
    private Category parent;

    @OneToMany(mappedBy = "parent") // 셀프로 양방향 연관관계를 걸었다
    private List<Category> child = new ArrayList<>();

    //==연관관계 편의 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }


}
