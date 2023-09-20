package com.fruityveggies.www.repository.recipes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface Required_IngredientRepository  extends JpaRepository<Required_Ingredient, Long> {

    //recipe로 검색하기:
    List<Required_IngredientRepository> findByRecipeOrderById(Recipe recipe);

    //디테일 불러오려면 Required_Ingredient 검색
    List<Required_Ingredient> findByRecipe(Recipe recipe);

    
    

    
}
