package org.example.backend.domain.message.service;

import java.util.List;
import java.util.Optional;

import org.example.backend.domain.chatroom.entity.Chatroom;
import org.example.backend.domain.chatroom.repository.ChatRoomRepository;
import org.example.backend.domain.chatroom.service.ChatRoomService;
import org.example.backend.domain.message.entity.Message;
import org.example.backend.domain.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class MessageServiceImpl implements MessageService{

	private final MessageRepository messageRepository;
	private final ChatRoomRepository chatRoomRepository;

	public MessageServiceImpl(MessageRepository messageRepository, ChatRoomRepository chatRoomRepository) {
		this.messageRepository = messageRepository;
		this.chatRoomRepository = chatRoomRepository;
	}

	@Override
	public void saveMessage(String message, String sender, Long roomId) {
		Optional<Chatroom> chatRoom = chatRoomRepository.findById(roomId);
		// Optional이 비어 있지 않은지 확인
		if (chatRoom.isEmpty()) {
			return;
		}

		Message newMessage = new Message();
		newMessage.setSender(sender);
		newMessage.setContent(message);
		newMessage.setChatRoom(chatRoom.get());

		Chatroom getChatroom = chatRoom.get();
		getChatroom.addMessage(newMessage);

		messageRepository.save(newMessage);
	}

	@Override
	public List<Message> getMessage() {
		return null;
	}
}
