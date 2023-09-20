package com.fruityveggies.www.dto;

import lombok.Data;

@Data
public class MemberSignUpDto {
    
    private String username;
    private String password;
    private String email;

}
