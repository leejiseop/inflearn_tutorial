package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent; // 셀프 매핑, 여러 자식 카테고리가 하나의 부모 카테고리에

    @OneToMany(mappedBy = "parent") // 자식 카테고리
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM", // 중간 테이블 CATEGORY_ITEM 을 만든다
            joinColumns = @JoinColumn(name = "CATEGORY_ID"), // JoinColumn = 외래키 매핑
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<Item> items = new ArrayList<>();

}
