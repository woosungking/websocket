import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.tsx";
import "./index.css";
import ChatPage from "./ChatPage.tsx";
import RealTimePage from "./RealTimePage.tsx";
import StickerPage from "./StickerPage.tsx";
import OnlyWebSocketPage from "./OnlyWebSocketPage.tsx";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    {/* <ChatPage></ChatPage> */}
    {/* <RealTimePage></RealTimePage> */}
    {/* <StickerPage></StickerPage> */}
    <OnlyWebSocketPage></OnlyWebSocketPage>
  </StrictMode>
);
