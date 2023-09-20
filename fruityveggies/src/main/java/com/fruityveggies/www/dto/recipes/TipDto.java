package com.fruityveggies.www.dto.recipes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipDto {

    private long recipe_id;
    private String description;
    //private String image;
}
