import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.tsx";
import "./index.css";
import ChatPage from "./ChatPage.tsx";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <ChatPage></ChatPage>
  </StrictMode>
);
