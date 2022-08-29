<script setup>
import { ref, watch, onMounted } from "vue";

const props = defineProps({
  dialog: Boolean,
});

const emit = defineEmits(["responseDialog"]);

const order = {
  id: 1,
  orderStatus: "elo",
  orderStartDate: "2022-10-10",
  orderEndDate: "2022-11-10",
  oneTimeCharge: 0,
  monthlyCharge: 0,
  items: [
    {
      id: 1,
      orderItemName: "Fiber",
      status: "XD",
      cost: 0,
      oneTimeCharge: 0,
      parameters: [
        {
          id: 1,
          name: "Speed",
          values: ["100 mb/s", "200 mb/s"],
          prices: [19.99, 29.99],
        },
        {
          id: 2,
          name: "Speed",
          values: ["100 mb/s", "200 mb/s"],
          prices: [19.99, 29.99],
        },
      ],
    },
    {
      id: 2,
      orderItemName: "Fiber",
      status: "XD",
      cost: 0,
      oneTimeCharge: 0,
      parameters: [
        {
          id: 1,
          name: "Speed",
          values: ["100 mb/s", "200 mb/s"],
          prices: [19.99, 29.99],
        },
        {
          id: 2,
          name: "Speed",
          values: ["100 mb/s", "200 mb/s"],
          prices: [19.99, 29.99],
        },
      ],
    },
  ],
};

const choosenValues = ref(null);
const prices = ref(null);

let startDate = ref(new Date(order.orderStartDate).toLocaleDateString());
let data = ref(false);

watch(
  () => props.dialog,
  () => {
    if (props.dialog === true) data.value = true;
  }
);

watch(
  () => data.value,
  () => {
    if (data.value === false) {
      emit("responseDialog", props.dialog);
    }
  }
);

const getPrice = (row, col, value) => {
  let index = order.items[row].parameters[col].values.indexOf(value);
  if (index === -1) return 0;
  return order.items[row].parameters[col].prices[index];
};

const getProductPrice = (productIndex) => {
  let sum = 0;
  for (var i = 0; i < choosenValues.value[productIndex].length; i++) {
    let index = order.items[productIndex].parameters[i].values.indexOf(
      choosenValues.value[productIndex][i]
    );
    if (index !== -1)
      sum += order.items[productIndex].parameters[i].prices[index];
  }
  return sum;
};

const getOrderProduct = () => {
  let sum = 0;
  for (var i = 0; i < choosenValues.value.length; i++) {
    for (var j = 0; j < choosenValues.value[i].length; j++) {
      let index = order.items[i].parameters[j].values.indexOf(
        choosenValues.value[i][j]
      );
      if (index !== -1) {
        sum += order.items[i].parameters[j].prices[index];
      }
    }
  }
  return sum;
};

onMounted(() => {
  choosenValues.value = new Array(order.items.length).fill("");
  prices.value = new Array(order.items.length).fill(0);
  for (var i = 0; i < order.items.length; i++) {
    choosenValues.value[i] = new Array(order.items[i].parameters.length).fill(
      ""
    );
    prices.value[i] = new Array(order.items[i].parameters.length).fill(0);
  }
  console.log(choosenValues.value);
});
</script>

<template>
  <q-dialog v-model="data" persistent>
    <q-card id="window">
      <q-card-section class="row items-center q-pb-none">
        <q-space />
        <q-btn
          icon="close"
          flat
          round
          dense
          @click="console.log(dialog)"
          v-close-popup
        />
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
                v-for="(item, ind) in order.items"
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
                    <div class="text-left">
                      <ui
                        v-for="(parameter, index) in item.parameters"
                        :key="parameter.id"
                      >
                        <div class="flex justify-between items-center">
                          {{ parameter.name }} :
                          <q-select
                            borderless
                            v-model="choosenValues[ind][index]"
                            :options="parameter.values"
                            style="width: fit-content"
                          />
                          {{ getPrice(ind, index, choosenValues[ind][index]) }}
                        </div>
                      </ui>
                    </div>
                  </div>
                  <div class="col flex justify-center">
                    <div class="text-h6 self-end">
                      {{ item.oneTimeCharge }} zł +
                      {{ getProductPrice(ind) }} zł/mo
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
          Total: {{ order.oneTimeCharge }} zł + {{ getOrderProduct() }} zł/mo
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
