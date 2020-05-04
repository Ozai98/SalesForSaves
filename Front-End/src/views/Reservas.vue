<template>
  <div id="container">
    <p class="tituloR">RESERVA YA! </p>
    <div class="recuentoR">
        <img src="@/assets/imgs/banano.jpg" class="imagenR" />
        <div class="card-bodyR">
          <p class="tiempoR">
          </p>
            <p class="nombreR">NOMBRE MAS LARGO QUE EL HISTORIAL DE FRACASOS DE MI VIDA</p>
            <p class="const1R">PRECIO :</p> 
            <p class="precioR"> 500/kg</p>
            <p class="const2R">PROVEEDOR :</p>
            <p class="tiendaR">CALI MIO Y TUYO</p>
            <p class="const3R">CANTIDAD DISPONIBLE :</p>
            <p class="cantidadR"> 22 UNIDADES</p>
        </div>
    </div>
    <div class="calculatorR">
        <label class="input">
            CANTIDAD:    
        </label> 
        <input
          v-model="cotiza.quantity"
          type="number"
          class="inputR"
        />
        <div>
        <label class="inputR"></label>
        </div>
    </div>
  </div>
</template>
<script>
import request from "@/services/request.service.js";
export default {
  name: "SellProduct",
  data() {
    return {
      cotiza: {
        quantity: 0,
        price: 0,

      }
    };
  }
};
</script>
<style>
#container {
  height: 90%;
  background-color: white;
}
.tituloR {
  font-family: Oswald;
  font-size: 70px;
  margin: 0%;
  text-align: center;
}
.recuentoR {
  border: 1px solid #ff8e43;
  overflow: hidden;
  border-radius: 15px;
  box-sizing: border-box;
  display: block;
  max-height: 30vw;
  max-width: 100vw;
}
.card-bodyR {
  float: left;
  display: grid;
  padding-left: 1vw;
  width: 50vw;
  font-family: verdana,Verdana, Geneva, Tahoma, sans-serif;
}
.imagenR {
  height: 30vw;
  float: left;
  border-top-left-radius: 1vw;
  border-bottom-left-radius: 1vw;
  max-width: 100%;
  width: auto;
  vertical-align: middle;
  border-style: none;

}

.precioR {
  grid-row: 2; 
  grid-column: 2; 
  font-size: 3vw;
  font-weight: bold;
  color: #ff8e43;
  text-align: right;
  margin: 1vw;

}
.tiendaR {
      grid-row: 3;
    font-weight: lighter;
  font-size: 2vw;
  margin-top: 2vw;
    text-align: right;
}
.nombreR {
  margin: 1vw;
  grid-column: 1/3;
  font-weight: lighter;
  font-size: 3vw;
  text-align: center;
  grid-row: 1;
}
.cantidadR{
  grid-row: 4;
    text-align: right;
  font-weight: lighter;
  font-size: 2vw;
  margin-top: 2vw;  
}
.const1R{
  grid-row: 2;  
  grid-column: 1;
  font-size: 3vw;
  font-weight: bold;
  color: #ff8e43;
  text-align: left;
  margin: 1vw;
}
.const2R{
      grid-row: 3;
    font-weight: lighter;
  font-size: 2vw;
  margin-top: 2vw;
  text-align: left;
  margin: 1vw;
}
.const3R{
  grid-row: 4;
  font-weight: lighter;
  font-size: 2vw;
  text-align: left;
  margin: 1vw;
}
.inputR{
    border: 1px solid #ff8e43;
}
.calculatorR{
    width: 55vw;
    height: 10vw;
    float:right;
}
</style>