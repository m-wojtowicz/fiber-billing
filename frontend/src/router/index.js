import { createRouter, createWebHistory } from "vue-router";
<<<<<<< HEAD
import HomeView from "../views/HomeView.vue";
=======
import { loginStore } from "../stores/loginStore";
import { exitSession, refreshToken } from "../services/loginService";
import { storeToRefs } from "pinia";
>>>>>>> develop

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/AboutView.vue"),
    },
    {
      path: "/calendar",
      name: "calendar",
      component: () => import("../views/CalendarView.vue"),
      meta: { requiresAuth: true },
    },
  ],
});

<<<<<<< HEAD
=======
router.beforeEach(async (to, from) => {
  const user = loginStore();
  if (to.meta.requiresAuth && !user.isLogged()) {
    return {
      path: "/",
      query: { redirect: to.fullPath },
    };
  }
  if (from.name !== "login" && user.isLogged()) {
    const oldToken = user.token;
    const { token } = storeToRefs(user);
    refreshToken(oldToken)
      .then((r) => (token.value = r.data))
      .catch(() => {
        exitSession(oldToken);
        user.$reset();
        router.replace({ name: "login" });
      });
  }
});

>>>>>>> develop
export default router;
