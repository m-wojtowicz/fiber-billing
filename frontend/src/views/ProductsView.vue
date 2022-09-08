<script setup>
import { ref, watch, onBeforeMount } from "vue";
import ProductEntry from "../components/ProductEntry.vue";
import ProductDetails from "../components/ProductDetails.vue";
import { getUserProducts } from "../services/productService.js";
import { storeToRefs } from "pinia";
import { loginStore } from "../stores/loginStore";
import { Loading } from "quasar";

const user = loginStore();
const { clientType, userId } = storeToRefs(user);

const dialog = ref(false);
const productId = ref(0);
const product = ref(null);
const current = ref(1);
const filter = ref("");
const filterType = ref("");

const currDate = new Date();
let date = currDate.getDate().toString();
let month = (currDate.getMonth() + 1).toString();
let year = currDate.getFullYear().toString();
if (date.length < 2) date = 0 + date; 
if (month.length < 2) month = 0 + month;
const filterDate = ref(year + "/" + month + "/" + date);
const filterTypes = ["ID", "Name", "Status", "Activation date"];

watch(filter, () => updateProducts());
watch(filterDate, () => updateProducts());

const login = loginStore();
const products = ref(null);
const total = ref(null);

await updateProducts();

async function updateProducts() {
  Loading.show();
  let filterValue = filter.value;
  if (filterType.value === "Activation date" && filterDate.value !== "") {
    filterValue = filterDate.value;
  }

  await getUserProducts(
    login.login,
    current.value,
    filterValue,
    filterType.value
  ).then((res) => {
    if (res.products !== undefined && res.total !== undefined) {
      products.value = res.orders;
      total.value = res.total;
    } else {
      products.value = [];
      total.value = 0;
    }
  });

  Loading.hide();
}


</script>

<template>
  <div
    class="column items-center q-gutter-y-md"
    style="min-width: 60%; max-width: 100%; min-height: inherit"
  >
    <div class="col-1 self-start full-width flex q-gutter-xs">
      <q-input
          outlined
          v-model="filter"
          debounce="1000"
          label="Filter"
          style="min-width: 200px; max-width: 300px; max-height: 54px"
          v-if="filterType !== 'Activation date'"
        >
          <template v-slot:append>
            <q-icon name="search" />
          </template>
        </q-input>

        <q-input
          outlined
          v-model="filterDate"
          mask="date"
          :rules="['date']"
          style="min-width: 200px; max-width: 300px; max-height: 54px"
          v-else
        >
          <template v-slot:append>
            <q-icon name="event" class="cursor-pointer">
              <q-popup-proxy
                cover
                transition-show="scale"
                transition-hide="scale"
              >
                <q-date v-model="filterDate">
                  <div class="row items-center justify-end">
                    <q-btn v-close-popup label="Close" color="primary" flat />
                  </div>
                </q-date>
              </q-popup-proxy>
            </q-icon>
          </template>
        </q-input>

        <q-select
          outlined
          v-model="filterType"
          :options="filterTypes"
          label="Type"
          style="min-width: 100px; max-width: 200px"
        />
    </div>

    <div class="col-10 products column full-width q-gutter-y-md">
      <ProductEntry
        class="col"
        :product="products[0]"
        v-if="products.length > 0"
        @click="
          product = products[0];
          dialog = !dialog;
        "
      />
      <ProductEntry
        class="col"
        :product="products[1]"
        v-if="products.length > 1"
        @click="
          product = products[1];
          dialog = !dialog;
        "
      />
      <ProductEntry
        class="col"
        :product="products[2]"
        v-if="products.length > 2"
        @click="
          product = products[2];
          dialog = !dialog;
        "
      />
    </div>
    <div class="col-1">
      <q-pagination
        v-model="current"
        @update:model-value="updateProducts()"
        :max="total"
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
