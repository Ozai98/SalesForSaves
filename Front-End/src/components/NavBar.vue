<template>
  <div id="navBar">
    <div id="nav1" v-if="idPage == 'MainHome'">
      <UserIcon v-bind:isLogged=true></UserIcon>
    </div>
    <div id="nav2" v-if="idPage == 'ProductSearch'">
      <UserIcon></UserIcon>
      <div class="titulo">
        <h1 @click="jumpHome()">SALES FOR SAVES</h1>
      </div>
      <SearchBar class="search-bar"></SearchBar>
    </div>
    <div id="nav3" v-if="idPage == 'Login'"></div>
    <div id="nav4" v-if="idPage == 'ProfileData' || idPage == 'EditProfile'">
      <h1 @click="jumpHome()">SALES FOR SAVES</h1>
    </div>
  </div>
</template>

<script>
import UserIcon from "@/components/UserIcon.vue";
import SearchBar from "@/components/SearchBar.vue";
export default {
  name: "NavBar",
  props: {
    idPage: String,
    isLogged: Boolean
  },
  components: {
    UserIcon,
    SearchBar
  },
  methods:{
    jumpHome(){
      this.$router.replace({ name: "Home" });
    }
  }
};
</script>

<style scoped>
#navBar {
  position: absolute;
  height: 170px;
  top: 0;
  left: 0;
  width: 100%;
  display: inline-block;
}
#nav1 {
  background-color: #a1ffca;
  height: 100%;
}
#nav2 {
  background-color: #a1ffca;
  height: 100%;
}
#nav3 {
  background-color: #ff8e43;
  height: 100%;
}
#nav4 {
  background-color: #a1ffca;
  font-family: "Oswald", sans-serif;
}
#nav4 h1 {
  position: absolute;
  top: 20px;
  left: 50px;
  font-size: 40px;
}
#nav4 h1:hover {
  cursor: pointer;
}

</style>
