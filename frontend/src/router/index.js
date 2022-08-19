import { createRouter, createWebHistory, useRouter } from "vue-router";
import { loginStore } from "../stores/loginStore";
import { exitSession, refreshToken } from "../services/loginService";
import { storeToRefs } from "pinia";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "login",
      component: () => import("../views/LoginView.vue"),
      meta: { requiresAuth: false },
    },
    {
      path: "/home",
      name: "home",
      component: () => import("../views/HomeView.vue"),
      meta: { requiresAuth: true },
    },
    {
      path: "/register",
      name: "register",
      component: () => import("../views/RegistrationView.vue"),
      meta: { requiresAuth: false },
    },
    {
      path: "/user",
      name: "user",
      component: () => import("../views/UserView.vue"),
      meta: { requiresAuth: true },
    },
    {
      path: "/products",
      name: "products",
      component: () => import("../views/ProductsView.vue"),
      meta: { requiresAuth: true },
    },
    {
      path: "/orders",
      name: "orders",
      component: () => import("../views/OrdersView.vue"),
      meta: { requiresAuth: true },
    },
    {
      path: "/account",
      name: "account",
      component: () => import("../views/AccountView.vue"),
      meta: { requiresAuth: true },
    },
    {
      path: "/new-order",
      name: "new-order",
      component: () => import("../views/NewOrderView.vue"),
      meta: { requiresAuth: true },
    },
    {
      path: "/terminate",
      name: "terminate",
    },
    {
      path: "/change",
      name: "change",
    },
  ],
});

router.beforeEach(async (to, from) => {
  const user = loginStore();
  if (to.meta.requiresAuth && !user.isLogged()) {
    return {
      path: "/",
      query: { redirect: to.fullPath },
    };
  }
  if (from.name !== 'login' && user.isLogged()) {
    const oldToken = user.token;
    const { login, token } = storeToRefs(user);
      refreshToken(oldToken).
      then((r) => (token.value = r.data)).
      catch(() => {
        exitSession(oldToken);
        user.$reset();
        router.replace({name: "login"});
      });
  }
});

export default router;
