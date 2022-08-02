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
  <div id="window">
    <div class="row bar">
      <div class="col text-h6 text-left">ID: {{ product.id }}</div>
      <div class="col text-h5 text-center">
        {{ product.name }}
      </div>
      <div class="col text-h6 text-right">State: {{ product.state }}</div>
    </div>
    <div class="row bar">
      <div class="col text-h6">
        Active from:
        {{ product.active.getDay() }}-{{ product.active.getMonth() }}-{{
          product.active.getFullYear()
        }}
      </div>
      <div class="col text-h6 text-right">{{ product.cost }} zł/mo</div>
    </div>
  </div>
</template>

<style scoped lang="sass">
#window
  padding: 10px 10px 10px 10px
  background: $background
  color: white
  filter: drop-shadow(5px 10px 4px rgba(0, 0, 0, 0.25))
  border-radius: 20px
  width: 100%
  min-width: 300px

.bar
  padding: 10px 10px 10px 10px
</style>
