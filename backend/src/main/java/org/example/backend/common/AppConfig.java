package org.example.backend.common;

import org.example.backend.domain.chatroom.controller.ChatRoomController;
import org.example.backend.domain.chatroom.repository.ChatRoomRepository;
import org.example.backend.domain.chatroom.service.ChatRoomService;
import org.example.backend.domain.chatroom.service.ChatRoomServiceImpl;
import org.example.backend.domain.message.repository.MessageRepository;
import org.example.backend.domain.message.service.MessageService;
import org.example.backend.domain.message.service.MessageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;

@Configuration
public class AppConfig {
	// @Bean
	// public WebSocketHandler webSocketHandler(){
	// 	return new org.example.backend.common.WebSocketHandler();
	// }

	@Bean
	public ChatRoomService chatRoomService(ChatRoomRepository chatRoomRepository) {
		return new ChatRoomServiceImpl(chatRoomRepository);
	}

	@Bean
	public MessageService messageService(MessageRepository messageRepository) {
		return new MessageServiceImpl(messageRepository);
	}}

