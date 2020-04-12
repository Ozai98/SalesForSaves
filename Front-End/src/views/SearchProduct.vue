<template>
  <div class="SearchView">
    <NavBar idPage="ProductSearch"></NavBar>
    <div class="contenedor">
      <!-- se maneja un v-for aqui xd -->
      <producto class="producto"></producto>
    </div>
  </div>
</template>

<script>
import producto from "@/components/producto.vue";
import NavBar from "@/components/NavBar.vue";
export default {
  name: "SearchView",
  components: {
    NavBar,
    producto,
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
  font-size: 35px;
  margin: 0%;
  text-align: center;
}

.search-bar {
  margin: auto;
}
.search-bar button {
  padding: 6px 10px;
  background: #ddd;
  font-size: 17px;
  border: none;
  cursor: pointer;
}
.navegacion {
  background-color: #a1ffca;
  position: fixed;
  width: 100%;
  height: 150px;
  top: 0;
  left: 0;
}

.contenedor {
  display: flex;
  justify-content: space-around;
  margin-top: 15%;
}
</style>
