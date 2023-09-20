package com.fruityveggies.www.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemItemOptionDto {

    private Long id;
    private String name;
    private int price;
    private String main_image_path;
    private String detail_image_path;
    
}
