// package org.example.backend.common;
//
// import org.springframework.stereotype.Component;
// import org.springframework.web.socket.CloseStatus;
// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;
// import org.springframework.web.socket.handler.TextWebSocketHandler;
// @Component
// public class WebSocketHandler extends TextWebSocketHandler {
// 	@Override
// 	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
// 		System.out.println("WebSocket 연결이 열렸습니다: " + session.getId());
// 	}
//
// 	@Override
// 	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
// 		String payload = message.getPayload();
// 		System.out.println("받은 메시지: " + payload);
// 		session.sendMessage(new TextMessage("Hello, " + payload));
// 	}
//
// 	@Override
// 	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
// 		System.out.println("WebSocket 연결이 닫혔습니다: " + session.getId());
// 	}
//
// }
