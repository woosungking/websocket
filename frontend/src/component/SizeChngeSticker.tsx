// DraggableSticker.tsx
import React from "react";
import Draggable from "react-draggable";

import { ResizableBox } from "react-resizable";
import "react-resizable/css/styles.css"; // react-resizable의 기본 스타일 필요

const SizeChangeSticker = () => (
  <Draggable>
    <div>
      <ResizableBox
        width={150} // 초기 너비
        height={150} // 초기 높이
        minConstraints={[50, 50]} // 최소 크기
        maxConstraints={[300, 300]} // 최대 크기
        className="relative bg-blue-300 text-white flex items-center justify-center rounded-lg cursor-move"
        resizeHandles={["se"]} // 크기 조절 핸들 설정 (오른쪽 아래)
      >
        <div className="flex items-center justify-center w-full h-full">
          후하
        </div>
      </ResizableBox>
    </div>
  </Draggable>
);

export default SizeChangeSticker;
