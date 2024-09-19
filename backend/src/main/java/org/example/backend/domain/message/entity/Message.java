package org.example.backend.domain.message.entity;

import org.example.backend.domain.chatroom.entity.Chatroom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	private String sender;

	@ManyToOne
	private Chatroom chatRoom;

	// 기본 생성자
	public Message() {}

	// 모든 필드를 받는 생성자
	public Message(Long id, String content, String sender, Chatroom chatRoom) {
		this.id = id;
		this.content = content;
		this.sender = sender;
		this.chatRoom = chatRoom;
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

	// chatRoom Getter
	public Chatroom getChatRoom() {
		return chatRoom;
	}

	// chatRoom Setter
	public void setChatRoom(Chatroom chatRoom) {
		this.chatRoom = chatRoom;
	}
}