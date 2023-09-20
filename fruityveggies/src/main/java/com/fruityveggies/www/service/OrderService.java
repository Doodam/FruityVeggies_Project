package com.fruityveggies.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fruityveggies.www.email.dto.FreshmarketOrderDto;
import com.fruityveggies.www.repository.Order;
import com.fruityveggies.www.repository.OrderItemsRepository;
import com.fruityveggies.www.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    
    
    public List<Order> getOrdersByOrderItemId(Long orderItemId) {
        log.info("getOrdersByOrderItemId(orderItemId={}",orderItemId );
        return orderRepository.findByOrderItemId(orderItemId);
    }
    
    
    
    public Order readOrder(Long id) {
        log.info("readOrder(id={})", id);
        
        return orderRepository.findById(id).orElseThrow();
    }
    
    
    public Order create(FreshmarketOrderDto dto) {
        log.info("orderCreate(dto={})", dto);
        
        Order entity = dto.toEntity();
        log.info("orderEntity={}", entity);
        
        orderRepository.save(entity);
        log.info("orderEntity={}", entity);
        
        return entity;
    }
    
//    public OrderItem orderRead(Long id) {
//        log.info("orderRead(id={})", id);
//        
//        return orderItemRepository.
//    }
    
}
