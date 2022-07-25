<script setup>
import { ref } from "vue";
import axios from "axios";

const step = ref(1);

// step 1
const clientType = ref("");

// step 2
const name = ref("");
const surname = ref("");
const email = ref("");
const registrationLoginInput = ref("");
const registrationPasswordInput = ref("");
const registrationRepeadPasswordInput = ref("");

const isPwd = ref(true);
const isRPwd = ref(true);

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
  console.log(access_token);
}

async function register() {
  let data = {
    firstName: "Sergey",
    lastName: "Kargopolov",
    email: "test@test.com",
    enabled: "true",
    username: "app-user",
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
        <div class="q-gutter-sm row justify-center">
          <q-radio
            v-model="clientType"
            checked-icon="task_alt"
            unchecked-icon="panorama_fish_eye"
            val="regular"
            label="Regular"
          />
          <q-radio
            v-model="clientType"
            checked-icon="task_alt"
            unchecked-icon="panorama_fish_eye"
            val="business"
            label="Business"
          />
        </div>
      </q-step>

      <q-step :name="2" title="User data" icon="person" :done="step > 2">
        <q-input
          v-if="clientType == 'regular'"
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="name"
          placeholder="Name"
        />

        <q-input
          v-if="clientType == 'regular'"
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="surname"
          placeholder="Surname"
        />

        <q-input
          v-if="clientType == 'business'"
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="surname"
          placeholder="Company name"
        />

        <q-input
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="email"
          placeholder="Email"
        />

        <q-input
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="registrationLoginInput"
          placeholder="Login"
        />

        <q-input
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="registrationPasswordInput"
          :type="isPwd ? 'password' : 'text'"
          placeholder="Password"
        >
          <template v-slot:append>
            <q-icon
              :name="isPwd ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="isPwd = !isPwd"
            />
          </template>
        </q-input>

        <q-input
          class="login-details-inputs"
          rounded
          outlined
          bg-color="white"
          v-model="registrationRepeadPasswordInput"
          :type="isRPwd ? 'password' : 'text'"
          placeholder="Repeat password"
        >
          <template v-slot:append>
            <q-icon
              :name="isRPwd ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="isRPwd = !isRPwd"
            />
          </template>
        </q-input>
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
          />
          <q-btn
            v-if="step == 3"
            @click="getToken"
            color="primary"
            label="Finish"
          />
          <q-btn
            v-if="step > 1"
            flat
            color="primary"
            @click="register"
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
  margin-top: 5%;
  font-size: large;
}

.router {
  text-decoration: none;
  color: #1976d2;
}
</style>
