package com.fruityveggies.www.web.recipes;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fruityveggies.www.dto.RecipeSearchDto;
import com.fruityveggies.www.dto.recipes.AdditionalIngredientDto;
import com.fruityveggies.www.dto.recipes.MakingDto;
import com.fruityveggies.www.dto.recipes.RecipeDto;
import com.fruityveggies.www.dto.recipes.RecipeUploadDto;
import com.fruityveggies.www.dto.recipes.Required_IngredientDto;
import com.fruityveggies.www.dto.recipes.SeasoningDto;
import com.fruityveggies.www.dto.recipes.TipDto;
import com.fruityveggies.www.repository.recipes.AdditionalIngredient;
import com.fruityveggies.www.repository.recipes.Making;
import com.fruityveggies.www.repository.recipes.Recipe;
import com.fruityveggies.www.repository.recipes.Required_Ingredient;
import com.fruityveggies.www.repository.recipes.Seasoning;
import com.fruityveggies.www.repository.recipes.Tip;
import com.fruityveggies.www.service.recipes.AdditionalIngredientService;
import com.fruityveggies.www.service.recipes.MakingService;
import com.fruityveggies.www.service.recipes.RecipeService;
import com.fruityveggies.www.service.recipes.Required_IngredientService;
import com.fruityveggies.www.service.recipes.SeasoningService;
import com.fruityveggies.www.service.recipes.TipService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/recipe")
public class RecipeController {
    
    private final RecipeService recipeService;
    private final Required_IngredientService required_IngredientService;
    private final AdditionalIngredientService additionalIngredientService;
    private final SeasoningService seasoningService;
    private final MakingService makingService;
    private final TipService tipService;
    
    @GetMapping("/detail")
    public String read(@RequestParam("id") Long id, Model model) {
        log.info("read(id={})",id);

        //RECIPES 테이블에서 id에 해당하는 레시피를 검색
        Recipe recipe = recipeService.read(id);
        
        //REQUIRED_INGREDIENT 테이블에에서 id에 해당하는 레시피를 검색
        List<Required_Ingredient> required_ingredient = required_IngredientService.read(id);
        
        //AdditionalIngredient 테이블에서 id에 해당하는 레시피 검색
        List<AdditionalIngredient> additionalIngredient = additionalIngredientService.read(id);
        
        //Seasoning 테이블에서 id에 해당하는 레시피를 검색
        List<Seasoning> seasoning = seasoningService.read(id);

        //Making 테이블에서 id에 해당하는 레시피 검색
        List<Making> making = makingService.read(id);
        
        //Tip 테이블에서 id에 해당하는 레시피 검색
        List<Tip> tip = tipService.read(id);
        
        log.info("recipe={}",recipe);
        log.info("required_ingredient={}",required_ingredient);
        //결과를 model에 저장 -> 뷰로 전달
        model.addAttribute("recipe", recipe);
        model.addAttribute("required_ingredient", required_ingredient);
        model.addAttribute("additionalIngredient", additionalIngredient);
        model.addAttribute("seasoning", seasoning);
        model.addAttribute("making", making);
        model.addAttribute("tip", tip);
        
        return "/recipe/detail";
    }
    
    
    
    
    @GetMapping("/upload")
    public String recipe(Model model) {
        log.info("upload()");
        
     // Get recipe list data from RecipeService
        List<Recipe> recipeList = recipeService.getRecipeList();
        
        // Add recipe list data to the Model
        model.addAttribute("recipeList", recipeList);
        
        return "/recipe/upload"; // View의 이름.
    }
    

    @PostMapping("/upload")
    public String create(RecipeUploadDto dto) throws IllegalStateException, IOException {
        log.info("upload(dto={}) POST", dto);
        
     // 파일 업로드 처리
        String fileName = null;
        MultipartFile uploadFile = dto.getUploadFile();
        if (!uploadFile.isEmpty()) {
            String originalFileName = uploadFile.getOriginalFilename();
            String ext = FilenameUtils.getExtension(originalFileName); // 확장자 구하기
            UUID uuid = UUID.randomUUID(); // UUID 구하기
            fileName = uuid + "." + ext;
            uploadFile.transferTo(new File("C:\\upload\\" + fileName));
        }
        dto.setFilename(fileName);
        
      //form에서 submit(제출)된 내용을 DB 테이블에 insert
        RecipeDto recipeDto = dto.toRecipeDto();
        log.info("recipeDto = {}", recipeDto);
        Recipe recipe = recipeService.create(recipeDto);
        log.info("recipe id = {}", recipe.getId());
        
        //필수재료 리스트 넘기는
        List<Required_IngredientDto> reqIngredList = 
                dto.toRequiredIngredientList(recipe.getId());
        for (Required_IngredientDto reqIngred : reqIngredList) {
            required_IngredientService.create(reqIngred);
        }
        
        //부재료 리스트 넘기는
        List<AdditionalIngredientDto> addIngreList =
                dto.toAdditionalIngredientList(recipe.getId());
        for(AdditionalIngredientDto addIngred : addIngreList) {
            additionalIngredientService.create(addIngred);
        }
        
        //양념 리스트 넘기는
        List<SeasoningDto> seasoningList = 
                dto.toSeasoningList(recipe.getId());
        for(SeasoningDto seasoning : seasoningList) {
            seasoningService.create(seasoning);
        }
        
        //만드는 방법 리스트 넘기는
        List<MakingDto> makingList = 
                dto.toMakingList(recipe.getId());
        for(MakingDto making : makingList) {
            makingService.create(making);
        }
        
        //Tip 리스트 넘기는
        List<TipDto> tipList = 
                dto.toTipList(recipe.getId());
        for(TipDto tip : tipList) {
            tipService.create(tip);
        }
        
        
     // DB 테이블 insert 후 레시피 목록 페이지로 redirect 이동.
        return "redirect:/main/recipes";
    }
    
    @GetMapping("/search")
    public String search(RecipeSearchDto dto, Model model) {
        log.info("search(dto={})", dto);
        
        // postService의 검색 기능 호출:
        List<Recipe> list = recipeService.search(dto);
        log.info("list(dto={})", list);
        
        // 검색 결과를 Model에 저장해서 뷰로 전달:
        model.addAttribute("recipes", list);
        
        return "/main/recipes";
    }
    
    
    
}
