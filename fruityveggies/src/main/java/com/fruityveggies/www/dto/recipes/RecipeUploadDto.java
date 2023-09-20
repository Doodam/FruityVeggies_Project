package com.fruityveggies.www.dto.recipes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeUploadDto {

    // TODO: 파일 업로드
    private String filename;
    
    private String title; // 레시피 이름
    private String content; // 레시피 설명
    private String cooking_time; // 예상 소요 시간
    private String category_main; // 주 카테고리
    private String category_sub; // 서브 카테고리
    private List<String> hashtag;// 해시태그
    // TODO: 기준량
    //필수재료
    private List<String> reqIngredNames;
    private List<String> reqIngredAmounts;
    //부재료, ...
    private List<String> addIngredNames;
    //시즈닝
    private List<String> seasoningNmaes;
    //만드는 방법
    private List<String> makingDes;
    //private List<String> makingImage;
    
    //Tip
    private List<String> TipDes;
    
 // MultipartFile 필드 추가
    private MultipartFile uploadFile;

  //DTO를 엔터티 객체로 변환해서 리턴하는 메서드
    public RecipeDto toRecipeDto() {
        return RecipeDto.builder()
                .title(title)
                .content(content)
                .cooking_time(cooking_time)
                .hashtag(hashtag)
                .category_main(category_main)
                .category_sub(category_sub)
                .filename(filename)
                .build();
    }
    
    //주재료
    public List<Required_IngredientDto> toRequiredIngredientList(Long recipeId) {
        List<Required_IngredientDto> list = new ArrayList<>();
        
        for (int i = 0; i < reqIngredNames.size(); i++) {
            Required_IngredientDto dto = Required_IngredientDto.builder()
                    .recipe_id(recipeId)
                    .name(reqIngredNames.get(i))
                    .amount(reqIngredAmounts.get(i))
                    .build();
            list.add(dto);
        }
        
        return list;
    }
    
    //부재료
    public List<AdditionalIngredientDto> toAdditionalIngredientList(Long recipeId){
        List<AdditionalIngredientDto> list = new ArrayList<>();
        
        for(int i = 0; i < addIngredNames.size(); i++ ) {
            AdditionalIngredientDto dto = AdditionalIngredientDto.builder()
                    .recipe_id(recipeId)
                    .name(addIngredNames.get(i))
                    .build();
            list.add(dto);
        }
        return list;
    }
    
    //양념
    public List<SeasoningDto> toSeasoningList(Long recipeId){
        List<SeasoningDto> list = new ArrayList<>();
        
        for(int i = 0; i < seasoningNmaes.size(); i++) {
            SeasoningDto dto = SeasoningDto.builder()
                    .recipe_id(recipeId)
                    .name(seasoningNmaes.get(i))
                    .build();
            list.add(dto);
        }
        return list;
    }
    
    // 만드는 방법
    public List<MakingDto> toMakingList(Long recipeId){
        List<MakingDto> list = new ArrayList<>();
        
        for(int i = 0; i < makingDes.size(); i++) {
            MakingDto dto = MakingDto.builder()
                    .recipe_id(recipeId)
                    .description(makingDes.get(i))
                    //.image(makingImage.get(i))
                    .build();
            list.add(dto);
        }
        return list;
    }
    
    //Tip
    public List<TipDto> toTipList(Long recipeId){
        List<TipDto> list = new ArrayList<>();
        
        for(int i = 0; i < TipDes.size(); i++ ) {
            TipDto dto = TipDto.builder()
                    .recipe_id(recipeId)
                    .description(makingDes.get(i))
                    //.image(makingImage.get(i))
                    .build();
            list.add(dto);
        }
        return list;
    }
    
}
