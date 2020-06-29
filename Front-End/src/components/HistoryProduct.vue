<template>
  <div class="summaryH">
    <img :src="getImage()" class="pictureH" />
    <div class="card-bodyH">
      <p class="timeH">{{ getSinceDate() }}</p>
      <h5 class="priceH">
        ${{ historicalProduct.price * historicalProduct.quantity }}
      </h5>
      <h6 id="nameH">{{ historicalProduct.name }}</h6>
      <p class="providerH" @click="changestate()">
        En {{ historicalProduct.provider.name }}
      </p>
      <p class="quantityH">{{ historicalProduct.quantity }} unidades</p>
    </div>
  </div>
</template>

<script>
import request from "@/services/request.service.js";
import { timeSince } from "@/services/dateServices.js";
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
      console.log(this.historicalProduct.time);
      let since = timeSince(this.historicalProduct.time);
      return since;
    },
    changestate() {
      if (this.$store.getters.returnHysState) {
        this.$store.dispatch("updateStateH", this.historicalProduct.provider);
      }
    },
    getImage() {
      return "data:image/jpeg;base64," + this.historicalProduct.image;
    },
  },
};
</script>

<style>
.summaryH {
  border: 1px solid #ff8e43;
  border-radius: 1vw;
  box-sizing: border-box;
  display: inline-block;
  max-height: 15vw;
  max-width: 65vw;
  text-align: left;
}
.card-bodyH {
  float: right;
  padding-left: 1vw;
  width: 50vw;
  display: grid;
  font-family: verdana, Verdana, Geneva, Tahoma, sans-serif;
}
.pictureH {
  height: 9.9vw;
  float: left;
  border-radius: 1vw 0 0 1vw;
  max-width: 13vw;
  width: auto;
  vertical-align: middle;
  border-style: none;
}
.timeH {
  color: #888;
  font-weight: lighter;
  font-size: 0.8vw;
  margin-left: 82.5%;
  margin-top: 1vw;
  grid-column: 1/3;
  grid-row: 1;
  text-align: center;
}
.priceH {
  font-size: 3vw;
  font-weight: bold;
  color: #ff8e43;
  text-align: left;
  grid-column: 1;
  grid-row: 1;
  font-family: "Oswald", sans-serif;
}
.providerH {
  font-weight: lighter;
  color: #888;
  cursor: pointer;
  font-size: 1.2vw;
  margin-left: 80%;
  grid-column: 1/3;
  grid-row: 4;
  text-align: right;
  padding-right: 1vw;
}
.providerH:hover {
  color: #ff8e43;
}
.quantityH {
  font-weight: lighter;
  font-size: 1.2vw;
  grid-column: 1;
  color: #888;
  padding-top: 0.5vw;
  grid-row: 4;
}
#nameH {
  font-weight: lighter;
  font-size: 1.2vw;
  color: #888;
  text-align: left;
  grid-row: 3;
  grid-column: 1;
}
</style>
