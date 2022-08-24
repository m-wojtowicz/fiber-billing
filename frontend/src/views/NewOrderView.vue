<script setup>
import { ref, onBeforeMount } from "vue";
import OfferCard from "../components/OfferCard.vue";
import {
  checkOpenOrder,
  createOrder,
  getOrderItems,
} from "../services/orderService";
import { getAvailableOffers } from "../services/offerService";
import { loginStore } from "../stores/loginStore";

const order = ref(null);
const offers = ref(null);
const items = ref(null);
const user = loginStore();

onBeforeMount(async () => {
  await checkOpenOrder(user.userId).then((resp) => {
    order.value = {
      id: resp.data,
    };
  });

  if (order.value === "") {
    await createOrder(user.userId).then((resp) => {
      order.value = {
        id: resp.data,
      };
    });
  }

  await getOrderItems(order.value.id).then((resp) => {
    items.value = resp.data;
  });

  await getAvailableOffers().then((resp) => {
    offers.value = resp.data;
  });
});
</script>
<template class="flex items-start">
  <div>
    <div class="header flex justify-between q-ma-lg">
      <q-btn class="cart_button" icon="shopping_cart" />
      <h3>Avalible offers</h3>
      <p class="order-number">Order number: {{ order.id }}</p>
    </div>
    <ul class="q-gutter-xl">
      <OfferCard
        v-for="offer in offers"
        :key="offer.id"
        class="q-mx-xl"
        :offer="offer"
      />
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
