<template>
  <div id="home" class="center-content">
    <div class="title">
      <h1>SALES FOR SAVES</h1>
    </div>
    <SearchBar @search="jumpScreen('SearchProduct')"></SearchBar>
    <CategorySearch @search2="jumpScreen('SearchProduct')"></CategorySearch>
  </div>
</template>

<script>
// @ is an alias to /src
import SearchBar from "@/components/SearchBar.vue";
import CategorySearch from "@/components/CategorySearch.vue";

export default {
  name: "Home",
  components: {
    SearchBar,
    CategorySearch,
  },
};
</script>

<style scoped>
.title {
  font-size: 6vw;
}
#home {
  background-color: #a1ffca;
  width: 100%;
  height: 100%;
}
</style>
