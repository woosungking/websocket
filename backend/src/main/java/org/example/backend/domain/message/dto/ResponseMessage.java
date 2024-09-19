package org.example.backend.domain.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ResponseMessage {
	private String sender;
	private String content;

	// 기본 생성자
	public ResponseMessage() {}

	// 모든 필드를 받는 생성자
	public ResponseMessage(String sender, String content) {
		this.sender = sender;
		this.content = content;
	}

	// sender Getter
	public String getSender() {
		return sender;
	}

	// sender Setter
	public void setSender(String sender) {
		this.sender = sender;
	}

	// content Getter
	public String getContent() {
		return content;
	}

	// content Setter
	public void setContent(String content) {
		this.content = content;
	}
}