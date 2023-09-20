package com.fruityveggies.www.service.recipes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fruityveggies.www.dto.recipes.MakingDto;
import com.fruityveggies.www.repository.recipes.Making;
import com.fruityveggies.www.repository.recipes.MakingRepository;
import com.fruityveggies.www.repository.recipes.Recipe;
import com.fruityveggies.www.repository.recipes.RecipeRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class MakingService {

    private final RecipeRepository recipeRepository;
    private final MakingRepository makingRepository;
    
    @Transactional
    public Making create(MakingDto dto) {
        log.info("Making(dto={}",dto);
        
        //1.recipe 엔터티 검색
        Recipe recipe = recipeRepository.findById(dto.getRecipe_id()).orElseThrow();
        log.info("recipe={}",recipe);
        
        //2.MakingDto 객체를 making 엔터티 객체로 변환.
        Making entity = Making.builder()
                .recipe(recipe)
                .description(dto.getDescription())
                //.image(dto.getImage())
                .build();
        //3. db Making 테이블에 insert
        log.info("entity={}",entity);
        
        makingRepository.save(entity);
        
        return entity;
    }
    
    //디테일 id로 읽어오기
    public List<Making> read(Long id) {
        log.info("read(id={})", id);
        Recipe recipe =  recipeRepository.findById(id).orElseThrow();
        return makingRepository.findByRecipe(recipe);
    }
}
