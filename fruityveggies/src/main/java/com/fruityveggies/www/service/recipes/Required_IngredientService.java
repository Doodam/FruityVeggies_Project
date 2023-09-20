package com.fruityveggies.www.service.recipes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fruityveggies.www.dto.recipes.Required_IngredientDto;
import com.fruityveggies.www.repository.recipes.Recipe;
import com.fruityveggies.www.repository.recipes.RecipeRepository;
import com.fruityveggies.www.repository.recipes.Required_Ingredient;
import com.fruityveggies.www.repository.recipes.Required_IngredientRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class Required_IngredientService {

    private final RecipeRepository recipeRepository;
    private final Required_IngredientRepository required_IngredientRepository;
    
    @Transactional
    public Required_Ingredient create(Required_IngredientDto dto) {
        log.info("required_ingredinet(dto={})", dto);
        
        //1.recipe 엔터티 검색
        Recipe recipe = recipeRepository.findById(dto.getRecipe_id()).orElseThrow();
        log.info("recipe={}",recipe);
        //2.required_ingredientDto 객체를 required_ingredient 엔터티 객체로 변환.
        Required_Ingredient entity = Required_Ingredient.builder()
                .recipe(recipe)
                .name(dto.getName())
                .amount(dto.getAmount())
                .build();
        //3. db required_ingredient 테이블에 insert
        
        log.info("entity={}",entity);
        
        required_IngredientRepository.save(entity);
        
        return entity;
                
        
    }

    //디테일 id로 읽어오기
    public List<Required_Ingredient> read(Long id) {
        log.info("read(id={})", id);//22
        
        Recipe recipe =  recipeRepository.findById(id).orElseThrow();
        
        return required_IngredientRepository.findByRecipe(recipe);
    }
}
