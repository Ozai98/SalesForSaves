<template>
  <div class="recuentoH">
    <img src="@/assets/imgs/banano.jpg" class="imagenH" />
    <div class="card-bodyH">
      <p class="tiempoH">
        Hace {{ getSinceDate() }} días
      </p>
      <h5 class="precioH">{{Historical_product.precio}}/kg</h5>
      <h6 id="nombreh">{{Historical_product.nombre}}</h6>
      <p class="tiendaH"> {{Historical_product.proveedor}}</p>
      <p class="cantidadH">{{Historical_product.cantidad}} unidades</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    Historical_product: {
      tiempo: Date,
      precio: Number,
      nombre: String,
      proveedor: String,
      cantidad: Number
    }
  },
  methods: {
    getSinceDate() {
      let current = new Date();
      let diff = current - this.Historical_product.time;
      let days = diff / (1000 * 3600 * 24);
      return Math.floor(days);
    }
  }
};
</script>

<style>
.recuentoH {
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
  font-family: verdana,Verdana, Geneva, Tahoma, sans-serif;
}
.imagenH {
  height: 9.9vw;
  float: left;
  border-radius: 1vw;
  max-width: 100%;
  width: auto;
  vertical-align: middle;
  border-style: none;

}
.tiempoH {
  font-weight: lighter;
  font-size: 1vw;
  margin-left: 85%;
  grid-column: 1/3;
  grid-row: 1;
}
.precioH {
  font-size: 2vw;
  font-weight: bold;
  color: #ff8e43;
  text-align: left;
  grid-column: 1;
  grid-row: 2;
}
.tiendaH {
    font-weight: lighter;
  font-size: 1vw;
  margin-left: 80%;
  grid-column: 1/3;
  grid-row: 4;
}
.cantidadH{
      font-weight: lighter;
  font-size: 2vw;
  grid-column: 1;
  grid-row: 4;
}
#nombreh {
  font-weight: lighter;
  font-size: 2vw;
  text-align: center;
  grid-row: 3;
  grid-column: 1/3;
}
</style>