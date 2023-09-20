package com.fruityveggies.www.service.recipes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fruityveggies.www.dto.recipes.TipDto;
import com.fruityveggies.www.repository.recipes.MakingRepository;
import com.fruityveggies.www.repository.recipes.Recipe;
import com.fruityveggies.www.repository.recipes.RecipeRepository;
import com.fruityveggies.www.repository.recipes.Tip;
import com.fruityveggies.www.repository.recipes.TipRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class TipService {

    private final RecipeRepository recipeRepository;
    private final TipRepository tipRepository;
    
    @Transactional
    public Tip create(TipDto dto) {
        log.info("Tip(dto={})", dto);
        
      //1.recipe 엔터티 검색
        Recipe recipe = recipeRepository.findById(dto.getRecipe_id()).orElseThrow();
        log.info("recipe={}",recipe);
        
      //2.TipDto 객체를 tip 엔터티 객체로 변환.  
        Tip entity = Tip.builder()
                .recipe(recipe)
                .description(dto.getDescription())
                //.image(dto.getImage())
                .build();
        
     //3. db Tip 테이블에 insert
        log.info("entity={}",entity);
        
        tipRepository.save(entity);
        
        return entity;
    }
    
    ////디테일 id로 읽어오기
    public List<Tip> read(Long id) {
        log.info("read(id={})", id);
        
        Recipe recipe =  recipeRepository.findById(id).orElseThrow();
        
        return tipRepository.findByRecipe(recipe);
    }
}
