<template>
  <div class="card" @click="jumpBookings(product.id)">
    <img :src="getImage()" class="picture" />
    <div class="card-body">
      <p id="time">{{ product.time }}</p>
      <h5 class="price">${{ product.price }}/kg</h5>
      <h5 id="name">{{ product.name }}</h5>
      <h6>Quedan {{ product.leftUnits }} unidades</h6>
    </div>
  </div>
</template>

<script>
import request from "@/services/request.service.js";
import { timeSince } from "@/services/dateServices.js";
export default {
  props: {
    product: {
      time: "",
      price: Number,
      name: String,
      leftUnits: Number,
      id: Number,
      image: Object,
    },
  },
  methods: {
    jumpBookings(id2) {
      this.$router.push({ name: "Bookings", params: { id: id2 } });
      this.$store.dispatch("changeViewState", "Bookings");
    },
    getImage() {
      return "data:image/jpeg;base64," + this.product.image;
    },
  },
};
</script>

<style>
.card {
  border: 1px solid #ff8e43;
  border-radius: 1vw;
  width: 100%;
  box-sizing: border-box;
  display: block;
  max-height: 30vw;
  max-width: 17vw;
}
.card-body {
  padding: 1vw;
}
.picture {
  width: 100%;
  border-top-left-radius: 1vw;
  border-top-right-radius: 1vw;
  max-width: 100%;
  height: auto;
  max-height: 10vw;
  vertical-align: middle;
  border-style: none;
}
#time {
  font-weight: lighter;
  font-size: 1vw;
  margin-left: 65%;
}
.price {
  font-size: 3vw;
  font-weight: bold;
  color: #ff8e43;
}
#name {
  font-weight: lighter;
  font-size: 2vw;
}
h6 {
  font-family: "Verdana", sans-serif;
  font-weight: normal;
}
</style>
