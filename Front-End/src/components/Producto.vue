<template>
  <div class="carta">
    <img src="@/assets/imgs/banano.jpg" class="imagen" />
    <div class="card-body">
      <p class="tiempo">
        hace 20 minutos
      </p>
      <h5 class="precio">$500/kg</h5>
      <h6 id="nombre">Banano ultravergas</h6>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.carta {
  border: 1px solid #ff8e43;
  border-radius: 15px;
  width: 100%;
  box-sizing: border-box;
  display: block;
  max-height: 20vw;
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
.tiempo {
  font-weight: lighter;
  font-size: 0.7vw;
  margin-left: 65%;
}
.precio {
  font-size: 3vw;
  font-weight: bold;
  color: #ff8e43;
}
#nombre {
  font-weight: lighter;
  font-size: 1vw;
  margin-left: 30%;
}
</style>
