import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import requestHandler from "@/services/request.service.js";

Vue.config.productionTip = false;

Vue.mixin({
  methods: {
    jumpScreen: function(newScreen) {
      console.log("Funciona");
      this.$router.replace({ name: newScreen });
      this.$store.dispatch("changeViewState", newScreen);
    }
  }
});

new Vue({
  router,
  store,
  requestHandler,
  render: h => h(App)
}).$mount("#app");
