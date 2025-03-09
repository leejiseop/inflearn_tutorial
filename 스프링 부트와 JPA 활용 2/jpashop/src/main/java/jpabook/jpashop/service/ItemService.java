package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item.Book;
import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional // (readOnly = false) 가 기본값
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        // 트랜잭션 안에서 조회를 해야 엔티티가 영속상태로 조회가 되고
        Item findItem = itemRepository.findOne(itemId);
        // 그것의 값을 바꿔야 변경감지가 된다 -> 커밋시 더티체킹으로 반영된다
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        // 이렇게 하는게 merge 보다 낫다
        // merge 는 모든 속성을 다 바꿔야 함 -> 실수로 null 값이 들어갈 수 있는 문제가 있다
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
