package com.fruityveggies.www.repository;

import java.util.ArrayList;
import java.util.List;

import com.fruityveggies.www.repository.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // Parameter로 모든 변수를 받는 생성자
@NoArgsConstructor  // Parameter가 없는 생성자
@Entity // Jpa에서 관리하는 클래스로 테이블과 자동 매핑
@Getter
@ToString
@Table(name = "ITEMOPTION")
@SequenceGenerator(name = "ITEMOPTION_SEQ_GEN", sequenceName = "ITEMOPTION_SEQ", allocationSize = 1)
public class ItemOption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEMOPTION_SEQ_GEN")
    private Long id;
    
    @Column(nullable = false)
    private String name; // 상품 이름
    
    @Column(nullable = false)
    private int price; // 
    
    @Column(nullable = false) 
    private int max_count;
    
    @Column(nullable = false)
    private Long optionId;
    
}
