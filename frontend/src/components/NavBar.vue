<script setup>
import { loginStore } from "../stores/loginStore";
import { exitSession } from "../services/loginService";
import { useRouter } from "vue-router";

defineProps(["displayButtons"]);
const router = useRouter();
const logout = () => {
  const user = loginStore();
  exitSession(user.token).then((r) => console.log(r));
  user.$reset();
  router.replace({ name: "login" });
};
</script>

<template>
  <q-toolbar id="toolbar">
    <div class="row full-width">
      <div class="col text-left q-ma-auto" v-if="displayButtons">
        <q-btn
          flat
          @click="$emit('showhidedrawer')"
          round
          dense
          icon="menu"
          size="25px"
        />
      </div>

      <div class="col text-center q-ma-auto">
        <q-btn flat dense :to="{ name: 'home' }">
          <img src="@/assets/logo1.png" height="50" alt="Application logo" />
        </q-btn>
      </div>

      <div class="col row justify-end" v-if="displayButtons">
        <q-btn
          flat
          round
          dense
          icon="person"
          size="25px"
          style="font-size: 20px"
        >
          <q-menu>
            <q-list style="min-width: 100px">
              <q-item clickable v-close-popup :to="{ name: 'user' }">
                <q-item-section>Profile</q-item-section>
              </q-item>
              <q-separator />
              <q-item clickable v-close-popup @click="logout">
                <q-item-section>Logout</q-item-section>
              </q-item>
            </q-list>
          </q-menu>
        </q-btn>
      </div>
    </div>
  </q-toolbar>
</template>

<style scoped lang="sass">

#toolbar
  left: 0px
  top: 0px
  display: flex
  flex-direction: row
  justify-content: space-between
  align-items: center
  padding: 0px 30px
  background: $primary
</style>
