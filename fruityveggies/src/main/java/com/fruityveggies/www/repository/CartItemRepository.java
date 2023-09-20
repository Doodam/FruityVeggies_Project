//package com.fruityveggies.www.repository;
//
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//
//@Repository
//public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//    
//    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.itemOption.id = :itemOptionId")
//    CartItem findByCartIdAndItemOptionId(@Param("cartId") Long cartId, @Param("itemOptionId") Long itemOptionId);
////    CartItem findByCartIdAndItemOptionId(Long cartId, Long itemOptionId);
//
//
//
//    
//}
