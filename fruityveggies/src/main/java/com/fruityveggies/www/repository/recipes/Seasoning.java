package com.fruityveggies.www.repository.recipes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@ToString(exclude = {"recipe"})
@Entity
@Table(name = "Seasoning")
@SequenceGenerator(name = "Seasoning_SEQ_GEN", sequenceName = "Seasoning_SEQ",allocationSize = 1)
public class Seasoning {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Seasoning_SEQ_GEN")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;
   
    @Column
    private String name;
    
}
