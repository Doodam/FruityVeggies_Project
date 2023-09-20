package com.fruityveggies.www.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fruityveggies.www.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/emailConfirm")
    public String emailConfirm(@RequestParam String email, Model model) {
        log.info("email={}", email);

        try {
            String confirm = emailService.sendSimpleMessage(email);
            model.addAttribute("verificationCode", confirm);
        } catch (Exception e) {
            log.error("Error sending email: {}", e.getMessage());
            return "error"; // Handle the error case as needed
        }

        return "emailConfirmationPage"; // Return the appropriate view name
    }
}
