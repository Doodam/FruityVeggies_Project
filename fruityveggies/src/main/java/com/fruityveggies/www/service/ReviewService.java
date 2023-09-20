package com.fruityveggies.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fruityveggies.www.dto.review.ReviewCreateDto;
import com.fruityveggies.www.repository.review.Review;
import com.fruityveggies.www.repository.review.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {
    
    // 생성자를 사용한 의존성 주입:
    private final ReviewRepository reviewRepository;
    
    // DB POSTS 테이블에 엔터티를 삽입(insert):
    public Review create(ReviewCreateDto dto) {
        log.info("create(dto={})", dto);
        
        // DTO를 Entity로 변환:
        Review entity = dto.toEntity();
        log.info("entity={}", entity);
        
        // DB 테이블에 저장(insert)
        reviewRepository.save(entity);
        log.info("entity={}", entity);
        
        return entity;
    }

    public List<Review> findByAll() {
        return reviewRepository.findAll();
        
    }
    
}