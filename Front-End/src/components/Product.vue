<template>
  <div class="card" @click="jumpBookings(product.id)">
    <img :src="getImage()" class="picture" />
    <div class="card-body">
      <p id="time">Hace {{ getSinceDate() }} días</p>
      <h5 class="price">${{ product.price }}/kg</h5>
      <h5 id="name">{{ product.name }}</h5>
      <h6>Quedan {{ product.leftUnits }} unidades</h6>
    </div>
  </div>
</template>

<script>
import request from "@/services/request.service.js";
export default {
  props: {
    product: {
      time: Date,
      price: Number,
      name: String,
      leftUnits: Number,
      id: Number,
      image: String,
    },
  },
  methods: {
    jumpBookings(id2) {
      //this.jumpScreen('Reservas');
      this.$router.push({ name: "Bookings", params: { id: id2 } });
    },
    getSinceDate() {
      let current = new Date();
      let diff = current - this.product.time;
      let days = diff / (1000 * 3600 * 24);
      return Math.floor(days);
    },
    getImage() {
      return request.getImgUrl(this.product.image);
    },
  },
};
</script>

<style>
.card {
  border: 1px solid #ff8e43;
  border-radius: 15px;
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
</style>
