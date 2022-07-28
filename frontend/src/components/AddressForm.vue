<script setup>
import { storeToRefs } from "pinia";
import { registerStore } from "../stores/register";

const register = registerStore();
const {
  country,
  city,
  street,
  streetNumber,
  houseNumber,
  zipCode,
  postOffice,
} = storeToRefs(register);

const options = ["Poland"];
</script>

<template>
  <q-select
    class="login-details-inputs"
    outlined
    bg-color="white"
    v-model="country"
    label="Country"
    default="Country"
    :options="options"
    :rules="[(val) => !!val || 'Field is required']"
  />
  <q-input
    class="login-details-inputs"
    outlined
    bg-color="white"
    v-model="city"
    label="City"
    :rules="[
      (val) => !!val || 'Field is required',
      (val) => val.length < 46 || 'Please use maximum 45 character',
    ]"
  />
  <q-input
    class="login-details-inputs"
    outlined
    bg-color="white"
    v-model="street"
    label="Street"
    :rules="[
      (val) => !!val || 'Field is required',
      (val) => val.length < 46 || 'Please use maximum 45 character',
    ]"
  />
  <q-input
    class="login-details-inputs"
    outlined
    bg-color="white"
    v-model="streetNumber"
    label="Street number"
    :rules="[
      (val) => !!val || 'Field is required',
      (val) => val.length < 6 || 'Please use maximum 5 character',
    ]"
  />
  <q-input
    class="login-details-inputs"
    outlined
    bg-color="white"
    v-model="houseNumber"
    label="House number"
    :rules="[
      (val) => !!val || 'Field is required',
      (val) => val.length < 6 || 'Please use maximum 5 character',
    ]"
  />
  <q-input
    class="login-details-inputs"
    outlined
    bg-color="white"
    v-model="zipCode"
    label="ZIP code"
    :rules="[
      (val) => !!val || 'Field is required',
      (val) => val.match(/^[0-9]{2}-[0-9]{3}$/) || 'Invalid zip code',
    ]"
  />
  <q-input
    class="login-details-inputs"
    outlined
    bg-color="white"
    v-model="postOffice"
    label="Post office"
    :rules="[
      (val) => !!val || 'Field is required',
      (val) => val.length < 46 || 'Please use maximum 45 character',
    ]"
  />
</template>

<style scoped>
.login-details-inputs {
  margin-top: 2%;
  font-size: large;
}
</style>
