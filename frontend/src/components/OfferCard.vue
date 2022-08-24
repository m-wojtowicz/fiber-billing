<script setup>
defineProps({
  offer: {
    id: Number,
    offerName: String,
    product: {
      id: Number,
      productName: String,
    },
  },
});
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
      <q-btn flat color="#FFFFFF" label="Add card" />
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
