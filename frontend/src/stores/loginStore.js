import { defineStore } from "pinia";

export const loginStore = defineStore({
  id: "login",
  state: () => ({
    login: "",
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
    clientType: "",
    userId: 0,
  }),
  actions: {
    isLogged() {
      if (this.token.access_token !== "") return true;
      else false;
    },
  },
  persist: true,
});
