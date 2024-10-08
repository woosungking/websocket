import React, { useEffect, useState } from "react";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

const ChatPage: React.FC = () => {
  const [stompClient, setStompClient] = useState<Stomp.Client | null>(null);
  const [message, setMessage] = useState<string>("");
  const [messages, setMessages] = useState<string[]>([]);
  const [roomId, setRoomId] = useState<number>(1); // default roomId
  const [user, setUser] = useState<string>("User1"); // default user

  useEffect(() => {
    // WebSocket 연결 설정
    const socket = new SockJS("http://localhost:8080/ws");
    const client = Stomp.over(socket);

    client.connect({}, (frame) => {
      console.log("Connected: " + frame);

      setStompClient(client);

      // 메시지 구독
      // client.subscribe(`/queue/room/${roomId}`) enableStompBroker에서 /topic /queue 를 정의했는데 둘중하나 써도 상관X 물론 관례적인 컨벤션이라 용도에 맞게!
      client.subscribe(`/topic/${roomId}`, (msg) => {
        console.log("Received message:", msg.body);

        try {
          const parsedMessage = JSON.parse(msg.body);
          console.log("Parsed message:", parsedMessage);
          const messageContent = parsedMessage.content;
          setMessages((prevMessages) => [...prevMessages, messageContent]);
        } catch (error) {
          console.error("Failed to parse message:", msg.body);
        }
      });

      // 연결 해제 로그
      client.onDisconnect = () => {
        console.log("WebSocket Disconnected");
      };
    });

    return () => {
      // 컴포넌트 언마운트 시 연결 해제
      if (client && client.connected) {
        client.disconnect(() => {
          console.log("Disconnected");
        });
      }
    };
  }, [roomId]); // roomId가 변경될 때마다 useEffect가 실행되도록 설정

  const sendMessage = () => {
    if (stompClient) {
      const newMessage = JSON.stringify({
        content: message,
        sender: user,
      }); // 서버단에 RequestMessage 형식에 맞춘거임.

      // 메시지 서버로 전송
      stompClient.send(`/publish/${roomId}`, {}, newMessage);
      //StompConfig에서 정의한

      // 메시지를 전송 후 바로 화면에 표시
      setMessages((prevMessages) => [...prevMessages, message]);

      // 입력 필드 초기화
      setMessage("");
    }
  };

  return (
    <div>
      <h1>Chat Page</h1>
      <div>
        <h2>Messages:</h2>
        <ul>
          {messages.map((msg, index) => (
            <li key={index}>{msg}</li>
          ))}
        </ul>
      </div>
      <input
        type="text"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        placeholder="Type your message..."
      />
      <button onClick={sendMessage}>Send</button>
      <div>
        <label>
          Room ID:
          <input
            type="number"
            value={roomId}
            onChange={(e) => setRoomId(Number(e.target.value))}
          />
        </label>
        <label>
          User:
          <input
            type="text"
            value={user}
            onChange={(e) => setUser(e.target.value)}
          />
        </label>
      </div>
    </div>
  );
};

export default ChatPage;
