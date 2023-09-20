package com.fruityveggies.www.repository.recipes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalIngredientRepository extends JpaRepository<AdditionalIngredient, Long>{

    //recipe로 검색
    List<AdditionalIngredientRepository> findByRecipeOrderById(Recipe recipe);
    
  //디테일 불러오려면 AdditionalIngredient 검색
    List<AdditionalIngredient> findByRecipe(Recipe recipe);
}
