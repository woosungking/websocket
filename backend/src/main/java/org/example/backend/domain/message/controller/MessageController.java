package org.example.backend.domain.message.controller;

import org.example.backend.domain.message.dto.RequestMessage;
import org.example.backend.domain.message.entity.Message;
import org.example.backend.domain.message.service.MessageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {
	private final MessageService messageService;

	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping("/create/{roomId}")
	public void createMessage(@RequestBody RequestMessage requestMessagemessage, @PathVariable("roomId") Long roomId){
		messageService.saveMessage(requestMessagemessage.getContent(), requestMessagemessage.getSender(),roomId);
	}
}
