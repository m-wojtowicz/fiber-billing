<script setup>
import { ref, watch } from "vue";
import ProductEntry from "../components/ProductEntry.vue";
import ProductDetails from "../components/ProductDetails.vue";

const dialog = ref(false);
const product = ref(null);
const current = ref(1);
const filter = ref("");
const products = [
  {
    id: 1,
    name: "product #1",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 2,
    name: "product #2",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 3,
    name: "product #3",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 4,
    name: "product #4",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 5,
    name: "product #5",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 6,
    name: "product #6",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 7,
    name: "product #7",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 8,
    name: "product #8",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 9,
    name: "product #9",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 10,
    name: "product #10",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 11,
    name: "product #11",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 12,
    name: "product #12",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 13,
    name: "product #13",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 14,
    name: "product #14",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 15,
    name: "product #15",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 16,
    name: "product #16",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 17,
    name: "product #17",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 18,
    name: "product #18",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
  {
    id: 19,
    name: "product #19",
    state: "Active",
    active: new Date(),
    cost: "55.00",
  },
];

watch(filter, (newv, oldv) => console.log(newv, oldv));
</script>

<template>
  <div
    class="column items-center q-gutter-y-md"
    style="min-width: 60%; max-width: 100%; min-height: inherit"
  >
    <div class="col-1 self-start full-width">
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

    <div class="col-10 products column full-width q-gutter-y-md">
      <ProductEntry
        class="col"
        :product="products[(current - 1) * 3]"
        v-if="(current - 1) * 3 < products.length"
        @click="
          product = products[(current - 1) * 3];
          dialog = !dialog;
        "
      />
      <ProductEntry
        class="col"
        :product="products[(current - 1) * 3 + 1]"
        v-if="(current - 1) * 3 + 1 < products.length"
        @click="
          product = products[(current - 1) * 3 + 1];
          dialog = !dialog;
        "
      />
      <ProductEntry
        class="col"
        :product="products[(current - 1) * 3 + 2]"
        v-if="(current - 1) * 3 + 2 < products.length"
        @click="
          product = products[(current - 1) * 3 + 2];
          dialog = !dialog;
        "
      />
    </div>
    <div class="col-1">
      <q-pagination
        v-model="current"
        :max="Math.ceil(products.length / 3)"
        :max-pages="5"
        outline
        direction-links
        boundary-numbers
      />
    </div>
  </div>

  <ProductDetails
    :dialog="dialog"
    :product="product"
    @change-dialog="dialog = false"
  />
</template>

<style scoped lang="sass">

.products
  flex: 1 0 100%
</style>