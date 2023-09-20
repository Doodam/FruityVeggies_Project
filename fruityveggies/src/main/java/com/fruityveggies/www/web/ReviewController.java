package com.fruityveggies.www.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fruityveggies.www.dto.review.ReviewCreateDto;
import com.fruityveggies.www.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/recipe")
public class ReviewController {
    
	private final ReviewService reviewService;
	
	
	@PostMapping("/review")
	public String create(ReviewCreateDto dto, @RequestParam(value = "rating", required = false) List<Integer> ratings) {
		log.info("create(dto={}) REIVEW", dto);
		
		if (ratings != null) {
            for (Integer rating : ratings) {
                log.info("별점: {}", rating);
            }
        }
		log.info("rating : {}", ratings.get(ratings.size() - 1));
		dto.setStar_score(ratings.get(ratings.size() - 1));
		reviewService.create(dto);
		
		return "redirect:/main/mission";
	}
	
	@GetMapping("/review")
    public void review(@RequestParam(value = "id") Long id) {
        log.info("review() GET");
        log.info("id={}", id);
            
        
    }
	
	
}
