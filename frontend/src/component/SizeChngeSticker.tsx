// DraggableSticker.tsx
import React, { useState } from "react";
import Draggable from "react-draggable";
import { ResizableBox } from "react-resizable";
import "react-resizable/css/styles.css";

const SizeChangeSticker = () => {
  const [isResizing, setIsResizing] = useState(false);

  return (
    <Draggable
      disabled={isResizing} // 크기 조절 중에는 드래그 비활성화
    >
      <div>
        <ResizableBox
          width={300}
          height={300}
          minConstraints={[100, 100]}
          maxConstraints={[1000, 1000]}
          className="relative bg-blue-300 text-white flex items-center justify-center rounded-lg"
          resizeHandles={["se"]}
          handle={<span className="react-resizable-handle"></span>}
          onResizeStart={() => setIsResizing((pre) => !pre)} // 크기 조절 시작 시 드래그 비활성화
          onResizeStop={() => setIsResizing((pre) => !pre)} // 크기 조절 종료 시 드래그 활성화
        >
          <div className="flex items-center justify-center w-full h-full">
            후하
          </div>
        </ResizableBox>
      </div>
    </Draggable>
  );
};

export default SizeChangeSticker;
