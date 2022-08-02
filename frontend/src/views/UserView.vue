<script setup>
import { ref, onMounted } from "vue";
import {
  getUserData,
  putUserData,
  getClientType,
} from "../services/userService";
import { loginStore } from "../stores/loginStore";

const disable = ref(true);
const isEdited = ref(false);

const clientType = ref("");

const state = ref({
  name: "",
  surname: "",
  emailAddress: "",
  country: "",
  city: "",
  street: "",
  streetNumber: "",
  houseNumber: "",
  zipCode: "",
  postOffice: "",
});

const login = loginStore();

onMounted(() => {
  getUserData(login.login).then((r) => {
    state.value = r.data;
  });

  getClientType(login.login).then((r) => {
    clientType.value = r.data;
    console.log(clientType);
  });
});

const edit = () => {
  disable.value = !disable.value;
  isEdited.value = !isEdited.value;
  putUserData(state.value);
};

const save = () => {
  disable.value = !disable.value;
  isEdited.value = !isEdited.value;
};
</script>

<template>
  <div>
    <h3 class="row q-mx-md">Account</h3>
    <div class="row q-gutter-xl">
      <div class="col q-gutter-md">
        <q-input
          class="text-input"
          outlined
          v-model="state.name"
          label="Name"
          :disable="disable"
          v-if="clientType == 'regular'"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.surname"
          label="Surname"
          :disable="disable"
          v-if="clientType == 'regular'"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.name"
          label="Company name"
          :disable="disable"
          v-if="clientType == 'business'"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.email"
          label="Email"
          :disable="disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.country"
          label="Country"
          :disable="disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.city"
          label="City"
          :disable="disable"
        />
      </div>
      <div class="col q-gutter-md">
        <q-input
          class="text-input"
          outlined
          v-model="state.street"
          label="Street"
          :disable="disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.streetNumber"
          label="Street number"
          :disable="disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.houseNumber"
          label="House number"
          :disable="disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.zipCode"
          label="Zip code"
          :disable="disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.postOffice"
          label="Post office"
          :disable="disable"
        />
      </div>
    </div>
    <q-btn
      v-if="isEdited === false"
      class="buttons q-mx-md q-mt-md"
      @click="save"
      >Edit
    </q-btn>
    <q-btn
      v-if="isEdited === true"
      class="buttons q-mx-md q-mt-md"
      @click="edit"
    >
      Save
    </q-btn>
  </div>
</template>

<style scoped>
.text-input {
  width: 20rem;
}

.buttons {
  font-size: large;
  color: white;
  background-color: #1976d2;
}
</style>
