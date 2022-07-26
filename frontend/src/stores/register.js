import { defineStore } from "pinia";

export const registerStore = defineStore({
  id: "register",
  state: () => ({
    clientType: "",
    name: "",
    surname: "",
    email: "",
    login: "",
    password: "",
    repetPassword: "",
  }),
});
