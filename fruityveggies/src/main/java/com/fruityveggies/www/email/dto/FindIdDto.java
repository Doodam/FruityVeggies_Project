package com.fruityveggies.www.email.dto;

public class FindIdDto {
    private String email;

    public FindIdDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
