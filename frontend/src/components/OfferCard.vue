<script setup>
import { addOfferToOrder } from "../services/orderService";
import { useQuasar } from "quasar";

const $q = useQuasar();
const emit = defineEmits(["updateItems"]);

defineProps({
  orderId: Number,
  offer: {
    id: Number,
    offerName: String,
    product: {
      id: Number,
      productName: String,
    },
  },
});

async function addOffer(orderId, offerId, productName) {
  await addOfferToOrder(orderId, offerId)
    .then((res) => {
      if (res.status === 200 || res.status === 204) {
        $q.notify({
          message: `${productName} added to cart`,
          color: "green",
        });
        emit("updateItems");
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

<template>
  <q-card flat bordered class="my-card">
    <q-card-section>
      <div class="text-h5 q-mt-sm q-mb-xs">{{ offer.offerName }}</div>
      <div class="text-caption">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua.
      </div>
    </q-card-section>
    <q-separator dark />
    <q-card-section>
      <div class="text-h5 q-mt-sm q-mb-xs">{{ offer.product.productName }}</div>
      <div class="text-caption">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua.
      </div>
    </q-card-section>
    <q-separator dark />
    <q-card-actions>
      <q-btn
        flat
        color="#FFFFFF"
        label="Add card"
        @click="addOffer(orderId, offer.id, offer.product.productName)"
      />
    </q-card-actions>
  </q-card>
</template>

<style scoped lang="sass">
.my-card
  color: white
  background: $background
  max-width: 500px
  filter: drop-shadow(5px 10px 4px rgba(0, 0, 0, 0.25))
  width: 100%
</style>
