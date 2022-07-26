<script setup>
import { storeToRefs } from "pinia";
import { registerStore } from "../stores/register";

const register = registerStore();

const { clientType } = storeToRefs(register);
</script>

<template>
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
</template>
