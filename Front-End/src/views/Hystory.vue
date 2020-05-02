<template>

  
  <div class="contenedor-nogrid">
    <center>
    <p class="tituloH" >TU HISTORIAL</p>
    <!-- se maneja un v-for aqui xd -->
    <Cosa class="j"></Cosa>
    <Cosa class="j"></Cosa>
    <Cosa class="j"></Cosa>
    <Cosa class="j"></Cosa>
    <Cosa class="j"></Cosa>
    <Cosa class="j"></Cosa>
    <Cosa class="j"></Cosa>
    <Cosa class="j"></Cosa>
    <Cosa class="j"></Cosa>
    <Cosa class="j"></Cosa>
    <Cosa class="j"></Cosa>
    <Cosa class="j"></Cosa>
    </center>
  </div>
  
</template>

<script>
import Producto from "@/components/Producto.vue";
import Cosa from "../components/Hystorial-product";
export default {
  name: "HView",
  components: {
    Producto
    ,Cosa

  }
};
</script>

<style>
.tituloH {
  font-family: Oswald;
  font-size: 70px;
  margin: 0%;
  text-align: center;
}
.contenedor-nogrid {
  padding: 2vw;
  align-self: center;
  height: 90%;
  background-color: white;
}
.j{

  margin: 1vw;
}

</style>