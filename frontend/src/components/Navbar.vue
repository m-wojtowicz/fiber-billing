<script setup>
import { userStore } from "../stores/userStore";

const user = userStore();
</script>

<template>
  <q-toolbar id="toolbar">
    <q-btn
      flat
      @click="drawer = !drawer"
      round
      dense
      icon="menu"
      size="25px"
    />

    <q-btn flat dense :to="{ name: 'home' }" @click="link = 'home'">
      <img src="@/assets/logo.svg" height="50" />
    </q-btn>

    <q-btn
      flat
      round
      dense
      icon="person"
      size="25px"
      :to="{ name: 'user' }"
      @click="link = 'user'"
      style="font-size: 20px"
    />
  </q-toolbar>
</template>

<style scoped lang="sass">

#toolbar
  left: 0px
  top: 0px
  display: flex
  flex-direction: row
  justify-content: space-between
  align-items: center
  padding: 0px 30px
  background: $primary
</style>
