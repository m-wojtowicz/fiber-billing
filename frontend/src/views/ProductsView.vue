<script setup>
import { ref, watch, onBeforeMount } from "vue";
import ProductEntry from "../components/ProductEntry.vue";
import ProductDetails from "../components/ProductDetails.vue";
import { getAllProducts } from "../services/productService.js";
import { storeToRefs } from "pinia";

import { loginStore } from "../stores/loginStore";
const user = loginStore();
const { clientType, userId } = storeToRefs(user);

const dialog = ref(false);
const productId = ref(0);
const product = ref(null);
const current = ref(1);
const filter = ref("");

const productList = ref([]);
onBeforeMount(async () => {
  await getAllProducts(userId.value, clientType.value).then((resp) => {
    productList.value = resp.data;
    productList.value.forEach((element) => {
      element.activationDate = new Date(element.activationDate);
    });
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
          productId = productList[(current - 1) * 3].id;
          dialog = !dialog;
          product = productList[(current - 1) * 3];
        "
      />
      <ProductEntry
        class="col"
        :product="productList[(current - 1) * 3 + 1]"
        v-if="(current - 1) * 3 + 1 < productList.length"
        @click="
          productId = productList[(current - 1) * 3 + 1].id;
          dialog = !dialog;
          product = productList[(current - 1) * 3 + 1];
        "
      />
      <ProductEntry
        class="col"
        :product="productList[(current - 1) * 3 + 2]"
        v-if="(current - 1) * 3 + 2 < productList.length"
        @click="
          productId = productList[(current - 1) * 3 + 2].id;
          dialog = !dialog;
          product = productList[(current - 1) * 3 + 2];
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
