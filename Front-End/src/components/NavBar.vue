<template>
  <nav id="navBar">
    <div id="nav1" v-if="idPage == 'MainHome'">
      <UserIcon isLogged="true"></UserIcon>
    </div>
    <div id="nav2" v-if="idPage == 'ProductSearch'">
      <UserIcon></UserIcon>
      <div class="titulo">
        <h1>SALES FOR SAVES</h1>
      </div>
      <SearchBar class="search-bar"></SearchBar>
    </div>
  </nav>
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
  }
};
</script>

<style scoped>
#navBar {
  position: absolute;
  height: 118px;
  top: 0;
  left: 0;
  width: 100%;
  display: inline-block;
}
#nav1 {
  background-color: #a1ffca;
  height: 100%;
}
#nav2{
  background-color: #ff8e43;
}
</style>
