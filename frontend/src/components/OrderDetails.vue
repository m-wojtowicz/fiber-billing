<script setup>
import { ref, watch } from "vue";

const emit = defineEmits(["changeDialog"]);

const props = defineProps({
  dialog: Boolean,
  order: {
    id: Number,
    type: String,
    creation: Date,
  },
});

watch(
  () => props.dialog,
  (newVal, oldVal) => {
    if (oldVal) emit("changeDialog");
  }
);

const products = ref([
  {
    id: 1,
    name: "Product #1",
    state: "Ordered",
    cost: "19,99",
    parameters: [
      {
        id: 1,
        name: "parameter #1",
      },
      {
        id: 2,
        name: "parameter #2",
      },
      {
        id: 3,
        name: "parameter #3",
      },
      {
        id: 4,
        name: "parameter #4",
      },
      {
        id: 5,
        name: "parameter #5",
      },
      {
        id: 6,
        name: "parameter #6",
      },
      {
        id: 7,
        name: "parameter #7",
      },
      {
        id: 8,
        name: "parameter #8",
      },
      {
        id: 9,
        name: "parameter #9",
      },
      {
        id: 10,
        name: "parameter #10",
      },
    ],
  },
  {
    id: 2,
    name: "Product #2",
    state: "Activated",
    cost: "49,99",
    parameters: [
      {
        id: 1,
        name: "parameter #1",
      },
      {
        id: 2,
        name: "parameter #2",
      },
      {
        id: 3,
        name: "parameter #3",
      },
      {
        id: 4,
        name: "parameter #4",
      },
      {
        id: 5,
        name: "parameter #5",
      },
      {
        id: 6,
        name: "parameter #6",
      },
      {
        id: 7,
        name: "parameter #7",
      },
      {
        id: 8,
        name: "parameter #8",
      },
      {
        id: 9,
        name: "parameter #9",
      },
      {
        id: 10,
        name: "parameter #10",
      },
    ],
  },
  {
    id: 3,
    name: "Product #2",
    state: "Activated",
    cost: "49,99",
    parameters: [
      {
        id: 1,
        name: "parameter #1",
      },
      {
        id: 2,
        name: "parameter #2",
      },
      {
        id: 3,
        name: "parameter #3",
      },
      {
        id: 4,
        name: "parameter #4",
      },
      {
        id: 5,
        name: "parameter #5",
      },
      {
        id: 6,
        name: "parameter #6",
      },
      {
        id: 7,
        name: "parameter #7",
      },
      {
        id: 8,
        name: "parameter #8",
      },
      {
        id: 9,
        name: "parameter #9",
      },
      {
        id: 10,
        name: "parameter #10",
      },
    ],
  },
]);

const totalcost = 4;
</script>

<template>
  <q-dialog v-model="dialog">
    <q-card id="window">
      <q-card-section class="row items-center q-pb-none">
        <q-space />
        <q-btn icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row full-width" style="margin: 10px 0 10px 0">
        <div class="col text-h6 text-left">
          Creation date: {{ order.creation.getDay() }}-{{
            order.creation.getMonth()
          }}-{{ order.creation.getFullYear() }}
        </div>
        <div class="col text-h5 text-center text-bold">
          Order #{{ order.id }}
        </div>
        <div class="col text-h6 text-right">State: {{ order.state }}</div>
      </div>
      <q-card-section
        class="items-center q-pb-none column"
        style="min-height: 450px"
      >
        <div class="col full-width" style="height: 120%">
          <q-scroll-area visible style="height: 450px">
            <div class="q-gutter-y-md">
              <div
                v-for="product in products"
                :key="product.id"
                class="product-section"
              >
                <div class="row">
                  <div class="col text-h6 text-left">
                    Product ID: {{ product.id }}
                  </div>
                  <div class="col text-h6 text-center">
                    {{ product.name }}
                  </div>
                  <div class="col text-h6 text-right">
                    State: {{ product.state }}
                  </div>
                </div>
                <div class="row justify-between">
                  <div class="col-9 parameters-section">
                    <q-scroll-area style="height: 80px" visible>
                      <div class="text-left">
                        <li
                          v-for="parameter in product.parameters"
                          :key="parameter.id"
                        >
                          {{ parameter.name }}
                        </li>
                      </div>
                    </q-scroll-area>
                  </div>
                  <div class="col text-h6">{{ product.cost }} zł/mo</div>
                </div>
              </div>
            </div>
          </q-scroll-area>
        </div>
      </q-card-section>
      <q-card-section class="items-center" >
        <div class="text-h5 text-center text-bold">
          Total: {{ totalcost }} zł/mo
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
