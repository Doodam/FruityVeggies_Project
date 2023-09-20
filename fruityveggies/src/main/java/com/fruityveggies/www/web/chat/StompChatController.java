package com.fruityveggies.www.web.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.fruityveggies.www.dto.chat.ChatMessageDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달 

    @MessageMapping("/chat/join")	
    public void join(ChatMessageDto dto){
    	log.info("# 채팅 입장"+dto);
    	
        dto.setMessage(dto.getWriter() + "님이 채팅방에 참여하였습니다.");
        
        template.convertAndSend("/sub/chat/room/" + dto.getRoomId(), dto);        
    }
    
    @MessageMapping("/chat/exit")	
    public void exit(ChatMessageDto dto){
    	log.info("# 채팅 퇴장"+dto);
    	
    	dto.setMessage(dto.getWriter() + "님이 퇴장하셨습니다.");
    	
    	template.convertAndSend("/sub/chat/room/" + dto.getRoomId(), dto);   
    	
    }
    
    @MessageMapping("/chat/message")
    public void message(ChatMessageDto dto){
    	log.info("# 채팅 전송 메세지 ");
        template.convertAndSend("/sub/chat/room/" + dto.getRoomId(), dto);
    }
    
    
    
    
}