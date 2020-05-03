<template>
  <div class="contenedor-nogrid">
    <center>
      <p class="tituloH">TU HISTORIAL </p>
      <div id="j" v-for="historial in dataH" :key="historial.nombre">
         <Hystorial :Historical_product="historial"></Hystorial>
      </div>

      
    </center>
  </div>
</template>

<script>
import Hystorial from "../components/Hystorial-product";
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
      console.log("si me llamaron");
      this.dataH = [];
      request.getHistoricbyId(this.id, data => {
        if (data.ok) {
          console.log(data);
          console.log("pos si sirvio el historial");
          
          for (const hist of data.clase) {
            this.dataH.push({
              tiempo:hist.tiempoReserva,
              precio:hist.producto.precio,
              nombre:hist.producto.nombre,
              proveedor:hist.producto.proveedor.nombre   
            });
          }
          console.log(this.dataProd);
        } else console.log("hijole no se va a podeeer");console.log(data);
      });
    }
  },
  components: {
    Hystorial
  },
    mounted() {
    this.Backbb();
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
#j {
  margin: 1vw;
}
</style>
