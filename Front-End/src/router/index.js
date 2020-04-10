import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("@/views/Home.vue")
  },
  {
    path: "/ProfileMain",
    name: "ProfilePage",
    component: () => import("@/views/ProfilePage.vue")
  },
  {
    path: "/Login",
    name: "Login",
    component: () => import("@/components/Login.vue")
  },
  {
    path: "/Register",
    name: "Register",
    component: () => import("@/components/Register.vue")
  },
  {
    path: "/EditProfile",
    name: "EditProfile",
    component: () => import("@/components/EditProfile.vue")
  }
];

const router = new VueRouter({
  routes
});

export default router;
