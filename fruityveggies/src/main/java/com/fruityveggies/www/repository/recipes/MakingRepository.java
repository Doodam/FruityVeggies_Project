package com.fruityveggies.www.repository.recipes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MakingRepository extends JpaRepository<Making,Long> {

    //recipe로 검색하기
    List<MakingRepository> findByRecipeOrderById(Recipe recipe);
    
    //디테일 불러오러면 Making 검색
    List<Making> findByRecipe(Recipe recipe);
}
