import React, { useEffect, useState } from "react";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

const ChatPage: React.FC = () => {
  const [stompClient, setStompClient] = useState<Stomp.Client | null>(null);
  const [message, setMessage] = useState<string>("");

  useEffect(() => {
    // WebSocket 연결 설정
    const socket = new SockJS("http://localhost:8080/ws-stomp");
    const client = Stomp.over(socket);

    client.connect({}, (frame) => {
      console.log("Connected: " + frame);
      setStompClient(client); // WebSocket 연결 후 상태 업데이트

      // WebSocket 연결이 성공하면 메시지를 수신할 수 있는 핸들러 설정
      client.subscribe("/room/1", (msg) => {
        console.log("Received message:", msg.body);
      });
    });

    // 컴포넌트 언마운트 시 연결 해제
    return () => {
      if (stompClient && stompClient.connected) {
        stompClient.disconnect(() => {
          console.log("Disconnected");
        });
      }
    };
  }, [stompClient]); // stompClient를 의존성 배열에 추가하여 상태 업데이트 반영

  const sendMessage = () => {
    if (stompClient && stompClient.connected && message.trim()) {
      // 채팅방 ID를 1로 가정
      const roomId = 1;
      stompClient.send(
        `/send/${roomId}`,
        {},
        JSON.stringify({
          content: message,
          sender: "User1",
        })
      );
      setMessage(""); // 메시지 전송 후 입력 필드를 비움
    }
  };

  return (
    <div>
      <h1>Chat Page</h1>
      <input
        type="text"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        placeholder="Type your message..."
      />
      <button onClick={sendMessage}>Send</button>
    </div>
  );
};

export default ChatPage;
