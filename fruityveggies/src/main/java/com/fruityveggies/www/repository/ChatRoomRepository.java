package com.fruityveggies.www.repository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fruityveggies.www.dto.chat.ChatMessageDto;
import com.fruityveggies.www.dto.chat.ChatRoomDto;

import jakarta.annotation.PostConstruct;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoomDto> chatRoomDtoMap;
    
    @PostConstruct
    private void init(){
    	chatRoomDtoMap = new LinkedHashMap<>();
    }

    
    public List<ChatRoomDto> findAllRooms() {
        List<ChatRoomDto> result = new ArrayList<>(chatRoomDtoMap.values());

        Collections.reverse(result);
        
        return result;
    }
    

    public ChatRoomDto findRoomById(String id){
        return chatRoomDtoMap.get(id);
    }

    public ChatRoomDto createChatRoomDto(String name){
    	ChatRoomDto room = ChatRoomDto.create(name);
    	chatRoomDtoMap.put(room.getRoomId(), room);

        return room;
    }
    
    
    // 채팅방 전체 userlist 조회
    public ArrayList<String> getUserList(String roomId){
        ArrayList<String> list = new ArrayList<>();

        ChatRoomDto room = chatRoomDtoMap.get(roomId);

        // hashmap 을 for 문을 돌린 후
        // value 값만 뽑아내서 list 에 저장 후 reutrn
        room.getUserlist().forEach((key, value) -> list.add(value));
        return list;
    }
    
    
}
