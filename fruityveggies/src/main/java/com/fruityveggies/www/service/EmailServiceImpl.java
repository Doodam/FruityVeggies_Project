package com.fruityveggies.www.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        String ePw = createKey(); // Generate a new verification code
        MimeMessage message = createMessage(to, ePw);
        try {
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }

    private MimeMessage createMessage(String to, String verificationCode) throws Exception {
        System.out.println("보내는 대상 : " + to);
        System.out.println("인증 번호 : " + verificationCode);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to);
        message.setSubject("FrutyVeggies 인증 코드");

        String msgg = "";
        msgg += "<div style='margin: 20px; font-family: Arial, sans-serif;'>";
        msgg += "<h1 style='color: #007BFF; text-align: center;'>Fruity Veggies</h1>";
        msgg += "<div style='border: 1px solid #007BFF; padding: 20px; text-align: center;'>";
        msgg += "<h3 style='color: #007BFF;'>Fruity Veggies 인증 코드입니다.</h3>";
        msgg += "<p style='font-size: 120%;'>CODE: <strong>" + verificationCode + "</strong></p>";
        msgg += "</div>";
        msgg += "<p style='text-align: center; margin-top: 20px;'>감사합니다!</p>";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");
        message.setFrom(new InternetAddress("FrutyVeggies", "FrutyVeggies"));

        return message;
    }

    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3);

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }
        return key.toString();
    }
}
