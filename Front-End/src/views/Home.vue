<template>
  <div id="home">
    <NavBar idPage="MainHome"></NavBar>
    <div class="titulo">
      <h1>SALES FOR SAVES</h1>
    </div>
    <SearchBar class="search-bar"></SearchBar>
  </div>
</template>

<script>
// @ is an alias to /src
import SearchBar from "@/components/SearchBar.vue";
import NavBar from "@/components/NavBar.vue";

export default {
  name: "Home",
  components: {
    SearchBar,
    NavBar
  }
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
.titulo {
  font-family: Oswald;
  font-size: 100px;
  margin: 10% 10%;
}

.search-bar button {
  padding: 6px 10px;
  background: #ddd;
  font-size: 17px;
  border: none;
  cursor: pointer;
}
#home {
  background-color: #a1ffca;
  position: absolute;
  top: 0%;
  left: 0%;
  height: 100%;
  width: 100%;
}
</style>
