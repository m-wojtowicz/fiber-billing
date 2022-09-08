<script setup>
import { ref, watch } from "vue";
import OrderEntry from "../components/OrderEntry.vue";
import OrderDetails from "../components/OrderDetails.vue";
import { getUserOrders } from "../services/orderService";
import { getUserType } from "../services/loginService";
import { loginStore } from "../stores/loginStore";
import { Loading } from "quasar";

const dialog = ref(false);
const order = ref(null);
const parameters = ref(null);
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
const filterTypes = ["ID", "Status", "Creation date"];

watch(filter, () => updateOrders());
watch(filterDate, () => updateOrders());

const login = loginStore();
const orders = ref(null);
const total = ref(null);

await updateOrders();

async function updateOrders() {
  Loading.show();
  let filterValue = filter.value;
  if (filterType.value === "Creation date" && filterDate.value !== "") {
    filterValue = filterDate.value;
  }

  await getUserOrders(
    login.login,
    current.value,
    filterValue,
    filterType.value
  ).then((res) => {
    if (res.orders !== undefined && res.total !== undefined) {
      orders.value = res.orders;
      total.value = res.total;
    } else {
      orders.value = [];
      total.value = 0;
    }
  });

  console.log(orders.value);

  Loading.hide();
}
</script>

<template>
  <div
    class="column items-center q-gutter-y-md"
    style="min-width: 60%; max-width: 100%; min-height: inherit;"
  >
    <div class="col-1 row full-width">
      <div class="col self-start flex q-gutter-xs">
        <q-input
          outlined
          v-model="filter"
          debounce="1000"
          label="Filter"
          style="min-width: 200px; max-width: 300px; max-height: 54px"
          v-if="filterType !== 'Creation date'"
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
      <div class="col column" style="height: 54px">
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
      <div
        v-if="orders.length === 0"
        class="col table"
        style="width: 100%; height: 100%"
      >
        <div class="no-entries">
          <h3 style="margin: auto">No entries found!</h3>
        </div>
      </div>
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
        :max="total"
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

.no-entries
  color: #DC143C
  filter: drop-shadow(5px 10px 4px rgba(0, 0, 0, 0.25))
  border-radius: 20px
  background: #FAA0A0
  border: 3px solid #DC143C
  width: 100%
  height: auto
  line-height: 200px
  max-height: 200px
  text-align: center
  display: table-cell
  vertical-align: middle
</style>
