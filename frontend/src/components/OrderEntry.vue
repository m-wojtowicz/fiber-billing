<script setup>
const props = defineProps({
  order: {
    id: Number,
    type: String,
    state: String,
    creation: Date,
    cost1: String,
    cost2: String,
    parameters: {
      id: Number,
      name: String,
    },
  },
});
</script>

<template>
  <div class="window column">
    <div class="col-1 row">
      <div class="col text-h6">Order type: {{ order.type }}</div>
      <div class="col text-h5 text-center">Order #{{ order.id }}</div>
      <div class="col text-h6 text-right">State: {{ order.state }}</div>
    </div>
    <div class="col row">
      <div class="col text-h6" style="align-self: flex-end">
        Creation date:
        {{ order.creation.getDay() }}-{{ order.creation.getMonth() }}-{{
          order.creation.getFullYear()
        }}
      </div>
      <div class="col column">
        <div class="text-center text-h6 col-5">Order products:</div>
        <div class="col-7">
          <q-scroll-area visible style="height: 100%">
            <div class="text-left">
              <li v-for="parameter in order.parameters" :key="parameter.id">
                {{ parameter.name }}
              </li>
            </div>
          </q-scroll-area>
        </div>
      </div>
      <div class="col text-h6 text-right" style="align-self: flex-end">
        {{ order.cost1 }} zł +{{ order.cost2 }} zł/mo
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
