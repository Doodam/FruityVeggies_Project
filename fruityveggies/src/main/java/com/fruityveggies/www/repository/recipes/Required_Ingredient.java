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
@Getter
@AllArgsConstructor
@Builder
@ToString(exclude = {"recipe"})
@Entity
@Table(name = "REQUIRED_INGREDIENT")
@SequenceGenerator(name = "REQUIRED_INGREDIENT_SEQ_GEN", sequenceName = "REQUIRED_INGREDIENT_SEQ", allocationSize = 1)
public class Required_Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQUIRED_INGREDIENT_SEQ_GEN")
    private Long id; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;
    
    @Column
    private String name;
    
    @Column
    private String amount;
    
}
