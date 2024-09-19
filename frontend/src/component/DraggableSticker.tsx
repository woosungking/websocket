import React from "react";
import Draggable from "react-draggable";

const DraggableSticker = () => (
  <Draggable>
    <div className="w-24 h-24 bg-blue-300 text-white flex items-center justify-center rounded-lg cursor-move">
      후하
    </div>
  </Draggable>
);

export default DraggableSticker;
