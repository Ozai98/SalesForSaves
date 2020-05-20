<template>
  <div class="ContainerNoGrid">
    <center>
      <p class="titleH">TU HISTORIAL</p>
      <div id="j" v-for="history in dataH" :key="history.name">
        <HistoryProduct :Historical_product="history"></HistoryProduct>
      </div>
    </center>
  </div>
</template>

<script>
import HistoryProduct from "../components/HistoryProduct.vue";
import request from "@/services/request.service.js";
export default {
  name: "HView",
  data() {
    return {
      id: this.$store.getters.returnUser.id,
      dataH: Object,
    };
  },

  methods: {
    Backbb() {
      this.dataH = [];
      request.getHistoricbyId(this.id, (data) => {
        if (data.ok) {
          for (const hist of data.clase) {
            this.dataH.push({
              time: hist.time,
              price: hist.product.price,
              name: hist.product.name,
              provider: hist.product.provider,
              quantity: hist.quantity,
            });
          }
          console.log(this.dataProd);
        } else console.log("hijole no se va a podeeer");
        console.log(data);
      });
    },
  },
  components: {
    HistoryProduct,
  },
  mounted() {
    this.Backbb();
  },
};
</script>

<style>
.titleH {
  font-family: Oswald;
  font-size: 70px;
  margin: 0%;
  text-align: center;
}
.ContainerNoGrid {
  padding: 2vw;
  align-self: center;
  height: 90%;
  background-color: white;
}
#j {
  margin: 1vw;
}
</style>
