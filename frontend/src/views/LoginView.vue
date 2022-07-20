<script setup>
import { ref } from "vue";
import axios from "axios";

const loginInput = ref("");
const passwordInput = ref("");

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
    <el-input 
      size="large" 
      class="logging-input" 
      v-model="loginInput" 
      placeholder="Login" />
    <el-input
      class="logging-input"
      v-model="passwordInput"
      size="large"
      type="password"
      placeholder="Password"
      show-password
    />
    <q-btn class="logging-button" style="color: #FF0080" label="Login" @click="checkData(loginInput, passwordInput)"></q-btn>

    <p>Not registered? <router-link class="router" :to="{ name: 'home' }"> Create account </router-link></p>
  </main>
  <router-view></router-view>
</template>

<style>
main{
  padding: 20px;
  display: grid;
  background-color: #64B5F6;
  box-shadow: 10px 10px 5px lightblue;
  border-radius: 15px;
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

.logging-input,
.logging-button{
  margin: 5% auto 0 auto;
}

.logging-input{
  max-width: 70%;
}

.logging-button {
  font-size: large;
  font-weight: bold;
}

.router {
  text-decoration: none;
  color: #1976D2
}

.router:hover{
  color: #023e8a;
}
</style>
