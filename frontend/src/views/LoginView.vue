<script setup>
import { ref } from "vue";
import router from "../router";
const loginInput = ref("");
const passwordInput = ref("");

function checkData(login, password) {
  if (login === "login" && password === "123") {
    // router.replace({name: "home"})
    console.log(login + " " + password);
  }
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
