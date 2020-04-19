<template>
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
</template>

<script>
import Producto from "@/components/Producto.vue";
export default {
  name: "SearchView",
  components: {
    Producto
  }
};
</script>

<style>
.titulo {
  font-family: Oswald;
  font-size: 35px;
  margin: 0%;
  text-align: center;
}
.contenedor {
  padding: 2vw;
  display: grid;
  grid-gap: 2vw;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  height: 90%;
  background-color: white;
}
</style>
