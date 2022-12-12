<template>
  <q-layout view="hHh LpR lff" container>
    <q-header elevated>
      <NavBar
        @showhidedrawer="showhidedrawer"
        :display-buttons="link !== 'login' && link !== 'register'"
      />
    </q-header>

    <q-drawer
      v-model="drawer"
      :width="292"
      :breakpoint="500"
      overlay
      elevated
      behavior="desktop"
      id="drawer"
    >
      <q-scroll-area class="fit">
        <q-list>
          <q-item
            clickable
            :active="link === 'products'"
            active-class="selected-link"
            :to="{ name: 'products' }"
            v-ripple
          >
            <q-item-section avatar>
              <q-icon name="inventory_2" />
            </q-item-section>
            <q-item-section>My products</q-item-section>
          </q-item>

          <q-item
            clickable
            :active="link === 'orders'"
            active-class="selected-link"
            :to="{ name: 'orders' }"
            v-ripple
          >
            <q-item-section avatar>
              <q-icon name="receipt_long" />
            </q-item-section>
            <q-item-section>My orders</q-item-section>
          </q-item>

          <q-item
            clickable
            :active="link === 'account'"
            active-class="selected-link"
            :to="{ name: 'account' }"
            v-ripple
          >
            <q-item-section avatar>
              <q-icon name="account_balance" />
            </q-item-section>
            <q-item-section>Check account balance</q-item-section>
          </q-item>

          <q-separator spaced />

          <q-item
            clickable
            :active="link === 'terminate'"
            active-class="selected-link"
            :to="{ name: 'terminate' }"
            v-ripple
          >
            <q-item-section avatar>
              <q-icon name="phonelink_erase" />
            </q-item-section>
            <q-item-section>Terminate existing service</q-item-section>
          </q-item>

          <q-item
            clickable
            :active="link === 'change'"
            active-class="selected-link"
            :to="{ name: 'change' }"
            v-ripple
          >
            <q-item-section avatar>
              <q-icon name="phonelink_setup" />
            </q-item-section>
            <q-item-section>Change existing service</q-item-section>
          </q-item>
        </q-list>
      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <q-page
        class="home-page column justify-center items-center"
        style="background: none"
      >
        <Suspense>
          <template #default>
            <router-view />
          </template>
          <template #fallback> Loading </template>
        </Suspense>
      </q-page>
    </q-page-container>

    <q-footer borderless id="footer">
      <q-img src="@/assets/weaves.svg" />
    </q-footer>
  </q-layout>
  
  <q-dialog v-model="cookiesDialog" position="bottom" persistent>
    <q-card>
      <q-card-section class="column items-center no-wrap">
        <div>
          Witryna korzysta z plików cookies. Dalsze korzystanie ze strony oznacza zgodę na ich użycie.
        </div>
      </q-card-section>
      <q-separator />
      <q-card-section>
        <q-btn label="Zaakceptuj pliki cookies" color="primary" @click="consentOk()" />
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script setup>
  import { useQuasar } from "quasar";
  import { ref, watch } from "vue";
  import { useRoute } from "vue-router";
  import NavBar from "./components/NavBar.vue";
  import { useCookies } from "vue3-cookies";
  
  const route = useRoute();
  const drawer = ref(false);
  const { cookies } = useCookies();
  const cookiesDialog = ref(true);
  const link = ref("");

  if (cookies.get("consent") === "True") {
    cookiesDialog.value = false;
  }
  
  function showhidedrawer() {
    drawer.value = !drawer.value;
  };
  
  function consentOk() {
    cookies.set("consent", "True");
    cookiesDialog.value = false;
  };
  
  watch(route, (n) => {
    link.value = n.name;
  });
  
  const $q = useQuasar();
  $q.notify.setDefaults({
    position: "bottom-right",
    timeout: 2000,
    textColor: "white",
    actions: [{ icon: "close", color: "white" }],
  });
  </script>
  

<style lang="sass">
@import "@/assets/base.css"

#app
  height: 100vh
  margin: 0
  display: flex
  flex-direction: column

#drawer
  background: $secondary

#footer
  background: none
  z-index: -1

#content
  align-items: center
  display: flex

.selected-link
  color: black
  background: $selected
</style>
