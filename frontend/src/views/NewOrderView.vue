<script setup>
import OfferCard from "../components/OfferCard.vue";

let offers = [
  { name: "Offer", description: "asdasds asd" },
  { name: "Offer", description: "asdasds asd" },
];
</script>

<template>
  <div id="new-order">
    <div class="header flex justify-between q-my-lg q-mx-xl">
      <q-btn class="cart_button" icon="shopping_cart" />
      <h3>Avalible offers</h3>
      <p class="order-number">Order number: 1323</p>
    </div>
    <div class="flex justify-center q-mt-xl">
      <ul class="q-gutter-xl">
        <OfferCard v-for="offer in offers" :key="offer.name" class="q-mx-xl" />
      </ul>
    </div>
    <div class="q-mt-xl flex justify-end">
      <q-btn class="configure_button q-mt-xl q-mr-xl">
        Configure Products
      </q-btn>
    </div>
  </div>
</template>

<style scoped lang="sass">
#new-order
  min-height: inherit
  width: 100%

.cart_button
  background-color: #1976D2
  color: #FFFFFF

.configure_button
  background-color: #1976D2
  color: #FFFFFF

.order-number
  font-weight: bold
  font-size: 20px

ul
  display: grid
  grid-template-columns: repeat(2, 1fr)
</style>
