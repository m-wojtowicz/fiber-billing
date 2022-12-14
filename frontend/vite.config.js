import { fileURLToPath, URL } from "url";

import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import { quasar, transformAssetUrls } from "@quasar/vite-plugin";

export default defineConfig({
  plugins: [
    vue({
      template: { transformAssetUrls },
    }),

    quasar({
      sassVariables: "src/assets/quasar-variables.sass",
    }),
  ],
  extras: ["material-icons"],
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url)),
    },
  },
});
