package com.fruityveggies.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fruityveggies.www.dto.ItemItemOptionDto;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findItemById(Long id);
    
 // ItemOption과 Item 엔티티를 조인하는 쿼리 작성
    @Query("SELECT new com.fruityveggies.www.dto.ItemItemOptionDto(a.id, b.name, b.price, a.main_image_path, a.detail_image_path) FROM Item a JOIN ItemOption b on a.id = b.optionId WHERE a.id = :itemId")
    List<ItemItemOptionDto> getJoin(@Param("itemId") Long id);
    
    @Query("SELECT new com.fruityveggies.www.dto.ItemItemOptionDto(a.id, b.name, b.price, a.main_image_path, a.detail_image_path) FROM Item a JOIN ItemOption b on a.id = b.optionId")
    List<ItemItemOptionDto> getJoinAll();

    
}
