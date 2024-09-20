package org.example.backend.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.Getter;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
	private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
	// 클라이언트가 연결을 하면 클라이언트의 세션정보를 저장
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session); // 새 클라이언트 세션 추가
	}

	// @Override
	// protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	// 	// // 받은 메시지를 모든 클라이언트에게 전송
	// 	// for (WebSocketSession s : sessions) {
	// 	// 	s.sendMessage(message);
	// 	// } 서비스나 컨트롤러 단에서 포이치문을 쓰는게 더 합리적이라 판단.
	// 	session.sendMessage(new TextMessage("서버에서 응답: " + message.getPayload()));
	// }
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 모든 세션에 메시지 전송
		for (WebSocketSession s : sessions) {
			s.sendMessage(new TextMessage("서버에서 응답: " + message.getPayload()));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session); // 세션 종료 시 클라이언트 제거
	}

	public Set<WebSocketSession> getSession(){
		return sessions;
	}
}
