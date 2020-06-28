<template>
  <div id="navBar" @search="search()">
    <p @click="jumpScreen('messages')" id="msg"
    v-if="this.$store.getters.returnLogState"> MENSAJES </p>
    <p
      v-if="this.$store.getters.returnUser.isProvider"
      id="sellLink"
      @click="jumpScreen('SellProduct')"
    >
      VENDER
    </p>
    <div id="nav1" v-if="idPage == 'Home'">
      <UserIcon @login="openLogin()"></UserIcon>
    </div>
    <div v-else id="nav2">
      <UserIcon @login="openLogin()"></UserIcon>
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
    log:Boolean
  },
  components: {
    UserIcon,
    SearchBar,
  },
  methods: {
    openLogin() {
      this.$emit("openLogin2");
    },
  }
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
#msg {
  position: absolute;
  top: 0.3vw;
  right: 12vw;
  font-family: "Oswald", sans-serif;
  font-weight: bold;
  font-size: 1.5vw;
  cursor: pointer;
}
#sellLink:hover {
  cursor: pointer;
}
#searchBar {
  position: absolute;
  margin: 1.4vw 28.5vw;
}

#nav4 h1 {
  position: absolute;
  top: 20px;
  left: 50px;
  font-size: 40px;
}
#nav2 h1:hover {
  cursor: pointer;
}
</style>
