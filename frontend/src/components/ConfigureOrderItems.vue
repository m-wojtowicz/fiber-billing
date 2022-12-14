<script setup>
import { ref, watch, onMounted, toRaw } from "vue";
import { loginStore } from "../stores/loginStore";
import {
  checkOpenOrder,
  getConfigData,
  sendConfigData,
} from "../services/orderService";
import { useQuasar } from "quasar";
import { Loading } from "quasar";

const $q = useQuasar();

const user = loginStore();

const isSizeChanged = ref(false);

const props = defineProps({
  dialog: Boolean,
  orderId: Number,
});

const emit = defineEmits(["responseDialog", "changeDialog"]);

const order = ref({
  id: 1,
  orderStatus: "",
  orderStartDate: "",
  oneTimeCharge: 0,
  monthlyCharge: 0,
  items: [
    {
      id: 1,
      orderItemName: "",
      status: "",
      cost: 0,
      oneTimeCharge: 0,
      parameters: [
        {
          id: 1,
          name: "",
          isMonthly: true,
          values: [],
          prices: [],
        },
      ],
    },
  ],
});

const saveData = ref(null);

const choosenValues = ref(null);
const prices = ref(null);

let data = ref(false);

watch(
  () => props.dialog,
  async (newVal) => {
    if (newVal) {
      data.value = true;
      await getConfigData(props.orderId, user.clientType).then((resp) => {
        order.value = resp.data;
      });
      choosenValues.value = new Array(order.value.items.length).fill("");
      prices.value = new Array(order.value.items.length).fill(0);
      for (var i = 0; i < order.value.items.length; i++) {
        choosenValues.value[i] = new Array(
          order.value.items[i].parameters.length
        ).fill("");
        prices.value[i] = new Array(
          order.value.items[i].parameters.length
        ).fill(0);
        isSizeChanged.value = true;
      }
    }
  }
);

const save = async () => {
  Loading.show();
  let data = new Object();
  data.id = order.value.id;
  data.items = new Array();
  for (var i = 0; i < order.value.items.length; i++) {
    let item = new Object();
    item.id = order.value.items[i].id;
    item.values = toRaw(choosenValues.value[i]);
    data.items.push(item);
  }
  await sendConfigData(order.value.id, data)
    .then((resp) => {
      if (resp.status === 200) {
        saveData.value = resp.data;
        $q.notify({
          message: `Config data saved`,
          color: "green",
        });
      }
    })
    .catch((err) => {
      $q.notify({
        message: "Error",
        color: "red",
      });
    });
  data.value = false;
  emit("changeDialog");
  emit("responseDialog", saveData.value);
  Loading.hide();
};

const getPrice = (row, col, value) => {
  let index = order.value.items[row].parameters[col].values.indexOf(value);
  if (index === -1) return 0;
  return order.value.items[row].parameters[col].prices[index];
};

const getMonthlyProductPrice = (productIndex) => {
  let sum = 0;
  for (var i = 0; i < choosenValues.value[productIndex].length; i++) {
    let index = order.value.items[productIndex].parameters[i].values.indexOf(
      choosenValues.value[productIndex][i]
    );
    if (index !== -1)
      sum += order.value.items[productIndex].parameters[i].prices[index];
  }
  return sum;
};

const getProductPrice = (productIndex) => {
  let sum = 0;
  for (var i = 0; i < order.value.items[productIndex].parameters.length; i++) {
    if (order.value.items[productIndex].parameters[i].isMonthly === false) {
      sum += order.value.items[productIndex].parameters[i].prices[0];
    }
  }
  return sum;
};

const getOrderProductPrice = () => {
  let sum = 0;
  for (var i = 0; i < choosenValues.value.length; i++) {
    for (var j = 0; j < choosenValues.value[i].length; j++) {
      let index = order.value.items[i].parameters[j].values.indexOf(
        choosenValues.value[i][j]
      );
      if (index !== -1) {
        sum += order.value.items[i].parameters[j].prices[index];
      }
    }
  }
  return sum;
};

const getMonthlyOrderProductPrice = () => {
  let sum = 0;
  for (var i = 0; i < choosenValues.value.length; i++) {
    for (var j = 0; j < choosenValues.value[i].length; j++) {
      if (order.value.items[i].parameters[j].isMonthly === false) {
        sum += order.value.items[i].parameters[j].prices[0];
      }
    }
  }
  return sum;
};

onMounted(() => {
  choosenValues.value = new Array(order.value.items.length).fill("");
  prices.value = new Array(order.value.items.length).fill(0);
  for (var i = 0; i < order.value.items.length; i++) {
    choosenValues.value[i] = new Array(
      order.value.items[i].parameters.length
    ).fill("");
    prices.value[i] = new Array(order.value.items[i].parameters.length).fill(0);
  }
});
</script>

<template>
  <q-dialog v-model="dialog" persistent>
    <q-card id="window">
      <q-card-section class="row items-center q-pb-none">
        <q-space />
        <q-btn
          icon="close"
          flat
          round
          dense
          @click="$emit('changeDialog')"
          v-close-popup
        />
      </q-card-section>
      <div class="row full-width" style="margin: 10px 0 10px 0">
        <div class="col text-h6 text-left">
          Creation date:
          {{ new Date(order.orderStartDate).toLocaleDateString() }}
        </div>
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
                      <div
                        v-for="(parameter, index) in item.parameters"
                        :key="parameter.id"
                      >
                        <div
                          class="flex justify-between items-center"
                          v-if="parameter.isMonthly === true"
                        >
                          {{ parameter.name }} :
                          <q-select
                            borderless
                            v-model="choosenValues[ind][index]"
                            :options="parameter.values"
                            style="width: fit-content"
                          />
                          {{ getPrice(ind, index, choosenValues[ind][index]) }}
                        </div>
                        <div
                          class="flex items-center"
                          v-if="parameter.isMonthly === false"
                        >
                          {{ parameter.name }} :
                          <div class="q-my-md" style="margin-left: auto">
                            {{ parameter.prices[0] }}
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col flex justify-center">
                    <div class="text-h6">
                      {{ getProductPrice(ind) }} z?? +
                      {{ getMonthlyProductPrice(ind) }} z??/mo
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </q-scroll-area>
        </div>
      </q-card-section>
      <q-card-section class="items-center">
        <div class="flex row justify-between q-mx-md">
          <div class="text-h5 text-bold">
            Total: {{ getMonthlyOrderProductPrice() }} z?? +
            {{ getOrderProductPrice() }} z??/mo
          </div>
          <q-btn class="save_btn" @click="save()"> Save </q-btn>
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

.save_btn
  background-color: #1976D2
  color: #FFFFFF
</style>
