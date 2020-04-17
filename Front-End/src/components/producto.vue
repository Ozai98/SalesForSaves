<template>
  <div class="carta" style="width: 18rem;">
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
#imagen {
  width: 56pt;
  height: auto;
  padding-top: 0px;
  margin: 0;
}

.carta {
  border: 1px solid #ff8e43;
  border-radius: 15px;
  width: 23%;
  box-sizing: border-box;
}
.card-body {
  padding: 15px;
}
.imagen {
  width: 100%;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  max-width: 100%;
  height: auto;
  vertical-align: middle;
  border-style: none;
}
.tiempo {
  font-weight: lighter;
  font-size: small;
  margin-left: 65%;
}
.precio {
  font-size: 40px;
  font-weight: bold;
  color: #ff8e43;
}
#nombre {
  font-weight: lighter;
  font-size: small;
  margin-left: 30%;
}
</style>
