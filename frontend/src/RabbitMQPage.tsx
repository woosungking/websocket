import React, { ChangeEvent, useEffect, useState } from "react";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

const RabbitMQPage: React.FC = () => {
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
      // RabbitMQ의 exchange와 연결된 queue로 메시지를 수신
      client.subscribe(`/topic/room.1`, (msg) => {
        console.log("Received message:", msg.body);
        try {
          console.log("메롱", msg.body);
          // const parsedMessage = JSON.parse(msg.body);
          // console.log("Parsed message:", parsedMessage);
          // const messageContent = parsedMessage.content;
          setMessages((prevMessages) => [...prevMessages, msg.body]);
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

  const sendMessage = (e: ChangeEvent<HTMLInputElement>) => {
    setMessage(e.target.value);

    if (stompClient) {
      const newMessage = JSON.stringify({
        content: message,
        sender: user,
      }); // 서버로 보낼 메시지 형식

      // 메시지 서버로 전송
      stompClient.send(`/publish/chat.enter.1`, {}, newMessage); // 컨트롤러 요청

      // 메시지를 전송 후 바로 화면에 표시
      setMessages((prevMessages) => [...prevMessages, message]);
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
        onChange={sendMessage}
        placeholder="Type your message..."
      />
      <button onChange={sendMessage}>Send</button>
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

export default RabbitMQPage;
