package com.fruityveggies.www.dto.recipes;

import com.fruityveggies.www.repository.recipes.Required_Ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Required_IngredientDto {

    private long recipe_id;
    private String name;
    private String amount;
    

}
