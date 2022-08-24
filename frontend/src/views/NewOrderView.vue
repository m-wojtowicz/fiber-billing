<script setup>
import OfferCard from "../components/OfferCard.vue";
let offers = [
  { name: "Offer", description: "asdasds asd" },
  { name: "Offer", description: "asdasds asd" },
];
</script>
<template class="flex items-start">
  <div>
    <div class="header flex justify-between q-ma-lg">
      <q-btn class="cart_button" icon="shopping_cart" />
      <h3>Avalible offers</h3>
      <p class="order-number">Order number: 1323</p>
    </div>
    <ul class="q-gutter-xl">
      <OfferCard v-for="offer in offers" :key="offer.name" class="q-mx-xl" />
    </ul>
    <div class="q-mt-lg">
      <q-btn class="configure_button q-ma-md"> Configure Products </q-btn>
    </div>
  </div>
</template>

<style scoped lang="sass">
.header
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
