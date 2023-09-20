package com.fruityveggies.www.web.chat;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fruityveggies.www.repository.ChatRoomRepository;
import com.fruityveggies.www.dto.chat.ChatRoomDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    
	private final ChatRoomRepository repository;

	// 채팅방 목록 조회
	@GetMapping("/chatRoomlist")
	public ModelAndView roomlist() {

		log.info("# All Chat Rooms");
		ModelAndView mv = new ModelAndView("chatRoomlist");		
		mv.addObject("list", repository.findAllRooms());

		return mv;
	}
	
	
	// 채팅방 개설
	@PostMapping("/chatRoom")
	public String create(@RequestParam String name, RedirectAttributes rttr, Principal principal) {

		log.info("# Create Chat Room , name: " + name + ", ");
		log.info("채팅방 개설 유저아이디: "+principal.getName());
		rttr.addFlashAttribute("roomName", repository.createChatRoomDto(name));
		rttr.addFlashAttribute("username", principal.getName());
		return "redirect:/chat/chatRoomlist";
	}

	// 채팅방 조회
		@GetMapping("/chatRoom")
		public void getRoom(String roomId, Model model, Principal principal) {

			log.info("# get Chat Room, roomID : " + roomId + ", 입장유저" + principal.getName());

			ChatRoomDto room = repository.findRoomById(roomId);
			model.addAttribute("room", room);
			model.addAttribute("username", principal.getName());
			
		}	
	
	// 채팅에 참여한 유저 리스트 반환
    @GetMapping("/userlist")
    @ResponseBody
    public ArrayList<String> userList(@RequestParam String roomId) {
    	log.info("# userlist : " + roomId);
        return repository.getUserList(roomId);
    }
    

}
