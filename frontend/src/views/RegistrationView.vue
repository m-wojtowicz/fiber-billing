<script setup>
import { ref, computed } from "vue";
import axios from "axios";
import { storeToRefs } from "pinia";
import { registerStore } from "../stores/register";
import ClientTypeChooser from "../components/ClientTypeChooser.vue";
import UserDataForm from "../components/UserDataForm.vue";

const register = registerStore();
const { clientType, name, surname, email, login, password, repetPassword } =
  storeToRefs(register);

const step = ref(1);

const validateUserData = computed(() => {
  if (
    name.value !== "" &&
    surname.value !== "" &&
    email.value !== "" &&
    login.value !== "" &&
    password.value !== "" &&
    repetPassword.value !== "" &&
    email.value.match(/\S+@\S+\.\S+/) &&
    password.value === repetPassword.value
  )
    return true;
  else return false;
});

// step 3
const country = ref("");
const street = ref("");
const streetNumber = ref("");
const houseNuber = ref("");
const zipCode = ref("");
const postOffice = ref("");

let access_token = "";

async function getToken() {
  let params = {
    grant_type: "client_credentials",
    client_id: "admin-cli",
    client_secret: "kGNEDI8CQphsw42oSzPk6Y1vVByAVmVo",
  };

  const data = Object.keys(params)
    .map((key) => `${key}=${encodeURIComponent(params[key])}`)
    .join("&");

  const options = {
    method: "POST",
    headers: { "content-type": "application/x-www-form-urlencoded" },
    data,
    url: "http://localhost:8080/auth/realms/master/protocol/openid-connect/token",
  };

  axios(options)
    .then((r) => (access_token = r.data.access_token))
    .catch((err) => console.log(err));
}

async function registration() {
  let data = {
    firstName: "Sergey",
    lastName: "Kargopolov",
    email: "test@test.com",
    enabled: "true",
    username: "app-user",
    credentials: [
      {
        type: "password",
        value: "test123",
        temporary: false,
      },
    ],
  };

  const options = {
    method: "POST",
    headers: {
      "content-type": "application/json",
      Authorization: `Bearer ${access_token}`,
    },
    data,
    url: "http://localhost:8080/auth/admin/realms/fiber-billing/users",
  };

  axios(options)
    .then((r) => console.log(r))
    .catch((err) => console.log(err));
}
</script>

<template>
  <main>
    <div class="q-mx-xl q-my-md">
      <h1>Register</h1>
      <hr class="divider" />
    </div>
    <q-stepper
      v-model="step"
      ref="stepper"
      alternative-labels
      animated
      done-color="light-green-13"
      active-color="blue-13"
      inactive-color="blue-grey-3"
      class="q-mx-xl q-my-md"
    >
      <q-step :name="1" title="Client type" icon="settings" :done="step > 1">
        <ClientTypeChooser />
      </q-step>

      <q-step :name="2" title="User data" icon="person" :done="step > 2">
        <UserDataForm />
      </q-step>

      <q-step :name="3" title="Address" icon="home">
        <q-input
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="country"
          placeholder="Country"
        />
        <q-input
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="street"
          placeholder="Street"
        />
        <q-input
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="streetNumber"
          placeholder="Street number"
        />
        <q-input
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="houseNuber"
          placeholder="House number"
        />
        <q-input
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="zipCode"
          placeholder="ZIP code"
        />
        <q-input
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="postOffice"
          placeholder="Post office"
        />
      </q-step>

      <template v-slot:navigation>
        <q-stepper-navigation>
          <q-btn
            v-if="step == 1"
            @click="$refs.stepper.next()"
            color="primary"
            label="Continue"
            :disable="clientType === '' ? true : false"
          />
          <q-btn
            v-if="step == 2"
            @click="$refs.stepper.next()"
            color="primary"
            label="Continue"
            :disable="!validateUserData"
          />
          <q-btn
            v-if="step == 3"
            @click="$refs.stepper.next()"
            color="primary"
            label="Finish"
          />
          <q-btn
            v-if="step > 1"
            flat
            color="primary"
            @click="$refs.stepper.previous()"
            label="Back"
            class="q-ml-sm"
          />
        </q-stepper-navigation>
      </template>
    </q-stepper>
    <p class="q-mx-xl q-mt-lg">
      Already registered?
      <router-link class="router" :to="{ name: 'login' }">
        Sign in
      </router-link>
    </p>
  </main>
</template>

<style scoped>
main {
  padding: 20px;
  display: grid;
  background-color: #64b5f6;
  box-shadow: 10px 10px 5px lightblue;
  border-radius: 15px;
  min-width: 300px;
}

h1 {
  margin-top: 7%;
  font-size: 60px;
  font-weight: bold;
  text-decoration: none;
  color: rgb(255, 255, 255);
}

.divider {
  width: 250px;
  border: 0;
  border-top: 3px solid #ffffff;
}

.stepper {
  margin-top: 5%;
  margin-left: 15%;
  margin-right: 15%;
}

.login-details-inputs {
  margin-top: 2%;
  font-size: large;
}

.router {
  text-decoration: none;
  color: #1976d2;
}
</style>
