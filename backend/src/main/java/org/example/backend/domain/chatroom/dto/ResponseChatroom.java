package org.example.backend.domain.chatroom.dto;

import java.util.List;

import org.example.backend.domain.message.dto.ResponseMessage;
import org.example.backend.domain.message.entity.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ResponseChatroom {
	private String name;
	private List<ResponseMessage> messages;

	// name Getter
	public String getName() {
		return name;
	}

	// name Setter
	public void setName(String name) {
		this.name = name;
	}

	// messages Getter
	public List<ResponseMessage> getMessages() {
		return messages;
	}

	// messages Setter
	public void setMessages(List<ResponseMessage> messages) {
		this.messages = messages;
	}
}
