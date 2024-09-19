package org.example.backend.domain.message.dto;

public class RequestMessage {
	private String sender;
	private String content;

	// 기본 생성자 (NoArgsConstructor)
	public RequestMessage() {
	}

	// 모든 필드를 받는 생성자 (AllArgsConstructor)
	public RequestMessage(String sender, String content) {
		this.sender = sender;
		this.content = content;
	}

	// Getter for sender
	public String getSender() {
		return sender;
	}

	// Setter for sender
	public void setSender(String sender) {
		this.sender = sender;
	}

	// Getter for content
	public String getContent() {
		return content;
	}

	// Setter for content
	public void setContent(String content) {
		this.content = content;
	}
}