package com.fruityveggies.www.dto.review;

import com.fruityveggies.www.repository.review.Review;

import lombok.Data;

@Data
public class ReviewCreateDto {
	private long id;
	private int star_score;
	private String content;
	private String writer;
	private Long itemId;
	
	
	// Dto를 엔터티 객체로 변환해서 리턴하는 메서드:
	public Review toEntity() {
		return Review.builder()
				.star_score(star_score)
				.content(content)
				.writer(writer)
				.item_id(itemId)
				.build();
	}
}
