package com.fruityveggies.www.service.recipes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fruityveggies.www.dto.recipes.AdditionalIngredientDto;
import com.fruityveggies.www.repository.recipes.AdditionalIngredient;
import com.fruityveggies.www.repository.recipes.AdditionalIngredientRepository;
import com.fruityveggies.www.repository.recipes.Recipe;
import com.fruityveggies.www.repository.recipes.RecipeRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class AdditionalIngredientService {

    private final RecipeRepository recipeRepository;
    private final AdditionalIngredientRepository additionalIngredientRepository;
    
    @Transactional
    public AdditionalIngredient create(AdditionalIngredientDto dto) {
        log.info("AdditionalIngredient(dto={})", dto);
        
        //1.recipe 엔터티 검색
        Recipe recipe = recipeRepository.findById(dto.getRecipe_id()).orElseThrow();
        
        //2.AdditionalIngredientDto 객체를 AdditionalIngredient 엔터티 객체로 변환
        AdditionalIngredient entity = AdditionalIngredient.builder()
                .recipe(recipe)
                .name(dto.getName())
                .build();
        //3.db Additional_Ingredient 테이블에 insert
        log.info("entity={}",entity);
        
        additionalIngredientRepository.save(entity);
        
        return entity;
    }
    
    //디테일 id로 읽어오기
    public List<AdditionalIngredient> read(Long id) {
        log.info("read(id={})", id);
        
        Recipe recipe =  recipeRepository.findById(id).orElseThrow();
        
        return additionalIngredientRepository.findByRecipe(recipe);
    }
}
