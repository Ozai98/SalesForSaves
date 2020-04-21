<template>
  <div id="navBar">
    <p id="sellLink" @click="jumpScreen('SellProduct')">VENDER</p>
    <div id="nav1" v-if="idPage == 'Home'">
      <UserIcon v-bind:isLogged="true"></UserIcon>
    </div>
    <div class="center-content" id="nav2" v-if="idPage == 'SearchProduct'">
      <UserIcon></UserIcon>
      <img
        class="logo "
        src="@/assets/imgs/pageIcon.svg"
        alt="logo"
        @click="jumpScreen('Home')"
      />
      <SearchBar class=""></SearchBar>
    </div>
    <div id="nav3" v-if="idPage == 'Login' || idPage == 'Register'">
      <img
        class="logo "
        src="@/assets/imgs/pageIcon.svg"
        alt="logo"
        @click="jumpScreen('Home')"
      />
    </div>
    <div id="nav4" v-else>
      <UserIcon v-bind:isLogged="true"></UserIcon>
      <img
        class="logo "
        src="@/assets/imgs/pageIcon.svg"
        alt="logo"
        @click="jumpScreen('Home')"
      />
    </div>
  </div>
</template>

<script>
import UserIcon from "@/components/UserIcon.vue";
import SearchBar from "@/components/SearchBar.vue";
export default {
  name: "NavBar",
  props: {
    idPage: String
  },
  components: {
    UserIcon,
    SearchBar
  }
};
</script>

<style scoped>
#navBar {
  height: 10%;
  width: 100%;
}
#nav1 {
  background-color: #a1ffca;
}
#nav2 {
  background-color: #a1ffca;
}
#nav3 {
  background-color: #ff8e43;
  width: 100%;
  height: 100%;
}
#sellLink {
  position: absolute;
  top: 0.3vw;
  right: 4vw;
  font-family: "Oswald", sans-serif;
  font-weight: bold;
  font-size: 1.5vw;
}
#sellLink:hover {
  cursor: pointer;
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
