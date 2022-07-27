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
    country: "",
    city: "",
    street: "",
    streetNumber: "",
    houseNumber: "",
    zipCode: "",
    postOffice: "",
  }),
  actions: {
    reset() {
      this.clientType = "";
      this.name = "";
      this.surname = "";
      this.email = "";
      this.login = "";
      this.password = "";
      this.repetPassword = "";
      this.country = "";
      this.city = "";
      this.street = "";
      this.streetNumber = "";
      this.houseNumber = "";
      this.zipCode = "";
      this.postOffice = "";
    },
  },
});
