<template>
  <div id="home">
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <div class="titulo">
      <h1>SALES FOR SAVES</h1>
    </div>
    <SearchBar class="search-bar"></SearchBar>
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
  margin-top: 5%;
  background-color: #a1ffca;
  position: absolute;
  top: 0%;
  left: 0%;
  height: 100%;
  width: 100%;
}
</style>
