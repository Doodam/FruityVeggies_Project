package com.fruityveggies.www.web.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fruityveggies.www.dto.MemberSignUpDto;
import com.fruityveggies.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
    
    private final MemberService memberService;
    
    @GetMapping("/login")
    public void login(Model model) {
        log.info("login()");
        
    }
    
    @GetMapping("/loginfail")
    public void loginfail(Model model) {
        log.info("loginfail()");
        
    }
    
    
    @GetMapping("/signup")
    public void signUp() {
        log.info("signUp() GET");
    }
    
    @PostMapping("/signup")
    public String signUp(MemberSignUpDto dto) {
    	
    	
    	
        log.info("signUp(dto={}) POST", dto);
        
        // 회원 가입 서비스 호출
        Long id = memberService.registerMember(dto);
        log.info("회원 가입 id = {}", id);
        
        // 회원 가입 이후에 로그인 화면으로 이동(redirect):
        return "redirect:/login";
    }
    
    @GetMapping("/findid")
    public void findid() {
        log.info("findid() GET");
    }
    
    @GetMapping("/findpassword")
    public void findpassword() {
        log.info("findid() GET");
    }
}
