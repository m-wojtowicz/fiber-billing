<script setup>
import { ref, watch } from "vue";

const emit = defineEmits(["changeDialog"]);

const props = defineProps({
  dialog: Boolean,
  product: {
    id: Number,
    name: String,
    state: String,
  },
});

watch(
  () => props.dialog,
  (newVal, oldVal) => {
    if (oldVal) emit("changeDialog");
  }
);

const parameters = ref([
  { id: 1, name: "Parameter #1", cost: "19,99" },
  { id: 2, name: "Parameter #2", cost: "19,99" },
  { id: 3, name: "Parameter #3", cost: "19,99" },
  { id: 4, name: "Parameter #4", cost: "19,99" },
  { id: 5, name: "Parameter #5", cost: "19,99" },
  { id: 6, name: "Parameter #6", cost: "19,99" },
  { id: 7, name: "Parameter #7", cost: "19,99" },
  { id: 8, name: "Parameter #8", cost: "19,99" },
  { id: 9, name: "Parameter #9", cost: "19,99" },
  { id: 10, name: "Parameter #10", cost: "19,99" },
]);

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
        <div class="col text-h6 text-right">State: {{ product.state }}</div>
      </div>

      <div class="row">
        <div class="col">
          <q-card
            class="parameters-list"
            style="text-align: left; padding: 10px 10px 10px 10px"
          >
            <h6 v-for="parameter in parameters" :key="parameter.id" class="bar">
              {{ parameter.name }}
            </h6>
          </q-card>
        </div>
        <div class="col">
          <q-card
            class="parameters-list"
            style="text-align: left; padding: 10px 10px 10px 10px"
          >
            <div
              v-for="parameter in parameters"
              :key="parameter.id"
              class="bar"
            >
              <h6>{{ parameter.name }}</h6>
              <h6>{{ parameter.cost }} zł/mo</h6>
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
