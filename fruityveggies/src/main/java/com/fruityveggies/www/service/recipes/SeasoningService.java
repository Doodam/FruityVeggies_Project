package com.fruityveggies.www.service.recipes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fruityveggies.www.dto.recipes.SeasoningDto;
import com.fruityveggies.www.repository.recipes.Recipe;
import com.fruityveggies.www.repository.recipes.RecipeRepository;
import com.fruityveggies.www.repository.recipes.Seasoning;
import com.fruityveggies.www.repository.recipes.SeasoningRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class SeasoningService {

    private final RecipeRepository recipeRepository;
    private final SeasoningRepository seasoningRepository;
    
    @Transactional
    public Seasoning create(SeasoningDto dto) {
        log.info("Seasoning(dto={})",dto);
        
        //1.recipe 엔터티 검색
        Recipe recipe = recipeRepository.findById(dto.getRecipe_id()).orElseThrow();
        
        //2.SeasoningDto 객체를 Seasoning 엔터티 객체로 변환
        Seasoning entity = Seasoning.builder()
                .recipe(recipe)
                .name(dto.getName())
                .build();
        //3.db Seasoning 테이블에 insert
        log.info("entity={}",entity);
        
        seasoningRepository.save(entity);
        
        return entity;
    }
    
    //디테일 id로 읽어오기
    public List<Seasoning> read(Long id) {
        log.info("read(id={})", id);//22
        
        Recipe recipe =  recipeRepository.findById(id).orElseThrow();
        
        return seasoningRepository.findByRecipe(recipe);
    }
}
