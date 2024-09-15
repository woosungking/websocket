package org.example.backend.domain.chatroom.entity;

import java.util.List;
import org.example.backend.domain.message.entity.Message;
import jakarta.persistence.Entity;
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

	@OneToMany(mappedBy = "chatRoom")
	private List<Message> messages;

	// 기본 생성자
	public Chatroom() {}

	// 모든 필드를 받는 생성자
	public Chatroom(Long id, String name, List<Message> messages) {
		this.id = id;
		this.name = name;
		this.messages = messages;
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

	// messages Getter
	public List<Message> getMessages() {
		return messages;
	}

	// messages Setter
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}