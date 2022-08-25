<script setup>
import { ref, onBeforeMount } from "vue";
import OfferCard from "../components/OfferCard.vue";
import {
  checkOpenOrder,
  createOrder,
  getOrderItems,
  removeItem,
} from "../services/orderService";
import { getAvailableOffers } from "../services/offerService";
import { loginStore } from "../stores/loginStore";
import { useQuasar } from "quasar";

const $q = useQuasar();
const order = ref({ id: "" });
const offers = ref(null);
const items = ref(null);
const user = loginStore();

onBeforeMount(async () => {
  await checkOpenOrder(user.userId).then((resp) => {
    order.value = {
      id: resp.data,
    };
  });

  if (order.value.id === "") {
    await createOrder(user.userId).then((resp) => {
      order.value = {
        id: resp.data,
      };
    });
  }

  await getAvailableOffers().then((resp) => {
    offers.value = resp.data;
  });

  await updateItems();
});

async function updateItems() {
  await getOrderItems(order.value.id)
    .then((resp) => {
      if (resp.data.length > 0) items.value = resp.data;
      else items.value = null;
    })
    .catch((err) => {
      $q.notify({
        message: "Error updating cart",
        color: "red",
      });
    });
}

async function removeItemFromOrder(itemId, itemName) {
  await removeItem(itemId)
    .then((res) => {
      if (res.status === 200 || res.status === 204) {
        $q.notify({
          message: `${itemName} removed from cart`,
          color: "green",
        });
        updateItems();
      }
    })
    .catch((err) => {
      $q.notify({
        message: "Error",
        color: "red",
      });
    });
}
</script>
<template class="flex items-start">
  <div id="new-order">
    <div class="header flex justify-between q-my-lg q-mx-xl">
      <q-btn-dropdown
        class="cart_button"
        icon="shopping_cart"
        menu-anchor="bottom left"
        menu-self="top start"
      >
        <q-list style="min-width: 100px">
          <q-item v-if="items === null">
            <q-item-section>
              <q-item-label>Cart is empty </q-item-label>
            </q-item-section>
          </q-item>
          <q-item v-for="item in items" :key="item.id">
            <q-item-section>
              <q-item-label>{{ item.name }}</q-item-label>
            </q-item-section>
            <q-item-section avatar>
              <q-btn
                flat
                round
                icon="close"
                @click="removeItemFromOrder(item.id, item.name)"
              />
            </q-item-section>
          </q-item>
        </q-list>
      </q-btn-dropdown>
      <h3>Avalible offers</h3>
      <p class="order-number">Order number: {{ order.id }}</p>
    </div>
    <div class="flex justify-center q-mt-xl">
      <ul class="q-gutter-xl">
        <OfferCard
          v-for="offer in offers"
          :key="offer.id"
          class="q-mx-xl"
          :orderId="order.id"
          :offer="offer"
          @update-items="updateItems()"
        />
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
