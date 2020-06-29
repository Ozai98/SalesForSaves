<template>
  <div id="home" class="center-content">
    <div class="title">
      <h1>SALES FOR SAVES</h1>
    </div>
    <SearchBar></SearchBar>
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
  methods: {
    mounted() {
      this.$router.dispatch();
    },
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
  height: 90%;
}
</style>
