import React, { ChangeEvent, useEffect, useState } from "react";
import Stomp from "stompjs";
import SockJS from "sockjs-client";
import axios from "axios";

const RealTimePage: React.FC = () => {
  const [stompClient, setStompClient] = useState<Stomp.Client | null>(null);
  const [message, setMessage] = useState<string>("");
  const [messages, setMessages] = useState<string[]>([]);
  const [roomId, setRoomId] = useState<number>(1); // default roomId
  const [user, setUser] = useState<string>("User1"); // default user

  useEffect(() => {
    // WebSocket 연결 설정
    const socket = new SockJS("http://localhost:8080/ws-stomp");
    const client = Stomp.over(socket);

    client.connect({}, (frame) => {
      console.log("Connected: " + frame);

      setStompClient(client);

      // 메시지 구독
      client.subscribe(`/room/realtime`, (msg) => {
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
      });

      // 메시지 서버로 전송
      stompClient.send(`/send/${roomId}`, {}, newMessage);

      // 메시지를 전송 후 바로 화면에 표시
      setMessages((prevMessages) => [...prevMessages, message]);

      // 입력 필드 초기화
      setMessage("");
    }
  };

  const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
    setMessage(e.target.value);
    if (stompClient) {
      const newMessage = JSON.stringify({
        content: message,
        sender: user,
      });

      // 메시지 서버로 전송
      stompClient.send(`/send/realtime`, {}, newMessage);

      // 메시지를 전송 후 바로 화면에 표시
      setMessages((prevMessages) => [...prevMessages, message]);
    }
  };

  return (
    <div className="flex flex-col h-screen w-[1064px] p-4 bg-gray-100">
      <p className="text-3xl font-bold mb-4 text-center">Chat Room</p>
      <div className="flex-1 overflow-y-auto bg-white p-4 border border-gray-300 rounded-lg shadow-md">
        <h2 className="text-xl font-semibold mb-2">Messages:</h2>
        <ul className="space-y-2">
          {messages.map((msg, index) => (
            <li
              key={index}
              className="bg-gray-200 p-2 rounded-md shadow-sm text-black"
            >
              {msg}
            </li>
          ))}
        </ul>
      </div>
      <div className="flex items-center mt-4">
        <input
          type="text"
          value={message}
          onChange={handleInput}
          placeholder="Type your message..."
          className="flex-1 p-2 border border-gray-300 rounded-l-md"
        />
        <button
          onClick={sendMessage}
          className="bg-blue-500 text-white p-2 rounded-r-md hover:bg-blue-600"
        >
          Send
        </button>
      </div>
    </div>
  );
};

export default RealTimePage;
