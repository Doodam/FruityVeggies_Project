package com.fruityveggies.www.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemOrderDto {
	
	private List<String> name;
	private List<Integer> cnt;
	private List<Integer> price; 
}
