<template>
  <div id="container">
    <CategorySearch @search2="bringFromBackCat()"></CategorySearch>
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
import CategorySearch from "@/components/CategorySearch.vue";
export default {
  data() {
    return {
      dataProd: Object,
    };
  },
  name: "SearchView",
  components: {
    Product,
    CategorySearch,
  },
  methods: {
    bringFromBack() {
      this.dataProd = [];
      request.searchProduct(this.$store.getters.returnSearchedValue, (data) => {
        if (data.ok) {
          console.log(data.classX);
          for (const prod of data.classX) {
            this.dataProd.push({
              time: new Date(prod.publicationDate),
              price: prod.price,
              name: prod.name,
              leftUnits: prod.quantity,
              id: prod.id,
              image: prod.image,
            });
          }
        }
      });
    },
    bringFromBackCat() {
      this.dataProd = [];
      request.getByCategory(this.$store.getters.returnSearchedValue, (data) => {
        if (data.ok) {
          console.log(data.classX);
          for (const prod of data.classX) {
            this.dataProd.push({
              time: new Date(prod.publicationDate),
              price: prod.price,
              name: prod.name,
              leftUnits: prod.quantity,
              id: prod.id,
              image: prod.image,
            });
          }
        }
      });
    },
  },
  updated() {
    this.$root.$on("search", () => {
      this.bringFromBack();
      console.log("buscó");
    });
  },
};
</script>

<style>
#container {
  height: 90%;
  background-color: white;
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
