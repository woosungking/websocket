package org.example.backend.domain.message.service;

import java.util.List;

import org.example.backend.domain.message.entity.Message;
import org.example.backend.domain.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{

	private final MessageRepository messageRepository;
	public MessageServiceImpl(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	public void saveMessage(String message, String sender) {
		Message newMessage = new Message();
		newMessage.setSender(sender);
		newMessage.setContent(message);
		messageRepository.save(newMessage);
	}

	@Override
	public List<Message> getMessage() {
		return null;
	}
}
