package com.fruityveggies.www.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fruityveggies.www.dto.ItemItemOptionDto;
import com.fruityveggies.www.repository.Cart;
import com.fruityveggies.www.repository.CartRepository;
import com.fruityveggies.www.repository.Item;
import com.fruityveggies.www.repository.ItemRepository;
import com.fruityveggies.www.repository.OrderItems;
import com.fruityveggies.www.repository.OrderItemsRepository;
import com.fruityveggies.www.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    public List<Item> findByAll() {
        return itemRepository.findAll();
    }
    
    public Item findItemById(long id) {
        return itemRepository.findItemById(id);
        
    }
    
    public List<OrderItems> getOrderItemsByUserEmail(String useremail) {
        return orderItemsRepository.findByUseremail(useremail);
    }
    
    public List<ItemItemOptionDto> getJoinedItemAndItemOptionByItemId(long id){
        return itemRepository.getJoin(id);
    }
    
    public List<ItemItemOptionDto> getJoinAll(){
        return itemRepository.getJoinAll();
    }
    
    
    @Transactional
	public void itemOrderSave(List<String> names, List<Integer> counts, List<Integer> prices, Long id,
			String userId) {
		
		int result = orderItemsRepository.deleteByUserEmail(userId);
		
		log.info("result : {}",result);
		
			for(int j=0; j < names.size(); j++) {
				orderItemsRepository.save(OrderItems.builder()
						.name(names.get(j))
						.cnt(Integer.valueOf(counts.get(j)))
						.price(Integer.valueOf(prices.get(j)))
						.itemid(id)
						.useremail(userId)
						.build());

			}

	}
	
    @Transactional
    public void cartSave(List<String> names, List<Integer> counts, List<Integer> prices, Long id,
            String userId, String main_image_path) {

        List<Cart> idLists = cartRepository.findByItemId(userId);

        log.info("idLists={}", idLists);
        log.info("idLists.size={}", idLists.size());

        // 비어 있으면 걍 save
        if (idLists.size() <= 0) {
            for (int j = 0; j < names.size(); j++) {
                cartRepository.save(Cart.builder()
                        .name(names.get(j))
                        .cnt(Integer.valueOf(counts.get(j)))
                        .price(Integer.valueOf(prices.get(j)))
                        .itemid(id)
                        .useremail(userId)
                        .main_image_path(main_image_path)
                        .build());
                log.info("비어있다");
            }
        } else {
            for (int j = 0; j < names.size(); j++) {
                boolean found = false; // 이름이 일치하는 항목을 찾았는지 여부를 나타내는 플래그
                for (int i = 0; i < idLists.size(); i++) {
                    Cart cart = idLists.get(i);

                    if (cart.getItemid() == id && cart.getName().equals(names.get(j))) {
                        // 아이템 아이디가 같고 이름이 같으면 cnt 변경
                        cartRepository.updateCnt(counts.get(j), names.get(j));
                        found = true; // 일치하는 항목 찾았음을 표시
                        break;
                    }
                }

                if (!found) {
                    // 일치하는 항목이 없으면 새로운 Cart 생성하여 저장
                    cartRepository.save(Cart.builder()
                            .name(names.get(j))
                            .cnt(Integer.valueOf(counts.get(j)))
                            .price(Integer.valueOf(prices.get(j)))
                            .itemid(id)
                            .useremail(userId)
                            .main_image_path(main_image_path)
                            .build());
                }
            }
        }
    }


	public List<Cart> read(String id) {
		
		return cartRepository.findByUserEmailOrderByDesc(id);
	}
	
	public List<OrderItems> read_order(String id) {
        
        return orderItemsRepository.findByUseremail(id);
    }


	public void deleteById(List<Long> delSelect) {
		for(Long id : delSelect) {
			cartRepository.deleteById(id);
		}
		
		
	}


}
