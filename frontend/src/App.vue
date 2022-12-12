<<<<<<< HEAD
<script setup>
import { RouterLink, RouterView } from "vue-router";
import HelloWorld from "@/components/HelloWorld.vue";
</script>

=======
>>>>>>> develop
<template>
  <header>
    <img
      alt="Vue logo"
      class="logo"
      src="@/assets/logo.svg"
      width="125"
      height="125"
    />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header>

<<<<<<< HEAD
  <RouterView />
</template>

<style>
@import "@/assets/base.css";
=======
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
>>>>>>> develop

#app {
  max-width: 1280px;
  margin: 0 auto;
  padding: 2rem;

  font-weight: normal;
}

header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

a,
.green {
  text-decoration: none;
  color: hsla(160, 100%, 37%, 1);
  transition: 0.4s;
}

@media (hover: hover) {
  a:hover {
    background-color: hsla(160, 100%, 37%, 0.2);
  }
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  body {
    display: flex;
    place-items: center;
  }

  #app {
    display: grid;
    grid-template-columns: 1fr 1fr;
    padding: 0 2rem;
  }

  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>
