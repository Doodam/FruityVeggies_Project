package com.fruityveggies.www.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.fruityveggies.www.service.MemberService;


@Controller
public class findPasswordController {

    private final MemberService memberService;

    @Autowired
    public findPasswordController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/findpassword")
    public String showFindUsernamePage(Model model) {
        return "findid";
    }
    

      
}
