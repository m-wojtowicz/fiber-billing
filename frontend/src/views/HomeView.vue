<script setup>
<<<<<<< HEAD
import TheWelcome from "@/components/TheWelcome.vue";
</script>

<template>
  <main>
    <TheWelcome />
  </main>
=======
import { loginStore } from "../stores/loginStore";

const user = loginStore();
</script>

<template>
  <h2 class="font">Hi {{ user.login }}!</h2>
  <h2 class="font">Welcome to our system</h2>
>>>>>>> develop
</template>

<style scoped lang="sass">
.font
  font-family: 'Brush Script MT', cursive
</style>
