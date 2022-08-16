<script setup>
import { ref, watch } from "vue";
import { getAllProductParameters } from "../services/productService.js";

const emit = defineEmits(["changeDialog"]);

const props = defineProps({
  dialog: Boolean,
  product: {
    id: Number,
    orderItemName: String,
    monthly: Boolean,
    activationDate: Date,
    status: String,
  },
  // params: {
  //   id: Number,
  //   orderItemName: String,
  //   name: String,
  //   value: String,
  //   priceRegular: Number,
  //   priceBusisness: Number,
  // },
});

const params = ref(null);

const passDataToEntry = async (id) => {
  let res = await getAllProductParameters(id);
  console.log(res);
  return res;
};

watch(
  () => props.dialog,
  async (newVal, oldVal) => {
    if (props.dialog) {
      params.value = await passDataToEntry(props.product.id);
      console.log(params.value);
    }
    if (oldVal) emit("changeDialog");
  }
);

const sum = 4;
</script>

<template>
  <q-dialog v-model="dialog">
    <q-card id="window">
      <q-card-section class="row items-center q-pb-none">
        <q-space />
        <q-btn icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row full-width" style="margin: 10px 0 10px 0">
        <div class="col text-h6 text-left">ID: {{ product.id }}</div>
        <div class="col text-h5 text-center">{{ product.name }}</div>
        <div class="col text-h6 text-right">State: {{ product.status }}</div>
      </div>

      <div class="row">
        <div class="col">
          <q-card
            class="parameters-list"
            style="text-align: left; padding: 10px 10px 10px 10px"
          >
            <h6 v-for="parameter in params" :key="parameter.id" class="bar">
              {{ parameter.name }}
            </h6>
          </q-card>
        </div>
        <div class="col">
          <q-card
            class="parameters-list"
            style="text-align: left; padding: 10px 10px 10px 10px"
          >
            <div v-for="parameter in params" :key="parameter.id" class="bar">
              <h6>{{ parameter.name }}</h6>
              <h6>{{ parameter.priceBusiness }} zł/mo</h6>
            </div>
            <div class="bar" style="margin-top: 30px">
              <div class="text-weight-bold text-h5">TOTAL</div>
              <div class="text-weight-bold text-h5">{{ sum }} zł/mo</div>
            </div>
          </q-card>
          <div style="padding: 10px 0 10px 0; float: right">
            <q-btn
              rounded
              class="btn shadow-5"
              @click="checkData(loginInput, passwordInput)"
            >
              <div style="font-weight: bold; padding: 3px; line-height: normal">
                REPORT<br />ISSUES
              </div>
            </q-btn>
          </div>
        </div>
      </div>
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
</style>
