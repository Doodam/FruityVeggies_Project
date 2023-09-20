package com.fruityveggies.www.dto.recipes;



import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fruityveggies.www.repository.recipes.Recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDto {


    private Long id;
    private String image;
    private String title;
    private String content;
    private String cooking_time;
    private String category_main;
    private String category_sub;
    private List<String> hashtag;
    private String reference_amount;
    private String filename;
    
    // MultipartFile 필드 추가
    //private MultipartFile uploadFile;
    
    //DTO를 엔터티 객체로 변환해서 리턴하는 메서드
    public Recipe toEntity() {
        return Recipe.builder()
                .id(id)
                .image(image)
                .title(title)
                .content(content)
                .cooking_time(cooking_time)
                .category_main(category_main)
                .category_sub(category_sub)
                .hashtag(hashtag)
                .reference_amount(reference_amount)
                .filename(filename)
                .build();
    }
}
