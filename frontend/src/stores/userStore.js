import { defineStore } from "pinia";

export const userStore = defineStore({
  id: "login",
  state: () => ({
    login: "",
    password: "",
    token: {},
  }),
});
