package com.fruityveggies.www.repository.review;

import org.springframework.boot.web.servlet.ServletComponentScan;

import com.fruityveggies.www.repository.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity
@Table(name = "REVIEW")
@SequenceGenerator(name = "REVIEW_SEQ_GEN", sequenceName = "REVIEW_SEQ", allocationSize = 1)
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQ_GEN")
	private Long id;
	
	@Column(nullable = false) // Not Null 제약조건
	private int star_score;

	@Column(nullable = false) // Not Null 제약조건
	private String content;
	
	@Column(nullable = false) // Not Null 제약조건
	private String writer;
	
	@Column(nullable = false) // Not Null 제약조건
	private Long item_id;
	
	
	
	
	
	
	
	
	
}
