<script setup>
import { ref, watch } from "vue";
import { getUserInvoices } from "../services/invoiceService";
import { loginStore } from "../stores/loginStore";
import { Loading } from "quasar";

const dialog = ref(false);
const invoice = ref(null);

const login = loginStore();
const invoices = ref(null);
const tableRef = ref();

const pagination = ref({
  sortBy: "id",
  descending: false,
  page: 1,
  rowsPerPage: 5,
});

const pagesNumber = ref(0);
const loading = ref(false);

await updateInvoices({
  pagination: {
    sortBy: "id",
    descending: false,
    page: 1,
    rowsPerPage: 5,
  },
});

async function updateInvoices(props) {
  loading.value = true;

  const { page, rowsPerPage, sortBy, descending } = props.pagination;

  pagination.value.page = page;
  pagination.value.rowsPerPage = rowsPerPage;
  pagination.value.sortBy = sortBy;
  pagination.value.descending = descending;

  const fetchCount = rowsPerPage === 0 ? pagination.value.rowsNumber : rowsPerPage

  await getUserInvoices(
    login.login,
    page,
    fetchCount
  ).then((res) => {
    if (res.invoices !== undefined && res.total !== undefined) {
      invoices.value = res.invoices;
      pagination.value.rowsNumber = res.total;
      pagesNumber.value = Math.ceil(
        pagination.value.rowsNumber / pagination.value.rowsPerPage
      );
      console.log(pagination.value);
    } else {
      invoices.value = [];
      pagination.value.rowsNumber = 0;
      pagesNumber.value = 0;
    }
  });

  loading.value = false;
}

const columns = [
  {
    name: "id",
    required: true,
    label: "ID",
    field: (invoice) => invoice.id,
    format: (val) => `${val}`,
    sortable: true,
    align: "left",
  },
  {
    name: "invoice_date",
    required: true,
    label: "Invoice Date",
    field: (invoice) => invoice.invoiceDate,
    format: (val) => new Date(val).toLocaleDateString(),
    sortable: true,
    align: "left",
  },
  {
    name: "due_date",
    required: true,
    label: "Due Date",
    field: (invoice) => invoice.dueDate,
    format: (val) => new Date(val).toLocaleDateString(),
    sortable: true,
    align: "left",
  },
  {
    name: "order",
    required: true,
    label: "Order",
    field: "action",
    sortable: true,
    align: "left",
  },
  {
    name: "provider",
    required: true,
    label: "Provider",
    field: "action",
    sortable: true,
    align: "left",
  },
  {
    name: "payment_date",
    required: true,
    label: "Payment Date",
    field: (invoice) => invoice.paymentDate,
    format: (val) => {
      if (val === null) return "---";
      return new Date(val).toLocaleDateString();
    },
    sortable: true,
    align: "left",
  },
  {
    name: "pay",
    required: true,
    label: "Pay",
    field: "action",
    sortable: true,
    align: "left",
  },
  {
    name: "download",
    required: true,
    label: "Download",
    field: "action",
    sortable: true,
    align: "left",
  },
];

function logging(value) {
  console.log(value);
}
</script>

<template>
  <div class="q-pa-md invoices">
    <q-table
      ref="tableRef"
      title="Invoices"
      :rows="invoices"
      :columns="columns"
      color="primary"
      row-key="id"
      :loading="loading"
      separator="vertical"
      v-model:pagination="pagination"
      @request="updateInvoices"
    >
      <template v-slot:body-cell-order="props">
        <q-td :props="props">
          <q-btn @click="logging(props.value)"> Order </q-btn>
        </q-td>
      </template>
      <template v-slot:body-cell-provider="props">
        <q-td :props="props">
          <q-btn @click="logging(props)"> Provider </q-btn>
        </q-td>
      </template>
      <template v-slot:body-cell-pay="props">
        <q-td :props="props">
          <q-btn @click="logging(props)" v-if="props.row.paymentDate === null">
            Pay
          </q-btn>
        </q-td>
      </template>
      <template v-slot:body-cell-download="props">
        <q-td :props="props">
          <q-btn @click="logging(props)"> Download </q-btn>
        </q-td>
      </template>
    </q-table>
  </div>
</template>

<style scoped lang="sass">
.invoices
  max-width: 100%
  min-width: 80%
  text-align: center
</style>
