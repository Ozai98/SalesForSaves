import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/Home",
    name: "Home",
    component: () => import("@/views/Home.vue")
  },
  {
    path: "/ProfilePage",
    name: "ProfilePage",
    component: () => import("@/views/ProfilePage.vue")
  },
  {
    path: "/Login",
    name: "Login",
    component: () => import("@/components/login.vue")
  },
  {
    path: "/Register",
    name: "Register",
    component: () => import("@/components/register.vue")
  },
  {
    path: "/EditProfile",
    name: "EditProfile",
    component: () => import("@/components/editProfile.vue")
  },
  {
    path: "/SearchProduct",
    name: "SearchProduct",
    component: () => import("@/views/SearchProduct.vue")
  },
  {
    path: "/SellProduct",
    name: "SellProduct",
    component: () => import("@/views/SellProduct.vue")
  }
];

const router = new VueRouter({
  routes
});

export default router;
