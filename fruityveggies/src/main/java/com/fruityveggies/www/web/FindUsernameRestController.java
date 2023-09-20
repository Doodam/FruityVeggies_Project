package com.fruityveggies.www.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fruityveggies.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FindUsernameRestController {

    private final MemberService memberService;

    @Autowired
    public FindUsernameRestController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping("/findid/findbyemail") // 클라이언트 요청과 일치하도록 엔드포인트 수정
    public ResponseEntity<String> findUsernameByEmail(@RequestParam String email) {
    	
    	log.info("email={}",email);
    	
        String foundUsername = memberService.findUsernameByEmail(email);
        
        if (foundUsername != null) {
            return ResponseEntity.ok(foundUsername); // 아이디를 찾았을 경우에만 아이디 반환
        } else {
            return ResponseEntity.notFound().build(); // 아이디를 찾지 못했을 경우 404 반환
        }
    }
}







