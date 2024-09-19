package org.example.backend.domain.message.entity;

import org.example.backend.domain.chatroom.entity.Chatroom;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	private String sender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chatroom_id")
	private Chatroom chatroom; // 수정된 부분

	// 기본 생성자
	public Message() {}

	// 모든 필드를 받는 생성자
	public Message(Long id, String content, String sender, Chatroom chatroom) { // 수정된 부분
		this.id = id;
		this.content = content;
		this.sender = sender;
		this.chatroom = chatroom; // 수정된 부분
	}

	// id Getter
	public Long getId() {
		return id;
	}

	// id Setter
	public void setId(Long id) {
		this.id = id;
	}

	// content Getter
	public String getContent() {
		return content;
	}

	// content Setter
	public void setContent(String content) {
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

	// chatroom Getter // 수정된 부분
	public Chatroom getChatroom() {
		return chatroom; // 수정된 부분
	}

	// chatroom Setter // 수정된 부분
	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom; // 수정된 부분
	}
}