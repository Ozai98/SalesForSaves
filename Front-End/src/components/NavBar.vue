<template>
  <div id="navBar" @search="search()">
    <p
      v-if="this.$store.getters.returnUser.isProvider"
      id="sellLink"
      @click="jumpScreen('SellProduct')"
    >
      VENDER
    </p>
    <div id="nav1" v-if="idPage == 'Home'">
      <UserIcon class="icon" frameSize="2"></UserIcon>
    </div>
    <div v-else id="nav2">
      <UserIcon class="icon" frameSize="2"></UserIcon>
      <img
        class="logo"
        src="@/assets/imgs/pageIcon.svg"
        alt="logo"
        @click="jumpScreen('Home')"
      />
      <SearchBar id="searchBar"></SearchBar>
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
  },
  components: {
    UserIcon,
    SearchBar,
  },
  methods: {
    openLogin() {
      this.$emit("openLogin2");
    },
  },
};
</script>

<style scoped>
#navBar {
  height: 10%;
  width: 100%;
  max-height: 100%;
  background-color: #a1ffca;
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
#searchBar {
  position: absolute;
  margin: 1.4vw 28.5vw;
}
.icon {
  position: absolute;
  top: 0.5vw;
  right: 0.5vw;
  width: 2vw;
  height: 2vw;
}
#nav2 h1:hover {
  cursor: pointer;
}
</style>
