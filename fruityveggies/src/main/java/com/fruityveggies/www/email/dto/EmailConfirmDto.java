package com.fruityveggies.www.email.dto;

import lombok.Data;

@Data
public class EmailConfirmDto {
	private String verificationCode;
	private String confirmMessage;
}
