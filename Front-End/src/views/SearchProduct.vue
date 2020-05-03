<template>
  <div id="container">
    <div id="sDiv"></div>
    <SearchBar id="s-el" @search="bringFromBack()"></SearchBar>
    <div id="products" v-for="prod in dataProd" :key="prod.name">
      <Producto :product="prod"></Producto>
    </div>
  </div>
</template>

<script>
import Producto from "@/components/Producto.vue";
import request from "@/services/request.service.js";
import SearchBar from "@/components/SearchBar.vue";
export default {
  data() {
    return {
      dataProd: Object
    };
  },
  name: "SearchView",
  components: {
    Producto,
    SearchBar
  },
  methods: {
    bringFromBack() {
      this.dataProd = [];
      request.buscarProducto(this.$store.getters.returnSearchedValue, data => {
        if (data.ok) {
          console.log(data);
          console.log("Producto encontrado");
          for (const prod of data.clase) {
            this.dataProd.push({
              time: new Date(prod.fechaPublicacion),
              price: prod.precio,
              name: prod.nombre,
              leftUnits: prod.cantidad
            });
          }
          console.log(this.dataProd);
        } else console.log("Error al encontrar producto");console.log(data);
      });
    }
  },
  mounted() {
    this.bringFromBack();
  }
};
</script>

<style>
#container {
  display: flexbox;
  height: 90%;
  background-color: white;
}
#sDiv {
  height: 2vw;
  width: 100%;
}
#s-el {
  margin: auto;
}
#products {
  padding: 2vw;
  display: grid;
  grid-gap: 2vw;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
}
</style>
