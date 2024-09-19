package org.example.backend.domain.chatroom.controller;

import java.util.Optional;

import org.example.backend.domain.chatroom.dto.ResponseChatroom;
import org.example.backend.domain.chatroom.service.ChatRoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/chatroom")
public class ChatRoomController {
	private  final ChatRoomService chatRoomService;

	public ChatRoomController(ChatRoomService chatRoomService) {
		this.chatRoomService = chatRoomService;
	}

	@PostMapping("/create")
	public void chatRoomCreate(){
		chatRoomService.createChatRoom();
	}

	@GetMapping("/{chatroom_id}")
	public Optional<ResponseChatroom> getChatRoom(@PathVariable("chatroom_id") Long id){
		return chatRoomService.getChatRoom(id);
	}
}
