<script setup>
import { ref, watch } from "vue";
import OrderEntry from "../components/OrderEntry.vue";
import OrderDetails from "../components/OrderDetails.vue";

const dialog = ref(false);
const order = ref(null);
const current = ref(1);
const filter = ref("");
const orders = [
  {
    id: 1,
    type: "Activation",
    state: "Ongoing",
    creation: new Date(),
    cost1: "55.00",
    cost2: "35.00",
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
    type: "Activation",
    state: "New",
    creation: new Date(),
    cost1: "45.00",
    cost2: "25.00",
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
    type: "Activation",
    state: "New",
    creation: new Date(),
    cost1: "45.00",
    cost2: "25.00",
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
    id: 4,
    type: "Activation",
    state: "New",
    creation: new Date(),
    cost1: "45.00",
    cost2: "25.00",
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
];

watch(filter, (newv, oldv) => console.log(newv, oldv));
</script>

<template>
  <div
    class="column items-center q-gutter-y-md"
    style="min-width: 60%; max-width: 100%; min-height: inherit"
  >
    <div class="col-1 row full-width">
      <div class="col self-start">
        <q-input
          outlined
          v-model="filter"
          label="Filter"
          style="min-width: 200px; max-width: 300px"
        >
          <template v-slot:append>
            <q-icon name="search" />
          </template>
        </q-input>
      </div>
      <div class="col text-right">
        <q-btn round color="primary" icon="add_circle" />
      </div>
    </div>

    <div class="col-10 orders column full-width q-gutter-y-md">
      <OrderEntry
        class="col"
        :order="orders[(current - 1) * 3]"
        v-if="(current - 1) * 3 < orders.length"
        @click="
          order = orders[(current - 1) * 3];
          dialog = !dialog;
        "
      />
      
      <OrderEntry
        class="col"
        :order="orders[(current - 1) * 3 + 1]"
        v-if="(current - 1) * 3 + 1 < orders.length"
        @click="
          order = orders[(current - 1) * 3 + 1];
          dialog = !dialog;
        "
      />
      <OrderEntry
        class="col"
        :order="orders[(current - 1) * 3 + 2]"
        v-if="(current - 1) * 3 + 2 < orders.length"
        @click="
          order = orders[(current - 1) * 3 + 2];
          dialog = !dialog;
        "
      />
    </div>
    <div class="col-1">
      <q-pagination
        v-model="current"
        :max="Math.ceil(orders.length / 3)"
        :max-pages="5"
        outline
        direction-links
        boundary-numbers
      />
    </div>
  </div>

  <OrderDetails
    :dialog="dialog"
    :order="order"
    @change-dialog="dialog = false"
  />
</template>

<style scoped lang="sass">

.orders
  flex: 1 0 100%
</style>
