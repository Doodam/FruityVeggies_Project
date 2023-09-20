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
@Table(name = "ADDITIONAL_INGREDIENT")
@SequenceGenerator(name = "ADDITIONAL_INGREDIENT_SEQ_GEN", sequenceName = "ADDITIONAL_INGREDIENT_SEQ",allocationSize = 1)
public class AdditionalIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ADDITIONAL_INGREDIENT_SEQ_GEN")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;
    
    @Column
    private String name;
}
