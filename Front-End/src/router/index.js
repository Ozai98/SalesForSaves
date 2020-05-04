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
      path: "/Hystory",
      name: "Hystory",
      component: () => import("@/views/Hystory.vue")
    },
    {
      path: "/ProfileView",
      name: "ProfileView",
      component: () => import("@/views/ProfileView.vue")
    },
    {
      path: "/Login",
      name: "Login",
      component: () => import("@/views/Login.vue")
    },
    {
      path: "/Register",
      name: "Register",
      component: () => import("@/views/Register.vue")
    },
    {
      path: "/EditProfile",
      name: "EditProfile",
      component: () => import("@/views/EditProfile.vue")
    },
    {
      path: "/SearchProduct",
      name: "SearchProduct",
      component: () => import("@/views/SearchProduct.vue"),
      props: {
        searchedValue: String
      }
    },
    {
      path: "/Reservas",
      name: "Reservas",
      component: ()=> import("@/views/Reservas.vue")
    },
    {
      path: "/SellProduct",
      name: "SellProduct",
      component: () => import("@/views/SellProduct.vue")
    }
  ]
});

export default router;
