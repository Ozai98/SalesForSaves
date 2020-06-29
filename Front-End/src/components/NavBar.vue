<template>
  <div id="navBar" @search="search()">
    <img
      @click="jumpScreen('messages')"
      id="msg"
      v-if="this.$store.getters.returnLogState"
      src="@/assets/imgs/chat2.svg"
    />
    <img
      v-if="this.$store.getters.returnUser.isProvider"
      id="sellLink"
      @click="jumpScreen('SellProduct')"
      src="@/assets/imgs/paperBag1.svg"
    />
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
    log: Boolean,
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
  top: 0.5vw;
  right: 4vw;
  width: 2vw;
  height: 2vw;
  cursor: pointer;
}
#msg {
  position: absolute;
  top: 0.5vw;
  right: 7vw;
  width: 2vw;
  height: 2vw;
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
