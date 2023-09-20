package com.fruityveggies.www.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fruityveggies.www.domain.UserDomain;
import com.fruityveggies.www.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService extends DefaultOAuth2UserService {
    
    @Autowired UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        
        // email, oauthType 호출
        Map<String, Object> attributes = super.loadUser(userRequest).getAttributes();
        log.info("ATTR INFO : {}", attributes.toString());
        
        String email = null;
        String oauthType = userRequest.getClientRegistration().getRegistrationId();
        
        OAuth2User user2 = super.loadUser(userRequest);
        
        // oauth 타입에 따라 데이터가 다르기에 분기
        if("kakao".equals(oauthType.toLowerCase())) {
            // kakao는 kakao_account 내에 email이 존재함.
            log.info("kakao email={} start",email);
            log.info("(Map<String, Object>) attributes.get(\"kakao_account\")={}",(Map<String, Object>) attributes.get("kakao_account"));

            email = ((Map<String, Object>) attributes.get("kakao_account")).get("email").toString();
                    
           // 1. 카카오 로그인 시 이메일 찾기, outhType이 있을 거임. (밑에 set, save()) 사용가능.
           // 2. 이메일이랑 outhType이랑 있는데 이걸 대체할 무언가를 찾아야 됨
//               (1). email이 -> nickName (user.setEmail(attributes.get("kakao_account")).get("profile")));
//               (2). 이미 이 if문에 들어왔다는 건 kakao라는 거니까  -> user.setOuthType("kakao");
//               :::: 디벨로퍼에서 email컬럼에는 닉네임, outhType에는 kakao
//               -> 테이블에는 메일 대신에 닉네임
//               -> html에는 메일 대신에 닉네임
            // 3. 나가리.

            log.info("kakao email={} end",email);
        }
        else if("google".equals(oauthType.toLowerCase())) {
            email = attributes.get("email").toString();
            log.info("google email={}",email);
        }
        else if("naver".equals(oauthType.toLowerCase())) {
            // naver는 response 내에 email이 존재함.
            email = ((Map<String, Object>) attributes.get("response")).get("email").toString();
            
            log.info("naver email={}",email);
        }
        
        // User 존재여부 확인 및 없으면 생성
        if(getUserByEmailAndOAuthType(email, oauthType) == null) {
            log.info("{}({}) NOT EXISTS. REGISTER", email, oauthType);
            UserDomain user = new UserDomain();
            user.setEmail(email);
            user.setOauthType(oauthType);
            
            save(user);
        }
        
        return super.loadUser(userRequest);
    }
    
    // 저장, 조회만
    public void save(UserDomain user) {
        userRepository.save(user);
    }
    
    public UserDomain getUserByEmailAndOAuthType(String email, String oauthType) {      
        return userRepository.findByEmailAndOauthType(email, oauthType).orElse(null);
    }
}
