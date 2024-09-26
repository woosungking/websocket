package org.example.backend.common;

import java.net.http.WebSocket;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class StompConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws")   //SockJS 연결 주소
			.setAllowedOrigins("http://localhost:5173","http://localhost:8080")
			.withSockJS(); //버전 낮은 브라우저에서도 적용 가능
		// 주소 : ws://localhost:8080/ws-stomp
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/publish","/room");
		//클라이언트에서 보낸 메세지를 받을 prefix
		// 프론트단에서 메세지를 보낼때 사용 할 url임 그말은 즉슨
		// 프론트단에서 발행한 메시지를 맏는 부분이겠지.
		registry.enableSimpleBroker("/topic","/queue");
		//해당 주소를 구독하고 있는 클라이언트들에게 메세지 전달
		//프론트단에서 구독이란걸 하는데, 그대 /topic 혹은 /queue로 이렇개 받고 있으면 되는거 하지만 이거는 컨트롤러를 봐야 이해가 됌.
	}


}
