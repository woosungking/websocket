// package org.example.backend.domain.chatroom.controller;
//
// import org.example.backend.domain.chatroom.service.ChatRoomService;
// import org.example.backend.domain.message.entity.Message;
// import org.springframework.messaging.handler.annotation.DestinationVariable;
// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.SendTo;
// import org.springframework.stereotype.Controller;
//
// import lombok.RequiredArgsConstructor;
//
// @Controller
// @RequiredArgsConstructor
// public class ChatController {
// 	private final ChatRoomService chatRoomService;
// 	@MessageMapping("/{roomId}") //여기로 전송되면 메서드 호출 -> WebSocketConfig prefixes 에서 적용한건 앞에 생략
// 	@SendTo("/room/{roomId}")   //구독하고 있는 장소로 메시지 전송 (목적지)  -> WebSocketConfig Broker 에서 적용한건 앞에 붙어줘야됨
// 	public Message chat(@DestinationVariable Long roomId, Message content) {
//
//
// 		//채팅 저장
// 		// Message chat = chatService.createChat(roomId, message.getSender(), message.getSenderEmail(), message.getMessage());
// 		// chatRoomService.
// 		// return ChatMessage.builder()
// 		// 	.roomId(roomId)
// 		// 	.sender(chat.getSender())
// 		// 	.senderEmail(chat.getSenderEmail())
// 		// 	.message(chat.getMessage())
// 		// 	.build();
// 	}
// }