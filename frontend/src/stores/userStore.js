import { defineStore } from "pinia";

export const userStore = defineStore({
  id: "login",
  state: () => ({
    login: "",
    password: "",
    token: {
      access_token: "",
      expires_in: null,
      "not-before-policy": null,
      refresh_expires_in: null,
      refresh_token: "",
      scope: "",
      session_state: "",
      token_type: "",
    },
  }),
});
