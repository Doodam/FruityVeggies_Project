package com.fruityveggies.www.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class ChatMessageDto {

	  private String roomId;
	  private String writer;
	  private String message;
	  
}