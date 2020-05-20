<template>
  <div id="container">
    <div id="sDiv"></div>
    <SearchBar id="s-el" @search="bringFromBack()"></SearchBar>
    <div id="products">
      <div v-for="prod in dataProd" :key="prod.name">
        <Product :product="prod"></Product>
      </div>
    </div>
  </div>
</template>

<script>
import Product from "@/components/Product.vue";
import request from "@/services/request.service.js";
import SearchBar from "@/components/SearchBar.vue";
export default {
  data() {
    return {
      dataProd: Object,
    };
  },
  name: "SearchView",
  components: {
    Product,
    SearchBar,
  },
  methods: {
    bringFromBack() {
      this.dataProd = [];
      request.searchProduct(this.$store.getters.returnSearchedValue, (data) => {
        if (data.ok) {
          console.log(data);
          console.log("Producto encontrado");
          for (const prod of data.classX) {
            this.dataProd.push({
              time: new Date(prod.publicationDate),
              price: prod.price,
              name: prod.name,
              leftUnits: prod.quantity,
              id: prod.id,
            });
          }
          console.log(this.dataProd);
        } else console.log("Error al encontrar producto");
      });
    },
  },
  mounted() {
    this.bringFromBack();
  },
};
</script>

<style>
#container {
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
