<script setup>
import { ref, computed } from "vue";
import { useQuasar } from "quasar";
import { useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { registerStore } from "../stores/register";
import ClientTypeChooser from "../components/ClientTypeChooser.vue";
import UserDataForm from "../components/UserDataForm.vue";
import AddressFormVue from "../components/AddressForm.vue";
import {
  registerKeycloak,
  registerUser,
  registerAddress,
  registerClientData,
} from "../services/registerService";

const router = useRouter();

const register = registerStore();
const { clientType, name, surname, email, login, password, repetPassword } =
  storeToRefs(register);

const step = ref(1);
const $q = useQuasar();

$q.notify.setDefaults({
  position: "top-right",
  timeout: 2000,
  textColor: "white",
  actions: [{ icon: "close", color: "white" }],
});

const validateUserData = computed(() => {
  if (
    clientType.value === "regular" &&
    name.value !== "" &&
    name.value.length < 46 &&
    surname.value !== "" &&
    surname.value.length < 46 &&
    email.value !== "" &&
    email.value.length < 46 &&
    email.value.match(/\S+@\S+\.\S+/) &&
    login.value !== "" &&
    password.value !== "" &&
    repetPassword.value !== "" &&
    email.value.match(/\S+@\S+\.\S+/) &&
    password.value === repetPassword.value
  )
    return true;
  else if (
    clientType.value === "business" &&
    name.value !== "" &&
    name.value.length < 46 &&
    email.value !== "" &&
    email.value.length < 46 &&
    email.value.match(/\S+@\S+\.\S+/) &&
    login.value !== "" &&
    password.value !== "" &&
    repetPassword.value !== "" &&
    email.value.match(/\S+@\S+\.\S+/) &&
    password.value === repetPassword.value
  )
    return true;
  else return false;
});

const registerScript = async () => {
  let user = {};
  let address = {};
  try {
    await registerUser().then((r) => (user = r.data));
    await registerAddress().then((r) => (address = r.data));
    await registerClientData(user, address).then((r) => console.log(r.data));
    await registerKeycloak();

    $q.notify({
      message: "Successfuly registered. You can login now.",
      type: "positive",
    });

    router.replace({ name: "login" });
  } catch (err) {
    let message = err.message;

    if (err.response.data.errorMessage !== undefined)
      message = err.response.data.errorMessage;
    if (err.response.data.error !== undefined)
      message = err.response.data.error;

    $q.notify({
      message: `${message}`,
      caption: `${err.code}`,
      type: "negative",
    });
  }
};
</script>

<template>
  <main>
    <div class="q-mx-xl q-my-md">
      <h1>Register</h1>
      <hr class="divider" />
    </div>
    <q-stepper
      v-model="step"
      ref="stepper"
      alternative-labels
      animated
      done-color="light-green-13"
      active-color="blue-13"
      inactive-color="blue-grey-3"
      class="q-mx-xl q-my-md"
    >
      <q-step :name="1" title="Client type" icon="settings" :done="step > 1">
        <ClientTypeChooser />
      </q-step>

      <q-step :name="2" title="User data" icon="person" :done="step > 2">
        <UserDataForm />
      </q-step>

      <q-step :name="3" title="Address" icon="home">
        <AddressFormVue />
      </q-step>

      <template v-slot:navigation>
        <q-stepper-navigation>
          <q-btn
            v-if="step == 1"
            @click="$refs.stepper.next()"
            color="primary"
            label="Continue"
            :disable="clientType === '' ? true : false"
          />
          <q-btn
            v-if="step == 2"
            @click="$refs.stepper.next()"
            color="primary"
            label="Continue"
            :disable="!validateUserData"
          />
          <q-btn
            v-if="step == 3"
            @click="registerScript()"
            color="primary"
            label="Finish"
          />
          <q-btn
            v-if="step > 1"
            flat
            color="primary"
            @click="$refs.stepper.previous()"
            label="Back"
            class="q-ml-sm"
          />
        </q-stepper-navigation>
      </template>
    </q-stepper>
    <p class="q-mx-xl q-mt-lg">
      Already registered?
      <router-link class="router" :to="{ name: 'login' }">
        Sign in
      </router-link>
    </p>
  </main>
</template>

<style scoped>
main {
  padding: 0 60px 0 60px;
  display: grid;
  background-color: #64b5f6;
  box-shadow: 10px 10px 5px lightblue;
  border-radius: 15px;
  min-width: 300px;
}

h1 {
  font-size: 60px;
  font-weight: bold;
  text-decoration: none;
  color: rgb(255, 255, 255);
}

.divider {
  width: 250px;
  border: 0;
  border-top: 3px solid #ffffff;
}

.stepper {
  margin-top: 5%;
  margin-left: 15%;
  margin-right: 15%;
}

.login-details-inputs {
  margin-top: 2%;
  font-size: large;
}

.router {
  text-decoration: none;
  color: #1976d2;
}
</style>
