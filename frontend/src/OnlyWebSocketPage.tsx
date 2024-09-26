import React, { useEffect, useState } from "react";

const OnlyWebSocketPage: React.FC = () => {
  const [webSocket, setWebSocket] = useState<WebSocket | null>(null);
  const [message, setMessage] = useState<string>("");
  const [messages, setMessages] = useState<string[]>([]);

  useEffect(() => {
    // WebSocket 연결 설정
    const ws = new WebSocket("ws://localhost:8080/websocket");
    ws.onopen = () => {
      console.log("WebSocket 연결됨");
    };

    ws.onmessage = (event) => {
      console.log("Received message:", event.data);
      setMessages((prevMessages) => [...prevMessages, event.data]);
    };

    ws.onclose = () => {
      console.log("WebSocket 연결 끊김");
    };

    setWebSocket(ws);

    return () => {
      ws.close();
    };
  }, []);

  const sendMessage = () => {
    if (webSocket) {
      webSocket.send(message); // 서버로 메시지 전송
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
        placeholder="메시지를 입력하세요"
      />
      <button onClick={sendMessage}>Send</button>
    </div>
  );
};

export default OnlyWebSocketPage;
