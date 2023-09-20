package com.fruityveggies.www.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.extern.slf4j.Slf4j;

// 내장 브로커 사용
// @EnableWebSocketMessageBroker : 메시지 브로커가 지원하는 ‘WebSocket 메시지 처리’를 활성화

@ComponentScan
@Configuration
@EnableWebSocketMessageBroker	
@Slf4j
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

	//endpoint를 /stomp로 하고, allowedOrigins를 "*"로 하면 페이지에서
    //Get /info 404 Error가 발생한다. 그래서 아래와 같이 2개의 계층으로 분리하고
    //origins를 개발 도메인으로 변경하니 잘 동작하였다.
    //이유는 왜 그런지 아직 찾지 못함// 엔드포인트 호출 (웹소켓 세션을 연결)
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	log.info("웹소켓 세션을 연결");
        registry.addEndpoint("/stomp/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    // 어플리케이션 내부에서 사용할 path를 지정할 수 있음 configureMessageBroker() : 메모리 기반의 Simple Message Broker를 활성화
    @Override
    public void configureMessageBroker(MessageBrokerRegistry broker) {
    	log.info("송수신 시 앞에 붙일 태그 ");
    	// 메세지 발신 시 붙일 prefix 정의 
    	broker.setApplicationDestinationPrefixes("/pub");	
    	// 메세지를 수신해 줄 때 prefix 정의 
    	broker.enableSimpleBroker("/sub");	//SimpleBroker는 해당하는 경로를 SUBSCRIBE하는 Client에게 메세지를 전달
    }
}