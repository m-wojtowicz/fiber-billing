import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
    },
    {
      path: "/login",
      name: "login",
      component: () => import("../views/LoginView.vue"),
    },
    {
      path: "/register",
      name: "register",
      component: () => import("../views/RegistrationView.vue"),
    },
    {
      path: "/user",
      name: "user",
    },
    {
      path: "/products",
      name: "products",
    },
    {
      path: "/orders",
      name: "orders",
    },
    {
      path: "/account",
      name: "account",
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

export default router;
