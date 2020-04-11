<template>
  <div id="app">
    <NavBar idPage="SearchBar"></NavBar>
    <router-view />
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "App",
  data() {
    return {
      authenticated: false,
      mockAccount: {
        username: "nraboy",
        password: "password"
      }
    };
  },
  mounted() {
    this.$router.replace("Home");
  },
  components: {
    NavBar
  },
  methods: {
    setAuthenticated(status) {
      this.authenticated = status;
    },
    logout() {
      this.authenticated = false;
    }
  }
};
</script>
<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
#navBar {
  position: absolute;
  top: 0%;
  left: 0%;
  height: 5%;
  width: 5%;
}
</style>
