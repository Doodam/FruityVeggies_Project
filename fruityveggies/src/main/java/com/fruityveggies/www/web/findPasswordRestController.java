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
public class findPasswordRestController {
	
	@Autowired
	private MemberService memberService;

	@PostMapping("/email/chpw") // 클라이언트 요청과 일치하도록 엔드포인트 수정
    public ResponseEntity<String> password(@RequestParam String password, @RequestParam String email) {
    	
    	log.info("password={}",password);
    	log.info("email={}",email);
    	
    	memberService.updatePassword(email, password);
    	
        
        return ResponseEntity.ok("success"); // 아이디를 찾지 못했을 경우 404 반환
        
    }
}
