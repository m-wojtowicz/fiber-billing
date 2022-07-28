<script setup>
import { ref, watch } from "vue";
import { storeToRefs } from "pinia";
import { registerStore } from "../stores/register";

const register = registerStore();
const { name, surname, email, login, password, repetPassword } =
  storeToRefs(register);

const isPwd = ref(true);
const isRPwd = ref(true);
const isPwdSame = ref("Passwords don't match");

watch([password, repetPassword], () => {
  if (password.value === repetPassword.value) isPwdSame.value = true;
  else isPwdSame.value = "Passwords don't match";
});
</script>

<template>
  <q-input
    v-if="register.clientType == 'regular'"
    class="login-details-inputs"
    rounded
    outlined
    bg-color="white"
    v-model="name"
    placeholder="Name"
    :rules="[
      (val) => !!val || 'Field is required',
      (val) => val.length < 46 || 'Please use maximum 45 character',
    ]"
  />

  <q-input
    v-if="register.clientType == 'regular'"
    class="login-details-inputs"
    rounded
    outlined
    bg-color="white"
    v-model="surname"
    placeholder="Surname"
    :rules="[
      (val) => !!val || 'Field is required',
      (val) => val.length < 46 || 'Please use maximum 45 character',
    ]"
  />

  <q-input
    v-if="register.clientType == 'business'"
    class="login-details-inputs"
    rounded
    outlined
    bg-color="white"
    v-model="name"
    placeholder="Company name"
    :rules="[
      (val) => !!val || 'Field is required',
      (val) => val.length < 46 || 'Please use maximum 45 character',
    ]"
  />

  <q-input
    class="login-details-inputs"
    rounded
    outlined
    bg-color="white"
    v-model="email"
    placeholder="Email"
    :rules="[
      (val) => !!val || 'Field is required',
      (val) => val.length < 46 || 'Please use maximum 45 character',
      (val) => val.match(/\S+@\S+\.\S+/) || 'Invalid email',
    ]"
  />

  <q-input
    class="login-details-inputs"
    rounded
    outlined
    bg-color="white"
    v-model="login"
    placeholder="Login"
    :rules="[(val) => !!val || 'Field is required']"
  />

  <q-input
    class="login-details-inputs"
    rounded
    outlined
    bg-color="white"
    v-model="password"
    :type="isPwd ? 'password' : 'text'"
    placeholder="Password"
    :rules="[(val) => !!val || 'Field is required']"
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
    v-model="repetPassword"
    :type="isRPwd ? 'password' : 'text'"
    placeholder="Repeat password"
    reactive-rules
    :rules="[(val) => !!val || 'Field is required', () => isPwdSame]"
  >
    <template v-slot:append>
      <q-icon
        :name="isRPwd ? 'visibility_off' : 'visibility'"
        class="cursor-pointer"
        @click="isRPwd = !isRPwd"
      />
    </template>
  </q-input>
</template>

<style scoped>
.login-details-inputs {
  margin-top: 2%;
  font-size: large;
}
</style>
