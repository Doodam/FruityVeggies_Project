package com.fruityveggies.www.service.recipes;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fruityveggies.www.dto.RecipeSearchDto;
import com.fruityveggies.www.dto.recipes.RecipeDto;
import com.fruityveggies.www.repository.recipes.Recipe;
import com.fruityveggies.www.repository.recipes.RecipeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecipeService {

    //생성자 사용한 의존성 주입
    private final RecipeRepository recipeRepository;
    
    
    //DB에 RECIPES 테이블에 엔터티를 삽입(insert)
    public Recipe create(RecipeDto dto) {
        log.info("recipe(dto={})", dto);
        
        //DTO를 Entity로 변환
        Recipe entity = dto.toEntity();
        
        //DB recipe테이블에 저장
        recipeRepository.save(entity);
        
        log.info("entity={}", entity);
        return entity;
        
    }


    public List<Recipe> getRecipeList() {
        List<Recipe> recipeList = recipeRepository.findAll();
        return recipeList;
    }

    
    //디테일 id로 읽어오기
    public Recipe read(Long id) {
        log.info("read(id={})",id);
        
        return recipeRepository.findById(id).orElseThrow();
    }
    
    @Transactional(readOnly = true)
    public List<Recipe> search(RecipeSearchDto dto) {
        log.info("search(dto={})", dto);
        
        List<Recipe> list = recipeRepository.findByTitleContainsIgnoreCaseOrderByIdDesc(dto.getKeyword());
        
        
        
        return list;
    }
    
    
}
