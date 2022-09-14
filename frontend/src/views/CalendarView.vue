<script setup>
import { sendProcessUpdate } from "../services/orderService";
import { updateOrderStatus } from "../services/calendarService";
import { orderStore } from "../stores/orderStore";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";

const $q = useQuasar();
const orderId = orderStore();

const router = useRouter();

const submit = async () => {
  await sendProcessUpdate(orderId.id);
  await updateOrderStatus(orderId.id);
  router.push({ name: "home" });
  $q.notify({
          message: `Order submitted succesfully`,
          color: "green",
        });
};
</script>

<template>
  <h6>Gratulacje twoje zamówienie zostało pomyślnie złożone</h6>
  <q-btn class="submit_btn" @click="submit">Submit order</q-btn>
</template>

<style scoped lang="sass">
.submit_btn
  background-color: #1976D2
  color: #FFFFFF
</style>
