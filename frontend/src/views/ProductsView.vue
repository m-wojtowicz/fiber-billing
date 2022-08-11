<script setup>
import { ref, watch, onBeforeMount } from "vue";
import ProductEntry from "../components/ProductEntry.vue";
import ProductDetails from "../components/ProductDetails.vue";
import { getAllProducts } from "../services/productService.js";

const dialog = ref(false);
const product = ref(null);
const current = ref(1);
const filter = ref("");

const productList = ref([]);
onBeforeMount(async () => {
  await getAllProducts(1).then((resp) => {
    productList.value = resp.data;
    productList.value.forEach((element) => {
      element.activationDate = new Date(element.activationDate);
    });
    console.log(productList.value);
  });
});

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
        :product="productList[(current - 1) * 3]"
        v-if="(current - 1) * 3 < productList.length"
        @click="
          product = productList[(current - 1) * 3];
          dialog = !dialog;
        "
      />
      <ProductEntry
        class="col"
        :product="productList[(current - 1) * 3 + 1]"
        v-if="(current - 1) * 3 + 1 < productList.length"
        @click="
          product = productList[(current - 1) * 3 + 1];
          dialog = !dialog;
        "
      />
      <ProductEntry
        class="col"
        :product="productList[(current - 1) * 3 + 2]"
        v-if="(current - 1) * 3 + 2 < productList.length"
        @click="
          product = productList[(current - 1) * 3 + 2];
          dialog = !dialog;
        "
      />
    </div>
    <div class="col-1">
      <q-pagination
        v-model="current"
        :max="Math.ceil(productList.length / 3)"
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
