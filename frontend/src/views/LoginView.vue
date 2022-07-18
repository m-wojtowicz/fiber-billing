<script setup>
import { ref } from "vue";
import axios from "axios";

const loginInput = ref("");
const passwordInput = ref("");

async function checkData(login, password) {
  
  // console.log(login + ' ' + password)
  let params = {
    grant_type: "password",
    username: login,
    password: password,
    client_id: "test"
  }

  const data = Object.keys(params)
  .map((key)=>`${key}=${encodeURIComponent(params[key])}`)
  .join('&');

  const options = {
    method: "POST",
    headers: {"content-type": "application/x-www-form-urlencoded"},
    data,
    url: "http://localhost:8080/realms/myrealm/protocol/openid-connect/token"
  }

axios(options).then(r => console.log(r)).catch(err=>console.log(err));
}
</script>

<template>
  <main>
    <h1>Welcome to our application</h1>
      <el-input class="logging-input" v-model="loginInput" placeholder="Login" />
      <el-input
        class="logging-input"
        v-model="passwordInput"
        type="password"
        placeholder="Password"
        show-password
      />
      <el-button
        class="logging-button"
        color="#00bd7e"
        @click="checkData(loginInput, passwordInput)"
        >Login</el-button
      >
  </main>
</template>

<style>
main{
  display: grid;
  text-align: center;
}

h1{
  text-decoration: none;
  color: hsla(160, 100%, 37%, 1);
}

.logging-input,
.logging-button{
  margin: 10px 0px;
}

.logging-input{
  max-width: 100%;
}
</style>
