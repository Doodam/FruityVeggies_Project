//package com.fruityveggies.www.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface CartFruityRepository extends JpaRepository<CartFruity, Long> {
//    
//    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
//    CartFruity findByUserId(Long userId);
//
//}
