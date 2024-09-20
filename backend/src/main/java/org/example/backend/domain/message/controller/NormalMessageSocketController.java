package org.example.backend.domain.message.controller;

import org.example.backend.common.WebSocketHandler;
import org.example.backend.domain.message.dto.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@RestController
@RequestMapping("/api/v1")
public class NormalMessageSocketController {
	@Autowired
	private final WebSocketHandler webSocketHandler;

	public NormalMessageSocketController(WebSocketHandler webSocketHandler) {
		this.webSocketHandler = webSocketHandler;
	}

	@PostMapping("/send")
	public void broadcastMessage(@RequestBody RequestMessage requestMessage) throws Exception {
		// 메시지를 WebSocketHandler에 전달하여 브로드캐스팅 처리
		TextMessage textMessage = new TextMessage(requestMessage.getContent());

		// WebSocketHandler에서 직접 모든 세션에 브로드캐스팅 처리
		for (WebSocketSession session : webSocketHandler.getSession()) {
			webSocketHandler.handleTextMessage(session, textMessage);

		}
	}
}