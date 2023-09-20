package com.fruityveggies.www.config;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fruityveggies.www.domain.UserDomain;
import com.fruityveggies.www.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OAuthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // 토큰에서 email, oauthType 추출
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        String email = null;
        String oauthType = token.getAuthorizedClientRegistrationId();
        
        
        if("kakao".equals(oauthType.toLowerCase())) {
         
            email = ((Map<String, Object>) token.getPrincipal().getAttribute("kakao_account")).get("email").toString();
        }
        else if("google".equals(oauthType.toLowerCase())) {
            email = token.getPrincipal().getAttribute("email").toString();
        }
        else if("naver".equals(oauthType.toLowerCase())) {

            email = ((Map<String, Object>) token.getPrincipal().getAttribute("response")).get("email").toString();
        }
        
        log.info("LOGIN SUCCESS : {} FROM {}", email, oauthType);
        
        if (email == null) {
        }
        UserDomain user = userService.getUserByEmailAndOAuthType(email, oauthType);

        // 세션에 user 저장
        log.info("USER SAVED IN SESSION");
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("email", email);
        session.setAttribute("oauthType", oauthType);
        
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
