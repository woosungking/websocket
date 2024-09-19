package org.example.backend.domain.chatroom.dto;

import java.util.List;

import org.example.backend.domain.message.entity.Message;

public class ResponseChatroom {
	private String sender;
	private String content;
	private List<Message> messages;
}
