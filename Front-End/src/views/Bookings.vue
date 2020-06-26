/* eslint-disable prettier/prettier */
<template>
  <div id="container">
    <div class="summaryR">
      <div class="pictureFr base-border">
        <img :src="getImage()" class="pictureR" />
      </div>
      <div class="cardBodyR base-border">
        <p class="priceR">$ {{ preview.price }}/kg</p>
        <p class="nameR body-text desc">{{ preview.name }}</p>
        <p class="quantityR">Quedan {{ preview.quantity }} unidades</p>
        <p class="saveR">Ahorras $ {{ preview.save }}/kg</p>
      </div>
    </div>
    <div class="contenedorR">
      <div class="seeMap">
        <center>
        UBICA EL NEGOCIO !
        <Map class="mapa2" v-bind:id="0" v-bind:pos="pos" v-if="pos.lat!=null"/>
        </center>
      </div>
      <div class="calculatorR">
        <p class="storeR">{{ preview.provider }}</p>
        <span class="calification" v-html="this.showStarts(this.rate)"></span>
        <p class="tittleCalculatorR">COTIZA Y RESERVA !</p>
        <label class="inputR">CANTIDAD:</label>
        <input v-model="to_buy" type="number" class="inputR" min="0" />
        <div>
          <label class="inputR">PRECIO FINAL:</label>
          <label class="inputR">${{ preview.price * to_buy }}</label>
        </div>
        <div>
          <label class="inputR">AHORRO FINAL:</label>
          <label class="inputR">${{ preview.save * to_buy }}</label>
        </div>
        <div id="bot2">
          <button class="button-baseR" type="button" v-on:click="book()">
            RESERVAR
          </button>
        </div>
        <div>
          <button class="button-baseR" type="button" v-on:click="comments()">
            COMENTAR
          </button>
        </div>
      </div>
    </div>


    <ModalComponent ref="modalComments" >
      <template v-slot:header>
        <div class="tittleModal " >COMENTARIOS</div>
      </template>
      <template v-slot:body>
        <Comments
          ref="comm"
          class="comments"
          :images="preview.image"
          :idProvider="idProvider"
        />
      </template>
      <template v-slot:footer>
        <button class="button-commR" type="button" v-on:click="comments2()">
          ENVIAR
        </button>
      </template>
    </ModalComponent>
  </div>
</template>

<script>
import Map from "../components/maps/Mapa";
import request from "@/services/request.service.js";
import Product from "@/components/Product.vue";
import ModalComponent from "@/components/ModalComponent.vue";
import Comments from "../components/comments";
import Vue from "vue";
import alert from "vue-simple-alert";
Vue.use(alert);
export default {
  name: "SellProduct",
  data() {
    return {
      rate:0,
      pos:{lat:null ,lng: null},
      to_buy:0 ,
      idProvider:0,
      preview: {
        quantity: 0,
        price: 0,
        name: "",
        provider: "",
        id: null,
        image: "",
        save: 0,
      },
    };
  },
  components: {
    Product,
    Map,
    Comments,
    ModalComponent,
  },
  methods: {
    bringFromBackR() {
      request.getProductById(this.$route.params.id, (data) => {
        if (data.ok) {
          console.log(data.classX);
          this.preview.quantity = data.classX.quantity;
          this.preview.price = data.classX.price;
          this.preview.name = data.classX.name;
          this.preview.provider = data.classX.provider.name;
          this.idProvider = data.classX.provider.id;
          this.preview.id = data.classX.id;
          this.preview.image = data.classX.image;
          this.preview.save = data.classX.saved;
          console.log(data.classX.provider.ubication.lat+' '+data.classX.provider.ubication.longitud);
          console.log(this.pos.lat+''+this.pos.lng)
          this.pos.lat=data.classX.provider.ubication.lat;
          this.pos.lng=data.classX.provider.ubication.longitud;
          console.log(this.pos.lat+''+this.pos.lng)
          request.getRate(data.classX.provider.id,(data) => {
          
           if(data.ok){
              this.rate=Math.floor(data.classX.rate);
            }
          });
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
      return "data:image/jpeg;base64," + this.preview.image;
    },
    showStarts(rate) {
      this.HtmlText = "";
      for (let index = 0; index < rate; index++) {
        this.HtmlText += '<label style="color:#ff8e43">★</label>';
      }
      for (let index = 0; index < 5 - rate; index++) {
        this.HtmlText += '<label style="color: grey">★</label>';
      }
      return this.HtmlText;
    },
    comments() {
      this.$refs.modalComments.openModal();
    },
    comments2() {
      this.$refs.comm.send();
      this.$refs.modalComments.closeModal();
    },
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
.summaryR {
  border-radius: 15px;
  box-sizing: border-box;
  display: block;
  max-height: 30vw;
  max-width: 100vw;
}
.pictureFr {
  margin-top: 10vh;
  margin-left: 10vw;
  overflow: hidden;
  height: 70vh;
  float: left;
  border-radius: 1vw;
  max-width: 30vw;
  vertical-align: middle;
}
.cardBodyR {
  display: inline-grid;
  margin-top: 5vh;
  border-radius: 1vw 0 0 1vw;
  float: right;
  width: 50vw;
  height: 12vw;
  font-family: verdana, Verdana, Geneva, Tahoma, sans-serif;
}
.pictureR {
  height: 80vh;
  width: auto;
  margin-left: -20vw;
}

.priceR {
  grid-row: 1;
  grid-column: 1;
  font-size: 3vw;
  font-family: "Oswald", sans-serif;
  font-weight: 700;
  color: #ff8e43;
  text-align: left;
  margin: 1vw;
}
.storeR {
  grid-row: 3;
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
  text-align: left;
  grid-row: 1;
  grid-column: 2;
  margin: 1vw;
  font-size: 2vw;
  float: left;
}
.quantityR {
  grid-row: 2;
  grid-column: 2;
  text-align: right;
  font-size: 0.8vw;
}
.saveR {
  float: left;
  font-family: "Oswald", sans-serif;
  font-size: 2vw;
  font-weight: bold;
  color: #ff8e43;
  text-align: left;
  position: relative;
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
.button-baseR {
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
.button-commR {
  background-color: white;
  color: #ff8e43;
  width: 6vw;
  height: 2vw;
  border-radius: 16px;
  padding: 0 0.2vw;
  cursor: pointer;
  font-size: 1vw;
  font-family: "Oswald", sans-serif;
  margin: 0.5vw 0 0.5vw 0;
  font-weight: bold;
}
.contenedorR {
  width: 100%;
  height: 40%;
  display: flex;
}
.tittleCalculatorR {
  font-size: 3vw;
  color: #ff8e43;
  font-family: "Oswald", sans-serif;
}
.seeMap {
  height: 40vw;
  width: 50vw;
  font-size: 3vw;
  color: #ff8e43;
  font-family: "Oswald", sans-serif;
  align-content: center;
  align-items: center;
}
.mapa2 {
    height: 30vw;
    width: 46vw;
    margin: 1.5vw 1.5vw 1.5vw 1.5vw;
    border: solid 0.3vw;
    border-color: black;
    border-radius: 0.2vw;
  }
  .comments {
    max-height: 450px;
  }
  .tittleModal{

  text-align: center;
  font-weight: lighter;
  font-size: 2vw;
  margin-top: 2vw;
  }
</style>
