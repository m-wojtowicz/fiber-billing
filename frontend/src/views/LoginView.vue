<script setup>
import { ref, onMounted, watch } from "vue";
import { useQuasar } from "quasar";
import { storeToRefs } from "pinia";
import { getLoginToken } from "../services/loginService";
import { registerStore } from "../stores/register";
import { loginStore } from "../stores/loginStore";
import { useRouter } from "vue-router";

const router = useRouter();

const register = registerStore();
const user = loginStore();
const { login, password, token } = storeToRefs(user);

onMounted(() => {
  register.reset();
});

const isPwd = ref(true);
const $q = useQuasar();

$q.notify.setDefaults({
  position: "top-right",
  timeout: 2000,
  textColor: "white",
  actions: [{ icon: "close", color: "white" }],
});

const checkData = () => {
  getLoginToken(login.value, password.value)
    .then((res) => {
      console.log(res);
      token.value = res.data;
    })
    .catch((err) => {
      if (err.response.status === 401) {
        $q.notify({
          message: "Invalid login or password",
          color: "red",
        });
      } else {
        console.log(err);
      }
    });
};

watch(token, () => {
  if (token.access_token !== "") {
    login.value = login.value.toLowerCase();
    router.replace({ name: "home" });

    $q.notify({
      message: "Successfuly logged in.",
      type: "positive",
    });
  }
});
</script>

<template>
  <main>
    <h1>Login</h1>
    <hr class="divider" />
    <q-input
      class="loggin-input"
      outlined
      bg-color="white"
      v-model="login"
      label="Login"
    />

    <q-input
      class="loggin-input"
      outlined
      bg-color="white"
      v-model="password"
      :type="isPwd ? 'password' : 'text'"
      label="Password"
    >
      <template v-slot:append>
        <q-icon
          :name="isPwd ? 'visibility_off' : 'visibility'"
          class="cursor-pointer"
          @click="isPwd = !isPwd"
        />
      </template>
    </q-input>

    <q-btn
      label="Login"
      rounded
      class="logging-button shadow-5"
      @click="checkData(loginInput, passwordInput)"
    />

    <div style="padding: 5% 15% 3% 15%">
      Not registered?
      <router-link class="router" :to="{ name: 'register' }">
        Create account
      </router-link>
    </div>
  </main>
</template>

<style scoped>
main {
  padding: 20px;
  display: grid;
  background-color: #64b5f6;
  box-shadow: 10px 10px 5px lightblue;
  border-radius: 15px;
  min-width: 300px;
  width: 574px;
}

h1 {
  margin-top: 7%;
  font-size: 60px;
  font-weight: bold;
  margin-left: 15%;
  text-decoration: none;
  color: rgb(255, 255, 255);
}

.divider {
  width: 170px;
  margin-left: 15%;
  border: 0;
  border-top: 3px solid #ffffff;
}

.logging-button {
  font-size: large;
  margin: 5% auto 0 auto;
  color: white;
  background-color: #1976d2;
}

.loggin-input {
  margin: 5% 15% 0 15%;
  font-size: large;
}

.router {
  text-decoration: none;
  color: #1976d2;
}

.router:hover {
  color: #023e8a;
}
</style>
