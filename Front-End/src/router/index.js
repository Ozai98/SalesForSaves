import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);
const router = new VueRouter({
  mode: "history",
  routes: [
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
  ]
});

export default router;
