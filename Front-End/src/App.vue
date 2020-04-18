<template>
  <div>
    <div id="container">
      <meta
        name="viewport"
        content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=device-dpi"
      />
      <NavBar :idPage="page"></NavBar>
      <div id="app">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
// import services from "@/services/request.service.js";
export default {
  name: "App",
  components: {
    NavBar
  },
  data() {
    return {
      idPage: "MainHome"
    };
  },
  computed:{
    page(id) {
      id = this.$store.getters.returnView;
      return id;
    }
  },
  mounted() {
    this.$router.replace("home");
    this.$store.dispatch("resetUser");
    this.$store.dispatch("restartLogin");
  }
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
}
#container {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #a1ffca;
}
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  font-family: "Oswald", sans-serif;
  height: 100%;
  margin: auto;
}
#navBar {
  top: 0%;
  left: 0%;
  height: 5%;
  width: 5%;
}
.body-text {
  font-family: Verdana, sans-serif;
}
.base-border {
  border-width: 1px;
  border-style: solid;
  border-color: #ff8e43;
}
.circular-frame {
  border-radius: 50%;
}
.center-content {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.small-img {
  width: 1vw;
  height: 1vw;
}
.soft-el {
  outline: none;
  border: none;
}
.space {
  background-color: #ff8e43;
  width: 1px;
  height: 1.7vw;
  margin-top: 0.2vw;
  margin-right: 0.2vw;
}
</style>
