<script setup>
  
  import { ref } from 'vue'

  const active = ref(0)
  const isRegularClient = ref(true);
  const registrationLoginInput = ref("");
  const registrationPasswordInput = ref("");

  const next = () => {
    active.value++;
  }

  const prev = () => {
    active.value--;
  }

  const register = () => {
    active.value++;
  }

</script>

<template>
  <main>
    <h1>Register</h1>
    <hr class="divider"/>

    <el-steps class="stepper" :active="active" finish-status="success">
      <el-step title="Login details" />
      <el-step title="User data" />
      <el-step title="Adres" />
    </el-steps>

    <el-input
      class="login-details-inputs"
      size="large" 
      v-model="registrationLoginInput" 
      placeholder="Login" 
    />
    <el-input
      class="login-details-inputs"
      v-model="registrationPasswordInput"
      size="large"
      type="password"
      placeholder="Password"
      show-password
    />
    <el-input
      class="login-details-inputs"
      v-model="registrationPasswordInput"
      size="large"
      type="password"
      placeholder="Repet password"
      show-password
    />

    <div class="nav-buttons">
      <el-button v-if="active > 0 &&  active < 3" @click="prev">Prev</el-button>
      <el-button v-if="active < 2" @click="next">Next</el-button>
      <el-button v-if="active == 2" @click="register">Register</el-button>
    </div>
  </main>
</template>

<style>

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
    width: 250px;
    margin-left: 15%;
    border: 0;
    border-top: 3px solid #FFFFFF;
  }

  .stepper {
    margin-top: 5%;
    margin-left: 15%;
    margin-right: 15%;
  }

  .login-details-inputs{
     max-width: 70%;
     margin: 5% auto 0 auto;
  }

  .nav-buttons {
    margin: 5% auto 0 auto;
  }

</style>
