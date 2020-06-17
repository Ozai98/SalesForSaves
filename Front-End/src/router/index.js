import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);
const router = new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/Home",
      name: "Home",
      component: () => import("@/views/Home.vue"),
    },
    {
      path: "/History",
      name: "History",
      component: () => import("@/views/History.vue"),
    },
    {
      path: "/ProfileView",
      name: "ProfileView",
      component: () => import("@/views/ProfileView.vue"),
    },
    {
      path: "/EditProfile",
      name: "EditProfile",
      component: () => import("@/views/EditProfile.vue"),
    },
    {
      path: "/SearchProduct/:q",
      name: "SearchProduct",
      component: () => import("@/views/SearchProduct.vue"),
      props: {
        searchedValue: String,
      },
    },
    {
      path: "/Bookings/:id",
      name: "Bookings",
      component: () => import("@/views/Bookings.vue"),
    },
    {
      path: "/SellProduct",
      name: "SellProduct",
      component: () => import("@/views/SellProduct.vue"),
    },
  ],
});

export default router;
