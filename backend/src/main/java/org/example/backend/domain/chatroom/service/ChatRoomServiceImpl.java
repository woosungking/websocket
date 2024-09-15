package org.example.backend.domain.chatroom.service;

import java.util.Optional;

import org.example.backend.domain.chatroom.entity.Chatroom;
import org.example.backend.domain.chatroom.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomServiceImpl implements ChatRoomService{

	private final ChatRoomRepository chatRoomRepository;

	public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository) {

		this.chatRoomRepository = chatRoomRepository;
	}

	@Override
	public void createChatRoom() {
		String chatRoomName = "채팅방";
		Chatroom newChatroom = new Chatroom();
		newChatroom.setName(chatRoomName);//테스트용이라 그냥.. 하드코딩으로
		chatRoomRepository.save(newChatroom);
	}

	@Override
	public Optional<Chatroom> getChatRoom(Long id) {
		Optional<Chatroom> chatRoom = chatRoomRepository.findById(id);
		return chatRoom;
	}
}
