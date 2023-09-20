package com.fruityveggies.www.repository.rescue;

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
@Entity // JPA 엔터티 클래스 - 데이터베이스 테이블과 매핑되는 클래스
@Table(name = "REGULARDELIVERY") 
@SequenceGenerator(name = "REGULARDELIVERY_SEQ_GEN", sequenceName = "REGULARDELIVERY_SEQ", allocationSize = 1)
public class RescueOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGULARDELIVERY_SEQ_GEN")
    private long id;
    
    @Column(nullable = false)
    private String boxsize;
    
    @Column(nullable = false)
    private String weeksize;
    
    @Column(nullable = false)
    private long box_price;
    
    @Column(nullable = false)
    private String dislikeid_1;
    
    private String dislikeid_2;
    private String dislikeid_3;
    
    @Column(nullable = false)
    private String recipient;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private String address_detail;
    
    @Column(nullable = false)
    private String userid;
    
   // @Column(nullable = false)
   // private String dislike;
       

}