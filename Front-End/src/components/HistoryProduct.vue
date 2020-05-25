<template>
  <div class="summaryH">
    <img :src="getImage()" class="pictureH" />
    <div class="card-bodyH">
      <p class="timeH">Hace {{ getSinceDate() }} días</p>
      <h5 class="priceH">{{ historicalProduct.price }}/kg</h5>
      <h6 id="nameH">{{ historicalProduct.name }}</h6>
      <p class="providerH">{{ historicalProduct.provider.name }}</p>
      <p class="quantityH">{{ historicalProduct.quantity }} unidades</p>
    </div>
  </div>
</template>

<script>
import request from "@/services/request.service.js";
export default {
  props: {
    historicalProduct: {
      time: Date,
      price: Number,
      name: String,
      provider: String,
      quantity: Number,
      image: "",
    },
  },
  methods: {
    getSinceDate() {
      let current = new Date();
      let diff = current - this.historicalProduct;
      let days = diff / (1000 * 3600 * 24);
      return Math.floor(days);
    },
    getImage() {
      return request.getImgUrl(this.historicalProduct.image);
    },
  },
};
</script>

<style>
.summaryH {
  border: 1px solid #ff8e43;
  overflow: hidden;
  border-radius: 15px;
  box-sizing: border-box;
  display: block;
  max-height: 10vw;
  max-width: 70vw;
}
.card-bodyH {
  float: left;
  padding-left: 1vw;
  width: 50vw;
  display: grid;
  font-family: verdana, Verdana, Geneva, Tahoma, sans-serif;
}
.pictureH {
  height: 9.9vw;
  float: left;
  border-radius: 1vw;
  max-width: 10vw;
  width: auto;
  vertical-align: middle;
  border-style: none;
}
.timeH {
  font-weight: lighter;
  font-size: 1vw;
  margin-left: 85%;
  grid-column: 1/3;
  grid-row: 1;
}
.priceH {
  font-size: 2vw;
  font-weight: bold;
  color: #ff8e43;
  text-align: left;
  grid-column: 1;
  grid-row: 2;
}
.providerH {
  font-weight: lighter;
  font-size: 1vw;
  margin-left: 80%;
  grid-column: 1/3;
  grid-row: 4;
}
.quantityH {
  font-weight: lighter;
  font-size: 2vw;
  grid-column: 1;
  grid-row: 4;
}
#nameH {
  font-weight: lighter;
  font-size: 2vw;
  text-align: center;
  grid-row: 3;
  grid-column: 1/3;
}
</style>
