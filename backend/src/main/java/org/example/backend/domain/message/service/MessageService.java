package org.example.backend.domain.message.service;

import java.util.List;

import org.example.backend.domain.message.entity.Message;

public interface MessageService {
	public void saveMessage(String message, String sender);
	public List<Message> getMessage();
}
