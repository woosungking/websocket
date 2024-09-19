package org.example.backend.domain.chatroom.service;

import java.util.List;
import java.util.Optional;

import org.example.backend.domain.chatroom.dto.ResponseChatroom;
import org.example.backend.domain.chatroom.entity.Chatroom;

public interface ChatRoomService {
	public void createChatRoom();
	public Optional<ResponseChatroom> getChatRoom(Long id);
}
