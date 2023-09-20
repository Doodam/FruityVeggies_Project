package com.fruityveggies.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fruityveggies.www.dto.ItemItemOptionDto;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    
	@Query("SELECT c FROM OrderItems c WHERE c.itemid = :itemid")
	List<OrderItems> findByItemId(@Param("itemid") Long itemid);
    
	@Modifying
	@Query("UPDATE OrderItems c SET c.cnt = :cnt WHERE c.name = :name")
	void updateCnt(@Param("cnt") int cnt,@Param("name") String name);

	@Modifying
    @Query("DELETE FROM OrderItems c where c.useremail = :usermail")
    int deleteByUserEmail(@Param("usermail") String usermail);
	
	// 로그인한 사용자의 주문 내역 가져오기
	@Query("SELECT c FROM OrderItems c WHERE c.useremail = :useremail")
    List<OrderItems> findByUseremail(@Param("useremail") String useremail);
	
}
