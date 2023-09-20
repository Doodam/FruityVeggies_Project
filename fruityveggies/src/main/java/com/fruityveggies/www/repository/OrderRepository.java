package com.fruityveggies.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
//    @Query("SELECT c FROM Order c WHERE c.orderitemid = :orderitemid")
//    List<Order> findByOrderItemId(@Param("orderitemid") Long orderitemid);
    
    List<Order> findByOrderItemId(Long orderItemId);
    
    
}
