//package com.fruityveggies.www.repository;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Table(name = "CARTITEM")
//public class CartItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    
//    @Column(nullable = false)
//    private Long cart_id; // 장바구니 id    
//    
//    @Column(nullable = false)
//    private Long itemOption_id; // 장바구니에 담긴 상품 id    
//
//    // 카트에 담긴 상품 개수
//    private int count; 
//    
//    public void addCount(int count) {
//        this.count += count;
//    }
//}
