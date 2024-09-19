import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  define: {
    global: "globalThis", // globalThis는 브라우저와 Node.js 모두에서 사용 가능
  },
});
