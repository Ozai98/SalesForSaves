<template>
  <div class="contenedor-nogrid">
    <center>
      <p class="tituloH">TU HISTORIAL  {{id}} </p>
      <!--<div id="products" v-for="prod in dataProd" :key="prod.name">
         <Producto :product="prod"></Producto>
      </div>-->
      
      <Cosa class="j"></Cosa>
    </center>
  </div>
</template>

<script>
import Cosa from "../components/Hystorial-product";
import request from "@/services/request.service.js";
export default {
  name: "HView",
    data() {
    return {
      id: this.$store.getters.returnUser.id,
      dataH: Object
    };
    },
    
    methods: {
    Backbb() {
      this.dataH = [];
      request.getHistoricbyId(id, data => {
        if (data.ok) {
          console.log(data);
          console.log("pos si sirvio el historial");
          /*
          for (const hist of data.clase) {
            this.dataH.push({
              precio:hist.producto.precio,
              tiempo:hist.tiempo_reserva,
              proveedor:hist.producto.
              nombre
            });
          }*/
          console.log(this.dataProd);
        } else console.log("hijole no se va a podeeer");console.log(data);
      });
    }
  },
  components: {
    Cosa
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
.j {
  margin: 1vw;
}
</style>
