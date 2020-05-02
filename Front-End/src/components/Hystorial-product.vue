<template>
  <div class="recuento">
    <img src="@/assets/imgs/banano.jpg" class="imagenH" />
    <div class="card-body">
      <p class="tiempo">
        hace 20 minutos
      </p>
      <h5 class="precio">$500/kg</h5>
      <h6 id="nombreh">Banano ultraverga</h6>
      <p class="tienda"> en la tienda norte</p>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.recuento {
  border: 1px solid #ff8e43;
  overflow: hidden;
  border-radius: 15px;
  box-sizing: border-box;
  display: block;
  max-height: 10vw;
  max-width: 70vw;
}
.card-body {
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
.tiempo {
  font-weight: lighter;
  font-size: 1vw;
  margin-left: 85%;
  grid-row: 1;
}
.precio {
  font-size: 2vw;
  font-weight: bold;
  color: #ff8e43;
  text-align: left;
  grid-row: 2;
}
.tienda {
    font-weight: lighter;
  font-size: 1vw;
  margin-left: 80%;
  grid-row: 4;
}
#nombreh {
  font-weight: lighter;
  font-size: 2vw;
  text-align: center;
  grid-row: 3;
}
</style>