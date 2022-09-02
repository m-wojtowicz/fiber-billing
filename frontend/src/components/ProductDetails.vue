<script setup>
import { ref, watch } from "vue";
import { getAllProductParameters } from "../services/productService.js";
import { loginStore } from "../stores/loginStore";

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
});

const user = loginStore();

const params = ref(null);
const totalCost = ref(0);

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
      totalCost.value = 0;
      if (user.clientType === "regular") {
        params.value.forEach((parm) => {
          totalCost.value += parm.priceRegular;
        });
      }
    }
    if (oldVal) emit("changeDialog");
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
            <div class="bar">
              <div class="text-weight-bold text-h5">Name</div>
              <div class="text-weight-bold text-h5">Value</div>
              <div class="text-weight-bold text-h5">Price</div>
            </div>
            <div v-for="parameter in params" :key="parameter.id" class="bar">
              <h6>{{ parameter.name }}</h6>
              <h6>{{ parameter.value }}</h6>
              <h6 v-if="user.clientType === 'regular'">
                {{ parameter.priceRegular }} zł/mo
              </h6>
              <h6 v-if="user.clientType === 'business'">
                {{ parameter.priceBusiness }} zł/mo
              </h6>
            </div>
            <div class="bar" style="margin-top: 30px">
              <div class="text-weight-bold text-h5">TOTAL</div>
              <div class="text-weight-bold text-h5">{{ totalCost }} zł/mo</div>
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

.parameters-list
  background: $background
  border-radius: 10px

.btn
  font-size: large
  margin: 5% auto 0 auto
  color: white
  background-color: $button
</style>
