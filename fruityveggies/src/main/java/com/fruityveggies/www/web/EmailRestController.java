package com.fruityveggies.www.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fruityveggies.www.email.dto.EmailConfirmDto;
import com.fruityveggies.www.email.dto.EmailVerifyDto;
import com.fruityveggies.www.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailRestController {
    
    private final EmailService emailService;
    
    private static String emailCode = "";

    @PostMapping("/verify")
    public ResponseEntity<EmailVerifyDto> emailVerify(@RequestBody EmailVerifyDto dto) throws Exception {
        log.info("dto={}", dto);

        String confirm = emailService.sendSimpleMessage(dto.getEmail());
        log.info("confirm={}", confirm);

        emailCode = confirm;
        
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/confirm")
    public ResponseEntity<EmailConfirmDto> emailConfirm(@RequestBody EmailConfirmDto dto) throws Exception {
        log.info("dto={} before ", dto);

        log.info("emailCode={}",emailCode);
        
        if (dto.getVerificationCode().equals(emailCode))
           dto.setConfirmMessage("success");
        
        if (!dto.getVerificationCode().equals(emailCode)) {
           dto.setConfirmMessage("fail");
        }   

        log.info("dto={} after", dto);     

        return ResponseEntity.ok(dto);
    }
}    