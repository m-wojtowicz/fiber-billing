import { createApp } from "vue";
import { createPinia } from "pinia";
<<<<<<< HEAD
=======
import { Quasar, Notify } from "quasar";
import { globalCookiesConfig } from "vue3-cookies";

import "@quasar/extras/material-icons/material-icons.css";

import "quasar/src/css/index.sass";
>>>>>>> develop

import App from "./App.vue";
import router from "./router";

const app = createApp(App);

app.use(createPinia());
app.use(router);

globalCookiesConfig({
  expireTimes: "30d",
  path: "/",
  domain: "",
  secure: true,
  sameSite: "None",
});

app.mount("#app");
