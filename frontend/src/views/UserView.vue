<script setup>
import { ref, onMounted } from "vue";
import { getUserData } from "../services/userService";
import { loginStore } from "../stores/loginStore";

const state = ref({
  disable: true,
  isEdited: false,
  name: "",
  surname: "",
  email: "",
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
    state.value.name = r.data.name;
    state.value.surname = r.data.surname;
    state.value.email = r.data.emailAddress;
    state.value.country = r.data.address.country;
    state.value.city = r.data.address.city;
    state.value.street = r.data.address.street;
    state.value.streetNumber = r.data.address.streetNumber;
    state.value.houseNumber = r.data.address.houseNumber;
    state.value.zipCode = r.data.address.zipCode;
    state.value.postOffice = r.data.address.postOffice;
  });
});

const edit = () => {
  state.value.disable = !state.value.disable;
  state.value.isEdited = !state.value.isEdited;
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
          :disable="state.disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.surname"
          label="Surname"
          :disable="state.disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.email"
          label="Email"
          :disable="state.disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.country"
          label="Country"
          :disable="state.disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.city"
          label="City"
          :disable="state.disable"
        />
      </div>
      <div class="col q-gutter-md">
        <q-input
          class="text-input"
          outlined
          v-model="state.street"
          label="Street"
          :disable="state.disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.streetNumber"
          label="Street number"
          :disable="state.disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.houseNumber"
          label="House number"
          :disable="state.disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.zipCode"
          label="Zip code"
          :disable="state.disable"
        />
        <q-input
          class="text-input"
          outlined
          v-model="state.postOffice"
          label="Post office"
          :disable="state.disable"
        />
      </div>
    </div>
    <q-btn
      v-if="state.isEdited === false"
      class="buttons q-mx-md q-mt-md"
      @click="edit"
      >Edit
    </q-btn>
    <q-btn
      v-if="state.isEdited === true"
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
  /* margin: 5% auto 0 auto;s */
  color: white;
  background-color: #1976d2;
}
</style>
