<script setup>
const props = defineProps({
  product: {
    id: Number,
    name: String,
    state: String,
    active: Date,
    cost: String,
  },
});
</script>

<template>
  <div class="window column">
    <div class="row col">
      <div class="col text-h6 text-left">ID: {{ product.id }}</div>
      <div class="col text-h5 text-center">
        {{ product.name }}
      </div>
      <div class="col text-h6 text-right">State: {{ product.state }}</div>
    </div>
    <div class="row col">
      <div class="col text-h6 text-left" style="align-self: flex-end">
        Active from:
        {{ product.active.getDay() }}-{{ product.active.getMonth() }}-{{
          product.active.getFullYear()
        }}
      </div>
      <div class="col text-h6 text-right" style="align-self: flex-end">
        {{ product.cost }} zł/mo</div>
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
