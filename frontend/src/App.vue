<script setup>
import LoginView from "./views/LoginView.vue";
</script>

<template>
  <LoginView/>
</template>

<style>
@import "@/assets/base.css";

#app {
  max-width: 1280px;
  margin: 0 auto;
  padding: 2rem;

  /* font-weight: normal; */
}

@media (min-width: 1024px) {
  body {
    display: flex;
    place-items: center;
  }
}
</style>
