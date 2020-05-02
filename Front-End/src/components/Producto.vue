<template>
  <div class="carta">
    <img src="@/assets/imgs/banano.jpg" class="imagen" />
    <div class="card-body">
      <p id="tiempo">Hace {{ getSinceDate() }} días</p>
      <h5 class="precio">${{ product.price }}/kg</h5>
      <h5 id="nombre">{{ product.name }}</h5>
      <h6 id="unidades">Quedan {{ product.leftUnits }} unidades</h6>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    product: {
      time: Date,
      price: Number,
      name: String,
      leftUnits: Number
    }
  },
  methods: {
    getSinceDate() {
      let current = new Date();
      let diff = current - this.product.time;
      let days = diff / (1000 * 3600 * 24);
      return Math.floor(days);
    }
  }
};
</script>

<style>
.carta {
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
.imagen {
  width: 100%;
  border-top-left-radius: 1vw;
  border-top-right-radius: 1vw;
  max-width: 100%;
  height: auto;
  vertical-align: middle;
  border-style: none;
}
#tiempo {
  font-weight: lighter;
  font-size: 1vw;
  margin-left: 65%;
}
.precio {
  font-size: 3vw;
  font-weight: bold;
  color: #ff8e43;
}
#nombre {
  font-weight: lighter;
  font-size: 2vw;
}
</style>
