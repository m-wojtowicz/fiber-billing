<script setup>
import OfferCard from "../components/OfferCard.vue";
</script>
<template>
  <div class="heders flex justify-between">
    <q-btn class="cart_button" icon="shopping_cart" />
    <h3>Avalible offers</h3>
    <p class="order-number">Order number: 1323</p>
  </div>
  <div class="avalible-offers flex justify-center q-gutter-xl">
    <OfferCard />
    <OfferCard />
  </div>
  <div>
    <q-btn class="configure_button q-ma-md"> Configure Products </q-btn>
  </div>
</template>

<style scoped lang="sass">

.heders
  width: 90%
  position: absolute
  top: 20px

.cart_button,
  background-color: #1976D2
  color: #FFFFFF

.configure_button
  background-color: #1976D2
  color: #FFFFFF

.order-number
  font-weight: bold
  font-size: 20px

.avalible-offers
  width: 100%
</style>
