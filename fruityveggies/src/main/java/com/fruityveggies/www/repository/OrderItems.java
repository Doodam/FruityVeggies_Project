package com.fruityveggies.www.repository;

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
@Getter
@Builder
@ToString
@Entity
@Table(name="ORDERITEM")
@SequenceGenerator(name = "ORDERITEM_SEQ_GEN", sequenceName = "ORDERITEM_SEQ", allocationSize = 1)
public class OrderItems {
	@Id // primary key 제약조건
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERITEM_SEQ_GEN")
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int cnt;
	
	@Column(nullable = false)
	private int price;
	
	@Column(nullable = false)
	private Long itemid ;
	
	@Column(nullable = false)
	private String useremail;


}

