package org.example.backend.domain.chatroom.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.backend.domain.chatroom.dto.ResponseChatroom;
import org.example.backend.domain.chatroom.entity.Chatroom;
import org.example.backend.domain.chatroom.repository.ChatRoomRepository;
import org.example.backend.domain.message.dto.ResponseMessage;
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
	public Optional<ResponseChatroom> getChatRoom(Long id) {
		Optional<Chatroom> chatRoom = chatRoomRepository.findById(id);

		if (chatRoom.isPresent()) {
			Chatroom chatroomEntity = chatRoom.get();

			// ResponseChatroom DTO 생성
			ResponseChatroom responseChatroom = new ResponseChatroom();
			responseChatroom.setName(chatroomEntity.getName());

			// Message 리스트를 DTO로 변환
			List<ResponseMessage> responseMessages = chatroomEntity.getMessages().stream()
				.map(message -> {
					ResponseMessage responseMessage = new ResponseMessage();
					responseMessage.setContent(message.getContent());
					return responseMessage;
				}).collect(Collectors.toList());

			responseChatroom.setMessages(responseMessages);

			return Optional.of(responseChatroom);
		}

		return Optional.empty();  // Chatroom이 없으면 빈 Optional 반환
	}
}
