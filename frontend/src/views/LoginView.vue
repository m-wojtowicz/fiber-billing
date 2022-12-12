<script setup>
import { ref, onMounted, watch } from "vue";
import { useQuasar } from "quasar";
import { storeToRefs } from "pinia";
import { getLoginToken, getUserType } from "../services/loginService";
import { registerStore } from "../stores/register";
import { loginStore } from "../stores/loginStore";
import { useRouter } from "vue-router";
import { getUserData } from "../services/userService";
import { Loading } from "quasar";
import { VueRecaptcha } from "vue-recaptcha";

const router = useRouter();

const register = registerStore();
const user = loginStore();
const { login, token, clientType, userId } = storeToRefs(user);
const password = ref("");
onMounted(() => {
  register.reset();
});

const isPwd = ref(true);
const $q = useQuasar();
const siteKey = "6Lct-nIjAAAAAKgSHivR0xRFjfz8rSQVLXCjshzn";
const captcha = ref(false);

function handleSuccess() {
  captcha.value = true;
};

function handleError() {
  captcha.value = false;
};

$q.notify.setDefaults({
  position: "bottom-right",
  timeout: 2000,
  textColor: "white",
  actions: [{ icon: "close", color: "white" }],
});

const checkData = () => {
  Loading.show();

  getLoginToken(login.value, password.value)
    .then((res) => {
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

  Loading.hide();
};

watch(token, async () => {
  Loading.show();
  if (token.access_token !== "") {
    login.value = login.value.toLowerCase();
    clientType.value = await getUserType(login.value).then(
      (resp) => (resp = resp.data)
    );
    userId.value = await getUserData(login.value).then((resp) => {
      return resp.data.id;
    });
    router.replace({ name: "home" });
    $q.notify({
      message: "Successfuly logged in.",
      type: "positive",
    });
  }
  Loading.hide();
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

    <center>
      <VueRecaptcha
        class = "loggin-input"
        :sitekey="siteKey"
        :load-recaptcha-script="true"
        @verify="handleSuccess"
        @error="handleError"
        @expired="handleError"
      />
    </center>

    <q-btn
      label="Login"
      rounded
      class="logging-button shadow-5"
      @click="checkData(loginInput, passwordInput)"
      :disable="!captcha"
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
