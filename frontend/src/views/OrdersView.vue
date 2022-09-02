<script setup>
import { ref, watch } from "vue";
import OrderEntry from "../components/OrderEntry.vue";
import OrderDetails from "../components/OrderDetails.vue";
import { getUserOrders } from "../services/orderService";
import { getUserType } from "../services/loginService";
import { loginStore } from "../stores/loginStore";

const dialog = ref(false);
const order = ref(null);
const parameters = ref(null);
const current = ref(1);
const filter = ref("");

watch(filter, (newv, oldv) => console.log(newv, oldv));

const login = loginStore();
const orders = ref(null);
const size = ref(null);

await updateOrders();

async function updateOrders() {
  await getUserOrders(login.login, current.value, filter.value).then((res) => {
    orders.value = res.orders;
    size.value = res.size;
  });
}
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
      <div class="col column">
        <div class="col row justify-end">
          <q-btn
            round
            color="primary"
            icon="add_circle"
            :to="{ name: 'new-order' }"
            size="18px"
          />
        </div>
      </div>
    </div>

    <div class="col-10 orders column full-width q-gutter-y-md">
      <OrderEntry
        class="col"
        :order="orders[0]"
        v-if="orders.length > 0"
        @click="
          order = orders[0];
          dialog = !dialog;
        "
      />

      <OrderEntry
        class="col"
        :order="orders[1]"
        v-if="orders.length > 1"
        @click="
          order = orders[1];
          dialog = !dialog;
        "
      />
      <OrderEntry
        class="col"
        :order="orders[2]"
        v-if="orders.length > 2"
        @click="
          order = orders[2];
          dialog = !dialog;
        "
      />
    </div>
    <div class="col-1">
      <q-pagination
        v-model="current"
        @update:model-value="updateOrders()"
        :max="Math.ceil(size / 3)"
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
