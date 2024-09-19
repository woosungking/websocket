import React from "react";
import DraggableSticker from "./component/DraggableSticker";
import SizeChangeSticker from "./component/SizeChngeSticker";

const StickerPage: React.FC = () => {
  return (
    <div>
      <DraggableSticker></DraggableSticker>
      <SizeChangeSticker></SizeChangeSticker>
    </div>
  );
};
export default StickerPage;
