<template>
  <div class="BackCl" id="componen">
    <div class="imgCl">
      <img :src="getImage()" />
    </div>
    <div class="nombreCl">
      <p style="font-size: 1vw;">NOMBRE</p>
      <p>{{ this.clasication.nombre }}</p>
    </div>
    <starts class="estrellas" :mainData="clasication"></starts>
  </div>
</template>

<script>
import starts from "./Stars";
import request from "../services/request.service.js";
export default {
  props: {
    clasication: {
      nombre: String,
      picture: String,
      Userid: Number,
      Provid: Number,
    },
  },
  methods: {
    getImage() {
      return "data:image/jpeg;base64," + this.clasication.picture;
    },
  },
  components: {
    starts,
  },
  mounted() {
    console.log(
      "ACTUAL" +
        this.clasication.Userid +
        " " +
        this.clasication.Provid +
        " " +
        this.clasication.picture
    );
  },
  destroyed() {
    if (!this.$store.getters.returnHysState) {
      this.$store.dispatch("updateStateH", null);
    }
  },
};
</script>

<style scoped>
.BackCl {
  background-color: #a1ffca;
  border-top-right-radius: 25px;
  border-bottom-right-radius: 25px;
}
.imgCl img {
  margin-top: 6vw;
  width: 50%;
  height: 50%;
  border-radius: 100%;
}
.nombreCl {
  font-family: "Oswald", sans-serif;
  padding: 2vw;
  font-size: 2vw;
}
</style>
