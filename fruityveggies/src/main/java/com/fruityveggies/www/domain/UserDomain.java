package com.fruityveggies.www.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class UserDomain {
    @Id
    @Column(name="email", columnDefinition="VARCHAR(100)", nullable=true)
    private String email;
    
    @Column(name="oauth_type", columnDefinition="VARCHAR(50)")
    private String oauthType;
       
    

}