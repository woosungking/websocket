package org.example.backend.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.Getter;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
	private final static Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
	private int test = 0;
	// private static Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
	// 클라이언트가 연결을 하면 클라이언트의 세션정보를 저장
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session);
		test = test+1;
		System.out.println("테스트 카운트"+ test);
		sessions.add(session); // 새 클라이언트 세션 추가
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("전송");
		// 모든 세션에 메시지 전송
		for (WebSocketSession s : sessions) {
			System.out.println("핸들러 중계중"+ sessions);
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
	public int getTest(){
		return test;
	}
}
