<template>
  <div class="SearchView">
    <div class="contenedor">
      <!-- se maneja un v-for aqui xd -->
      <Producto class="producto"></Producto>
      <Producto class="producto"></Producto>
      <Producto class="producto"></Producto>
      <Producto class="producto"></Producto>
      <Producto class="producto"></Producto>
      <Producto class="producto"></Producto>
      <Producto class="producto"></Producto>
    </div>
  </div>
</template>

<script>
import Producto from "@/components/Producto.vue";
export default {
  name: "SearchView",
  components: {
    Producto,
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
  background-color: white;
}
</style>
