import React, { useEffect, useState } from "react";
import DraggableSticker from "./component/DraggableSticker";
import SizeChangeSticker from "./component/SizeChngeSticker";
import SockJS from "sockjs-client";

const StickerPage: React.FC = () => {
  const [position, setPosition] = useState({ x: 0, y: 0 });

  const handleDrag = (e, data) => {
    // 드래그 중인 스티커의 좌표를 업데이트
    console.log(data.x, data.y);
    setPosition({ x: data.x, y: data.y });
  };

  useEffect(() => {
    const socket = new SockJS("http://localhost:8080/ws");
  });
  return (
    <div>
      <DraggableSticker ondrag={handleDrag}></DraggableSticker>
      <SizeChangeSticker></SizeChangeSticker>
    </div>
  );
};
export default StickerPage;
