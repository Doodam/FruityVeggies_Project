package com.fruityveggies.www.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.web.socket.WebSocketSession;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {

    private String roomId;
    private String name;
    //WebSocketSession은 Spring에서 WebSocket Connection이 맺어진 세션
    private Set<WebSocketSession> sessions = new HashSet<>();

    
    public static ChatRoomDto create(String name){
    	ChatRoomDto room = new ChatRoomDto();

        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        return room;
    }
    
    private HashMap<String, String> userlist = new HashMap<String, String>();
    

}