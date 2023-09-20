package com.fruityveggies.www.repository.recipes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TipRepository extends JpaRepository<Tip, Long>{

    //recipe로 검색하기
    List<TipRepository> findByRecipeOrderById(Recipe recipe);
    
  //디테일 불러오려면 Tip 검색
    List<Tip> findByRecipe(Recipe recipe);
}
