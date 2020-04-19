<template>
  <div id="home" class="center-content">
    <div class="titulo">
      <h1>SALES FOR SAVES</h1>
    </div>
    <SearchBar id="search-bar"></SearchBar>
  </div>
</template>

<script>
// @ is an alias to /src
import SearchBar from "@/components/SearchBar.vue";

export default {
  name: "Home",
  components: {
    SearchBar
  }
};
</script>

<style scoped>
.titulo {
  font-size: 6vw;
}
#home {
  background-color: #a1ffca;
  width: 100%;
  height: 100%;
}
</style>
