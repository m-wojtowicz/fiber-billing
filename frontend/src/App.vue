<script setup>
</script>

<template>
  <div id="main-container">
    <router-view></router-view>
  </div>
  <footer class="ftr"/>
</template>

<style>
@import "@/assets/base.css";

#app {
  height:100vh; 
  margin:0;
  display:flex; 
  flex-direction:column; 
}

#main-container {
  max-width: 1280px;
  width: 40%;
  margin: 0 auto;
  padding: 2rem;

}

footer{
  margin-top:auto; 
  width: 100%;
  height: 175px;
  background-image: url("../src/assets/weaves.svg");
  background-size: cover;
}
</style>
