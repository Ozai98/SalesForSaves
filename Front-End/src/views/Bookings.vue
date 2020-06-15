/* eslint-disable prettier/prettier */
<template>
  <div id="container">
    <p class="titleR">RESERVA YA!</p>
    <div class="summaryR">
      <img :src="getImage()" class="pictureR" />
      <div class="cardBodyR">
        <p class="timeR"></p>
        <p class="nameR">{{ preview.name }}</p>
        <p class="const1R">PRECIO :</p>
        <p class="priceR">${{ preview.price }}/kg</p>
        <p class="const2R">PROVEEDOR :</p>
        <p class="storeR">{{ preview.provider }}</p>
        <p class="const3R">CANTIDAD DISPONIBLE :</p>
        <p class="quantityR">{{ preview.quantity }} UNIDADES</p>
        <p class="const4R">AHORRO EN EL PRODUCTO :</p>
        <p class="saveR">${{ preview.save}}/kg</p>
        <p class="const5R">CALIFICACION DEL PROVEEDOR :</p>
        <span class="calification" v-html="this.showStarts(3)"></span>
      </div>
    </div>
    <div class="contenedorR">
      <div class="seeMap">
        <center>
        UBICA EL NEGOCIO !
        <Map/>
        </center>
      </div>
      <div class="calculatorR">
        <p class="tittleCalculatorR"> COTIZA Y RESERVA !</p>
        <label class="inputR">CANTIDAD:</label>
        <input v-model="to_buy" type="number" class="inputR" min="0"  />
        <div>
          <label class="inputR">PRECIO FINAL:</label>
          <label class="inputR">${{preview.price * to_buy}}</label>
        </div>
        <div>
          <label class="inputR">AHORRO FINAL:</label>
          <label class="inputR">${{preview.save * to_buy}}</label>
        </div>
        <div id="bot2">
          <button class="button-base" type="button" v-on:click="book()">
              RESERVAR
            </button>
         </div>   
        </div>
    </div>
    
  </div>

</template>

<script>
import Map from "../components/maps/Mapa";
import request from "@/services/request.service.js";
import Product from "@/components/Product.vue";
import Vue from "vue";
import alert from "vue-simple-alert";
Vue.use(alert);
export default {
  name: "SellProduct",
  data() {
    return {
      h:this.showStarts(2),
      to_buy:0 ,
      preview: {
        quantity: 0,
        price: 0,
        name: "",
        provider: "",
        id: null,
        image: "",
        save:0
      },
    };
  },
  components: {
    Product,
    Map
  },
  methods: {
    bringFromBackR() {
      request.getProductById(this.$route.params.id, (data) => {
        if (data.ok) {
          this.preview.quantity = data.classX.quantity;
          console.log(this.preview.quantity);
          this.preview.price = data.classX.price;
          this.preview.name = data.classX.name;
          this.preview.provider = data.classX.provider.name;
          this.preview.id = data.classX.id;
          this.preview.image = data.classX.image;
          this.preview.save = data.classX.saved;
        }
      });
    },
    book() {
      if (!(this.to_buy > this.preview.quantity)) {
        request.newReserve(
          this.$store.getters.returnUser.id,
          Number(this.preview.id),
          this.to_buy,
          (data) => {
            if (data.ok) {
              this.$fire({
                text: "Reserva creada con éxito",
                titleText: "RESERVA CREADA",
                icon: "success",
                confirmButtonColor: "#ff8e43",
                customClass: "swal2-error",
              });
              this.bringFromBackR();
            } else {
              this.$fire({
                text: "Ocurrió un error al crear la reserva",
                titleText: "NO SE PUDO CREAR RESERVA",
                icon: "error",
                confirmButtonColor: "#ff8e43",
                customClass: "swal2-error",
              });
            }
          }
        );
      } else {
        this.$fire({
          text: "Estás pidiendo más unidades de las que hay disponibles",
          titleText: "NO SE PUDO CREAR RESERVA",
          icon: "error",
          confirmButtonColor: "#ff8e43",
          customClass: "swal2-error",
        });
      }
    },
    getImage() {
      return 'data:image/jpeg;base64,' + this.preview.image;
    },
    showStarts(rate){
      this.HtmlText='';
        for (let index = 0; index < rate; index++) {
          this.HtmlText+='<label style="color:#ff8e43">★</label>'
        }
        for (let index = 0; index < 5-rate; index++) {
          this.HtmlText+='<label style="color: grey">★</label>'
          
        }
        console.log(this.HtmlText);
        return this.HtmlText;
    }
  },
  mounted() {
    this.bringFromBackR();
  },
};
</script>

<style>
#container {
  height: 90%;
  background-color: white;
}
.titleR {
  font-family: Oswald;
  font-size: 70px;
  margin: 0%;
  text-align: center;
}
.summaryR {
  border: 1px solid #ff8e43;
  overflow: hidden;
  border-radius: 15px;
  box-sizing: border-box;
  display: block;
  max-height: 30vw;
  max-width: 100vw;
}
.cardBodyR {
  float: left;
  display: grid;
  padding-left: 1vw;
  width: 50vw;
  font-family: verdana, Verdana, Geneva, Tahoma, sans-serif;
}
.pictureR {
  height: 30vw;
  float: left;
  border-top-left-radius: 1vw;
  border-bottom-left-radius: 1vw;
  max-width: 100%;
  width: auto;
  vertical-align: middle;
  border-style: none;
}

.priceR {
  grid-row: 2;
  grid-column: 2;
  font-size: 3vw;
  font-weight: bold;
  color: #ff8e43;
  text-align: right;
  margin: 1vw;
}
.storeR{
  grid-row:3 ;
  font-size: 2vw;
  margin-top: 2vw;
}
.providerR {
  grid-row: 3;
  font-weight: lighter;
  font-size: 2vw;
  margin-top: 2vw;
  text-align: right;
}
.nameR {
  margin: 1vw;
  grid-column: 1/3;
  font-weight: lighter;
  font-size: 3vw;
  text-align: center;
  grid-row: 1;
}
.quantityR {
  grid-row: 4;
  text-align: right;
  font-weight: lighter;
  font-size: 2vw;
  margin-top: 2vw;
}
.saveR {
  grid-row: 5;
  grid-column: 2;
  font-size: 2vw;
  font-weight: bold;
  color: #ff8e43;
  text-align: right;
  margin: 1vw;
}
.const1R {
  grid-row: 2;
  grid-column: 1;
  font-size: 3vw;
  font-weight: bold;
  color: #ff8e43;
  text-align: left;
  margin: 1vw;
}
.const2R {
  grid-row: 3;
  font-weight: lighter;
  font-size: 2vw;
  margin-top: 2vw;
  text-align: left;
  margin: 1vw;
}
.const3R {
  grid-row: 4;
  font-weight: lighter;
  font-size: 2vw;
  text-align: left;
  margin: 1vw;
}
.const4R {
  grid-row: 5;
  font-weight: lighter;
  font-size: 1.8vw;
  text-align: left;
  margin: 1vw;
}
.const5R {
  grid-row: 6;
  font-weight: lighter;
  font-size: 1.8vw;
  text-align: left;
  margin: 1vw;
}
.calification {
    grid-row: 6;
  grid-column: 2;
  font-size: 2.5vw;
  font-weight: bold;
  text-align: right;
  margin: 1vw;
}
.inputR {
  font-size: 2vw;
  margin: 2vw;
}
.calculatorR {
  width: 55vw;
  height: auto;
  float: right;
}
button {
  background-color: #a1ffca;
  color: #ff8e43;
  width: 15vw;
  height: 5vw;
  border-radius: 16px;
  padding: 0 0.2vw;
  cursor: pointer;
  font-size: 3vw;
  font-family: "Oswald", sans-serif;
  margin: 3vw 0 0.5vw 0;
  font-weight: bold;
}  
.contenedorR{
  width:100%;
  height: 40%;
  display: flex;
}
.tittleCalculatorR{
    font-size: 3vw;
    color: #ff8e43;
    font-family: "Oswald", sans-serif;
}
.seeMap{
    height: 40vw;
    width: 50vw;
    font-size: 3vw;
    color: #ff8e43;
    font-family: "Oswald", sans-serif;
    align-content: center;
    align-items: center;
}
.mapa {
    height: 30vw;
    width: 46vw;
    margin: 1.5vw 1.5vw 1.5vw 1.5vw;
    border: solid 0.3vw;
    border-color: black;
    border-radius: 0.2vw;
  }
</style>
