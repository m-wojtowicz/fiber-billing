<script setup>
import { ref } from "vue";
import axios from "axios";

const loginInput = ref("");
const passwordInput = ref("");
const isPwd = ref(true);

async function checkData(login, password) {

  let params = {
    grant_type: "password",
    username: login,
    password: password,
    client_id: "fiber-billing"
  }

  const data = Object.keys(params)
  .map((key)=>`${key}=${encodeURIComponent(params[key])}`)
  .join('&');

  const options = {
    method: "POST",
    headers: {"content-type": "application/x-www-form-urlencoded"},
    data,
    url: "http://localhost:8080/auth/realms/fiber-billing/protocol/openid-connect/token"
  }

axios(options).then(r => console.log(r)).catch(err=>console.log(err));
}
</script>

<template>
  <main>
    <h1>Login</h1>
    <hr class="divider"/>
    <q-input
      class="loggin-input"
      rounded
      outlined
      bg-color="white"
      v-model="loginInput" 
      placeholder="Login"
    />

    <q-input class="loggin-input"
      rounded
      outlined
      bg-color="white"
      v-model="passwordInput" 
      :type="isPwd ? 'password' : 'text'"
      placeholder="Password">
        <template v-slot:append>
          <q-icon
            :name="isPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPwd = !isPwd"
          />
        </template>
      </q-input>

    <q-btn
      label="Login" 
      rounded 
      class="logging-button shadow-5"
      @click="checkData(loginInput, passwordInput)"
    />

    <p>Not registered? <router-link class="router" :to="{ name: 'register' }"> Create account </router-link></p>
  </main>
  <router-view></router-view>
</template>

<style scoped>
main{
  padding: 20px;
  display: grid;
  background-color: #64B5F6;
  box-shadow: 10px 10px 5px lightblue;
  border-radius: 15px;
  min-width: 300px;
  width: 574px;
}

h1{
  margin-top: 7%;
  font-size: 60px;
  font-weight: bold;
  margin-left: 15%;
  text-decoration: none;
  color: rgb(255, 255, 255);
}

p {
  margin: 7% auto 7% 15%;
}

.divider {
  width: 170px;
  margin-left: 15%;
  border: 0;
  border-top: 3px solid #FFFFFF;
}

.logging-button {
  font-size: large;
  margin: 5% auto 0 auto;
  color: white;
  background-color: #1976D2;
}

.loggin-input {
  margin: 5% 15% 0 15%;
  font-size: large;
}

.router {
  text-decoration: none;
  color: #1976D2
}

.router:hover{
  color: #023e8a;
}
</style>
