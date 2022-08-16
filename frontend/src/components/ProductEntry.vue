<script setup>
defineProps({
  product: {
    id: Number,
    orderItemName: String,
    monthly: Boolean,
    activationDate: Date,
    status: String,
    // cost: String, TODO
  },
});
</script>

<template>
  <div class="window column">
    <div class="row col">
      <div class="col text-h6 text-left">ID: {{ product.id }}</div>
      <div class="col text-h5 text-center">
        {{ product.orderItemName }}
      </div>
      <div class="col text-h6 text-right">State: {{ product.status }}</div>
    </div>
    <div class="row col">
      <div class="col text-h6 text-left" style="align-self: flex-end">
        Active from:
        {{ product.activationDate.toLocaleDateString() }}
      </div>
      <div class="col text-h6 text-right" style="align-self: flex-end">
        {{ product.cost }} zł/mo
      </div>
    </div>
  </div>
</template>

<style scoped lang="sass">
.window
  padding: 10px
  background: $background
  color: white
  filter: drop-shadow(5px 10px 4px rgba(0, 0, 0, 0.25))
  border-radius: 20px
  width: 100%
  height: auto
  max-height: 200px
</style>
