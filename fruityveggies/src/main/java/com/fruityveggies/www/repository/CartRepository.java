package com.fruityveggies.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fruityveggies.www.dto.ItemItemOptionDto;

public interface CartRepository extends JpaRepository<Cart, Long> {
    
	@Query("SELECT c FROM Cart c WHERE c.useremail = :useremail ORDER BY c.id DESC")
	List<Cart> findByUserEmailOrderByDesc(@Param("useremail") String useremail);
	
	@Query("SELECT c FROM Cart c WHERE c.useremail = :useremail")
	List<Cart> findByItemId(@Param("useremail") String useremail);
    
	@Modifying
    @Query("UPDATE Cart c SET c.cnt = :cnt WHERE c.name = :name")
    int updateCnt(@Param("cnt") int cnt, @Param("name") String name);

    
}
