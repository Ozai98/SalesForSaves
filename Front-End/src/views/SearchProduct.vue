<template>
  <div id="container">
    <CategorySearch @search2="bringFromBackCat()"></CategorySearch>
    <div id="products">
      <div v-for="prod in dataProd" :key="prod.id">
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
      dataProd: [],
    };
  },
  name: "SearchView",
  components: {
    Product,
    CategorySearch,
  },
  methods: {
    bringFromBack(query) {
      this.dataProd = [];
      request.searchProduct(query, (data) => {
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
    bringFromBackCat(query) {
      this.dataProd = [];
      request.getByCategory(query, (data) => {
        if (data.ok) {
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
  mounted() {
    this.$root.$on("search", (query) => {
      console.log(query);
      this.bringFromBack(query);
    });
    this.$root.$on("searchCat", (query) => {
      console.log(query);
      this.bringFromBackCat(query);
    });
    this.bringFromBack(this.$route.params.q);
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
  margin-left: 22vw;
  padding: 2vw;
  display: grid;
  grid-gap: 2vw;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
}
</style>
