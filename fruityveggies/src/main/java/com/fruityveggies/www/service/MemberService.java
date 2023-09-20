package com.fruityveggies.www.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fruityveggies.www.dto.MemberSignUpDto;
import com.fruityveggies.www.repository.member.Member;
import com.fruityveggies.www.repository.member.MemberRepository;
import com.fruityveggies.www.service.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService{
    
    private final MemberRepository memberRepository;
    
    // SecurityConfig에서 설정한 PasswordEncoder 빈(bean)을 주입해줌.
    private final PasswordEncoder passwordEncoder;
    
    public String findUsernameByEmail(String email) {
        Member member = memberRepository.findByEmail(email);

        if (member != null) {
            return member.getUsername();
        } else {
            return null;
        }
    }

    // 회원 가입
    public Long registerMember(MemberSignUpDto dto) {
        log.info("registerMember(dto={})", dto);
        
        Member entity = Member.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .build();
        log.info("save 전: entity={}", entity);
        
        memberRepository.save(entity); // DB insert
        log.info("save 후: entity={}", entity);
        
        return entity.getId(); // DB에 저장된 ID(고유키)를 리턴.
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername(username={})", username);
        
        // DB에서 username으로 사용자 정보 검색(select).
        UserDetails user = memberRepository.findByUsername(username);
        
        if (user != null) {
            return user;
        }
        
        throw new UsernameNotFoundException(username + " - not found");
    }

    @Transactional
	public void updatePassword(String email, String password) {
    	
    	String encodedPassword = passwordEncoder.encode(password);
		
    	memberRepository.updateMemberPassword(email, encodedPassword);
	}
    
}
