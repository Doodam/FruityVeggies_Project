package com.fruityveggies.www.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fruityveggies.www.dto.ItemItemOptionDto;
import com.fruityveggies.www.repository.Item;
import com.fruityveggies.www.repository.recipes.Recipe;
import com.fruityveggies.www.repository.review.Review;
import com.fruityveggies.www.service.ItemService;
import com.fruityveggies.www.service.recipes.RecipeService;
import com.fruityveggies.www.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class HomeController {
    
	private final RecipeService recipeService;
	
    @Autowired
    private ItemService itemService;
    
//    @PreAuthorize("hassRole('USER')") // 페이지 접근 이전에 인증(권한, 로그인) 여부를 확인.
    
    @Autowired
    private ReviewService reviewService;
    
    @GetMapping("/")
    public String home(Model model) {
        log.info("home()");
        
        return "/main/index"; // View의 이름.
    }
    
    @GetMapping("/main/mission")
    public String mission(Model model) {
        log.info("mission()");
        
        return "/main/mission"; // View의 이름.x
    }
    
    @GetMapping("/main/regulardelivery")
    public String regulardelivery(Model model) {
        log.info("regulardelivery()");
        
        List<Review> lists = reviewService.findByAll();
        
        log.info("lists={}",lists);
        
        model.addAttribute("lists", lists);
        
        return "/main/regulardelivery"; // View의 이름.
    }
    
    @GetMapping("/main/market")
    public String market(Model model) {
        log.info("market()");
        
        List<Item> items = itemService.findByAll();
        log.info("item={}",items);
        model.addAttribute("items", items);
        
        List<ItemItemOptionDto> joinItems = itemService.getJoinAll();
        List<ItemItemOptionDto> uniqueItems = removeDuplicates(joinItems);
        
        log.info("uniqueItems={}",uniqueItems);
        model.addAttribute("uniqueItems", uniqueItems);
        
        return "/main/market"; // View의 이름.
    }
    
    public static List<ItemItemOptionDto> removeDuplicates(List<ItemItemOptionDto> items) {
        Map<Long, ItemItemOptionDto> uniqueItemsMap = new HashMap<>();
        for (ItemItemOptionDto item : items) {
            if (!uniqueItemsMap.containsKey(item.getId())) {
                uniqueItemsMap.put(item.getId(), item);
            }
        }
        return new ArrayList<>(uniqueItemsMap.values());
    }
    
    @GetMapping("/main/recipes")
    public String recipes(Model model) {
        log.info("recipes()");
        
        List<Recipe> list = recipeService.getRecipeList();
        
        log.info("list={}", list);
        
        model.addAttribute("recipes", list);
        
        
        return "/main/recipes"; // View의 이름.
    }

}
