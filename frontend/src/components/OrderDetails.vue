<script setup>
import { ref, watch } from "vue";
import { getItemParameters } from "../services/orderService";

const emit = defineEmits(["changeDialog"]);

const props = defineProps({
  dialog: Boolean,
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

let startDate = ref(null);

watch(
  () => props.order,
  async (newVal, oldVal) => {
    if (oldVal) emit("changeDialog");
    if (newVal) {
      console.log(props.order);
      startDate.value = new Date(
        props.order.orderStartDate
      ).toLocaleDateString();
      props.order.items.forEach(async (item) => {
        item.parameters = await getItemParameters(item.id);
      });
    }
  }
);
</script>

<template>
  <q-dialog v-model="dialog">
    <q-card id="window">
      <q-card-section class="row items-center q-pb-none">
        <q-space />
        <q-btn icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row full-width" style="margin: 10px 0 10px 0">
        <div class="col text-h6 text-left">Creation date: {{ startDate }}</div>
        <div class="col text-h5 text-center text-bold">
          Order #{{ order.id }}
        </div>
        <div class="col text-h6 text-right">State: {{ order.orderStatus }}</div>
      </div>
      <q-card-section
        class="items-center q-pb-none column"
        style="min-height: 450px"
      >
        <div class="col full-width" style="height: 120%">
          <q-scroll-area visible style="height: 450px">
            <div class="q-gutter-y-md">
              <div
                v-for="item in order.items"
                :key="item.id"
                class="product-section"
              >
                <div class="row">
                  <div class="col text-h6 text-left">
                    Product ID: {{ item.id }}
                  </div>
                  <div class="col text-h6 text-center">
                    {{ item.orderItemName }}
                  </div>
                  <div class="col text-h6 text-right">
                    State: {{ item.status }}
                  </div>
                </div>
                <div class="row justify-between">
                  <div class="col-9 parameters-section">
                    <q-scroll-area style="height: 80px" visible>
                      <div class="text-left">
                        <li
                          v-for="parameter in item.parameters"
                          :key="parameter.id"
                        >
                          {{ parameter.name }} :
                          {{ parameter.value }}
                        </li>
                      </div>
                    </q-scroll-area>
                  </div>
                  <div class="col flex justify-center">
                    <div class="text-h6 self-end">
                      {{ item.oneTimeCharge }}zł/mo + {{ item.cost }} zł
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </q-scroll-area>
        </div>
      </q-card-section>
      <q-card-section class="items-center">
        <div class="text-h5 text-center text-bold">
          Total: {{ order.oneTimeCharge }} + {{ order.monthlyCharge }} zł/mo
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<style scoped lang="sass">
#window
  min-width: 60%
  justify-content: center
  text-align: center

.bar
  display: flex
  flex-direction: row
  justify-content: space-between
  align-items: center
  padding: 0px 20px 0px 20px
  box-sizing: border-box

.row > div
  padding: 10px 15px
.row + .row
  margin-top: 1rem

.col
  align-items: right

.parameters-list
  background: $background
  border-radius: 10px

.btn
  font-size: large
  margin: 5% auto 0 auto
  color: white
  background-color: $button

.product-section
  background: $background
  width: 100%
  box-shadow: 4px 8px 4px rgba(0, 0, 0, 0.25)
  border-radius: 30px
  padding: 5px 0px 0px 0px
  margin-bottom: 10px

.parameters-section
  background: $second-bg
  border-radius: 30px
</style>
