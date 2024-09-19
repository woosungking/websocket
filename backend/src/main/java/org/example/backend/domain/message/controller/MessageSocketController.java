package org.example.backend.domain.message.controller;

import org.example.backend.domain.message.dto.RequestMessage;
import org.example.backend.domain.message.dto.ResponseMessage;
import org.example.backend.domain.message.service.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageSocketController {
	private final MessageService messageService;

	public MessageSocketController(MessageService messageService) {
		this.messageService = messageService;
	}

	@MessageMapping("/{roomId}")
	@SendTo("/room/{roomId}")
	public ResponseMessage enter(@DestinationVariable("roomId") Long roomId, RequestMessage message){
	messageService.saveMessage(message.getContent(), message.getSender(),roomId);
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setContent(message.getContent());
		responseMessage.setSender(message.getSender());
	return responseMessage;
	};
}
