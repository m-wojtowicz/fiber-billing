<script setup>
import { ref } from "vue";

const props = defineProps({
  order: {
    id: Number,
    orderStatus: String,
    orderStartDate: Date,
    orderEndDate: Date,
    oneTimeCharge: Number,
    monthlyCharge: Number,
    items: [],
  },
});

const startDate = ref(null);

if (props.order) {
  startDate.value = new Date(props.order.orderStartDate).toLocaleDateString();
}
</script>

<template>
  <div class="window column">
    <div class="col-1 row">
      <div class="col text-h6">Order type: Activation</div>
      <div class="col text-h5 text-center">Order #{{ order.id }}</div>
      <div class="col text-h6 text-right">State: {{ order.orderStatus }}</div>
    </div>
    <div class="col row">
      <div class="col text-h6" style="align-self: flex-end">
        Creation date:
        {{ startDate }}
      </div>
      <div class="col column">
        <div class="text-center text-h6 col-5">Order products:</div>
        <div class="col-7">
          <q-scroll-area visible style="height: 100%">
            <div class="text-left">
              <li v-for="item in order.items" :key="item.id">
                {{ item.orderItemName }}
              </li>
            </div>
          </q-scroll-area>
        </div>
      </div>
      <div class="col text-h6 text-right" style="align-self: flex-end">
        {{ order.oneTimeCharge }} zł + {{ order.monthlyCharge }} zł/mo
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
