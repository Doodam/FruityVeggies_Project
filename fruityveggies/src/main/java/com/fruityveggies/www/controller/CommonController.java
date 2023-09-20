package com.fruityveggies.www.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fruityveggies.www.repository.OrderItems;
import com.fruityveggies.www.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommonController {
	
    @Autowired
    private ItemService itemService;
    
    /**
     * 로그인 페이지
     * @return
     */
    @GetMapping("/login")
    public String login(Model model) {
        log.info("login()");
        return "/login/login";
    }
    @PostMapping("/login")
    public String loginPost(Model model) {
        log.info("loginPost()");
        return "redirect:/login/login"; // 로그인 후 리다이렉트할 페이지 설정
    }
    
    @GetMapping("/login/mypage")
    @PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능
    public String myPage(Model model) {
        log.info("myPage()");
        return "/login/mypage";
    }
    @GetMapping("/login/orderhistory")
    public String orderhistory(Model model, Principal principal) {
        log.info("orderhistory");

        String userEmail = principal.getName();
        String[] emailParts = userEmail.split("=");
        for(String s: emailParts) {
            log.info("s : {}",s);
       
        }
        String emailVal = emailParts[2].split("}")[0].replace(" ", "");;
        
        log.info("emailVal : {}", emailVal);
        
        String userLocalPart = emailParts[1]; // 도메인을 제외한 부분
        
        log.info("userLocalPart = {}",userLocalPart);
        
        
        // 이미지 가지고 오기
        
        List<OrderItems> orderItems = itemService.getOrderItemsByUserEmail(emailVal);
        log.info("orderItems={}", orderItems);
        model.addAttribute("orderItems", orderItems);
        
        

        List<String> imgs = new ArrayList<>();
             
        for(int i=0; i<orderItems.size(); i++) {
            imgs.add(itemService.findItemById(orderItems.get(i).getItemid()).getMain_image_path() );
        }
        
        log.info("imgs={}",imgs);
        
        model.addAttribute("imgs",imgs);
        return "login/orderhistory";
    }
}


    /**
     * 메인 페이지
     * @return
     */
    /*
     * @GetMapping("/") public String main() { log.info("main"); return
     * "/main/main"; }
     */
    
