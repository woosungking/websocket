package org.example.backend.domain.chatroom.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.backend.domain.message.entity.Message;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Chatroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Message> messages = new ArrayList<>();

	// 기본 생성자
	public Chatroom() {}

	// 모든 필드를 받는 생성자
	public Chatroom(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	// id Getter
	public Long getId() {
		return id;
	}

	// id Setter
	public void setId(Long id) {
		this.id = id;
	}

	// name Getter
	public String getName() {
		return name;
	}

	// name Setter
	public void setName(String name) {
		this.name = name;
	}

	// messages Getter (읽기 전용)
	public List<Message> getMessages() {
		return Collections.unmodifiableList(messages);
	}

	// 메시지 추가 메서드
	public void addMessage(Message message) {
		messages.add(message);
		message.setChatRoom(this);
	}

	// 메시지 제거 메서드
	public void removeMessage(Message message) {
		messages.remove(message);
		message.setChatRoom(null);
	}
}