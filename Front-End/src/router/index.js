import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import LoginComponent from "../components/login.vue";
import RegComponent from "../components/register.vue";


Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "login",
    component: LoginComponent
},
{
    path: "/register",
    name: "register",
    component: RegComponent
}
];

const router = new VueRouter({
  routes,
});

export default router;
