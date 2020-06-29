/* eslint-disable prettier/prettier */
<template>
  <div id="containerB">
    <div class="productCard">
      <div class="pictureFr base-border">
        <img :src="getImage()" class="pictureR" />
      </div>
      <div class="productInfo">
        <h1 class="nameR">{{ preview.name }}</h1>
        <h1 class="priceR">$ {{ preview.price }}/kg</h1>
        <h3 class="dates desc">{{ preview.publicationDate }}</h3>
        <h3 class="dates desc">{{ preview.finishDate }}</h3>
      </div>
      <div class="mapAndBook">
        <Map
          class="mapI"
          v-bind:id="0"
          v-bind:pos="pos"
          v-if="pos.lat != null"
        />
        <div class="infoBook">
          <div class="providerBox">
            <p class="storeR">{{ preview.provider }}</p>
            <div class="dialogArrow">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="1.5vw"
                height="1.5vw"
                viewBox="0 0 50 50"
              >
                <path d="M 45 1 L 0 50" stroke="#ff8e43" stroke-width="2" />
              </svg>
            </div>
            <span
              class="calification"
              v-html="this.showStarts(this.rate)"
            ></span>
          </div>
          <div class="calculatorR">
            <div>
              <label class="saving">AHORRAS ${{ preview.save * to_buy }}</label>
            </div>
            <div>
              <label class="pricing">PAGAS ${{ preview.price * to_buy }}</label>
            </div>
            <p class="quantityBuy">
              Cantidad:
              <input
                v-model="to_buy"
                type="number"
                class="inputR"
                min="0"
                :max="preview.quantity"
              />
              ({{ preview.quantity }})
            </p>
            <button
              class="button-baseR soft-el"
              type="button"
              v-on:click="book()"
            >
              RESERVAR
            </button>
          </div>
        </div>
      </div>
    </div>
    <div class="commentsFrame">
      <Comments ref="commEl" class="comments" :idProvider="idProvider" />
    </div>
  </div>
</template>

<script>
import Map from "../components/maps/Mapa";
import request from "@/services/request.service.js";
import Product from "@/components/Product.vue";
import Comments from "../components/comments.vue";
import Vue from "vue";
import alert from "vue-simple-alert";
import { timeSince, timeTil } from "@/services/dateServices.js";

Vue.use(alert);
export default {
  name: "SellProduct",
  data() {
    return {
      rate: 0,
      pos: { lat: null, lng: null },
      to_buy: 0,
      idProvider: 0,
      preview: {
        quantity: 0,
        price: 0,
        name: "",
        provider: "",
        id: null,
        image: "",
        save: 0,
        publicationDate: "",
        finishDate: "",
      },
    };
  },
  components: {
    Product,
    Map,
    Comments,
  },
  methods: {
    bringFromBackR() {
      request.getProductById(this.$route.params.id, (data) => {
        if (data.ok) {
          this.preview.quantity = data.classX.quantity;
          this.preview.price = data.classX.price;
          this.preview.name = data.classX.name;
          this.preview.provider = data.classX.provider.name;
          this.idProvider = data.classX.provider.id;
          this.getComments();
          this.preview.id = data.classX.id;
          this.preview.image = data.classX.image;
          this.preview.save = data.classX.saved;
          this.preview.publicationDate = timeSince(data.classX.publicationDate);
          this.preview.finishDate = timeTil(data.classX.timeLimit);
          this.pos.lat = data.classX.provider.ubication.lat;
          this.pos.lng = data.classX.provider.ubication.longitud;
          request.getRate(data.classX.provider.id, (data) => {
            if (data.ok) {
              this.rate = Math.floor(data.classX.rate);
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
                type: "success",
                confirmButtonColor: "#ff8e43",
                customClass: "swal2-error",
              });
              this.bringFromBackR();
            } else {
              this.$fire({
                text: "Ocurrió un error al crear la reserva",
                titleText: "NO SE PUDO CREAR RESERVA",
                type: "error",
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
          type: "error",
          confirmButtonColor: "#ff8e43",
          customClass: "swal2-error",
        });
      }
    },
    getImage() {
      return "data:image/jpeg;base64," + this.preview.image;
    },
    getComments() {
      this.$refs.commEl.getComents(this.idProvider);
    },
    showStarts(rate) {
      this.HtmlText = "";
      for (let index = 0; index < rate; index++) {
        this.HtmlText += '<label style="color:#ff8e43">★</label>';
      }
      for (let index = 0; index < 5 - rate; index++) {
        this.HtmlText += '<label style="color: #BFBFBF">★</label>';
      }
      return this.HtmlText;
    },
  },
  mounted() {
    this.bringFromBackR();
  },
};
</script>

<style>
#containerB {
  height: 160vh;
  display: block;
  background-color: white;
}
.productCard {
  width: 100%;
  height: 100vh;
}
.pictureFr {
  position: absolute;
  margin-left: 10vw;
  overflow: hidden;
  height: 80vh;
  float: left;
  border-radius: 1vw;
  max-width: 30vw;
  bottom: 0;
}
.pictureR {
  height: 80vh;
  width: auto;
  margin-left: -20vw;
}
.productInfo {
  position: absolute;
  text-align: left;
  margin-top: 5vh;
  border-radius: 1vw 0 0 1vw;
  border: 1px solid #ff8e43;
  border-right: none;
  right: 0;
  width: 56.5vw;
  height: 14.5vw;
  font-family: verdana, Verdana, Geneva, Tahoma, sans-serif;
}
.productInfo * {
  margin-left: 1vw;
}
.nameR {
  font-family: "Oswald", sans-serif;
  font-size: 2.5vw;
  font-weight: 700;
  margin-left: 1vw;
  margin-top: 0.4vw;
  max-width: 50vw;
}
.priceR {
  font-family: "Oswald", sans-serif;
  font-size: 3.7vw;
  font-weight: 700;
  margin-left: 1vw;
  color: #ff8e43;
}
.dates {
  font-size: 1vw;
  font-weight: normal;
}
.mapAndBook {
  position: absolute;
  width: 54%;
  height: 52%;
  bottom: 0;
  right: 0;
}
.mapI {
  overflow: hidden;
  position: absolute;
  height: 100%;
  width: 35%;
  border: solid 1px;
  border-color: #ff8e43;
  border-radius: 1vw;
  bottom: 0;
}
.infoBook {
  position: absolute;
  left: 38%;
  top: 0;
  width: 40%;
  height: 100%;
}
.providerBox {
  border: 1px solid #ff8e43;
  border-radius: 1vw 1vw 1vw 0;
  position: absolute;
  top: 0;
  left: 5%;
  z-index: 1;
}
.storeR {
  font-size: 2.5vw;
  padding: 0 0.5vw 0 0.5vw;
  font-weight: 700;
  max-width: 30vw;
  overflow: auto;
}
.dialogArrow {
  position: absolute;
  width: 1.5vw;
  height: 1.5vw;
  border-bottom: 1px solid #ff8e43;
  border-right: 1px solid #ffffff;
  background-color: #ffffff;
  bottom: -1px;
  left: -1.4vw;
  z-index: 10;
}
.calification {
  font-size: 2.5vw;
  font-weight: bold;
  margin: 1vw;
}
.calculatorR {
  position: absolute;
  bottom: 15%;
  width: auto;
  max-width: 40vw;
  height: 50%;
  min-width: 16vw;
  bottom: 1vw;
  margin-left: 1vw;
}
.saving {
  font-size: 2vw;
  font-weight: 700;
  color: #bfbfbf;
  padding: 0 1vw;
}
.pricing {
  font-size: 2vw;
  font-weight: 700;
  color: #ff8e43;
  padding: 0 1vw;
}
.quantityBuy {
  padding: 1vw;
  font-family: "Verdana", sans-serif;
  color: #888;
}
.inputR {
  font-size: 1vw;
  width: 5vw;
  border-radius: 0.5vw;
  border: 3px solid #bfbfbf;
  outline: none;
  color: #bfbfbf;
}
.button-baseR {
  background-color: #a1ffca;
  color: #ff8e43;
  width: 100%;
  height: 30%;
  border-radius: 0.5vw;
  padding: 0 0.2vw;
  cursor: pointer;
  font-size: 2.5vw;
  font-family: "Oswald", sans-serif;
  font-weight: bold;
}

.commentsFrame {
  margin: auto;
  width: 80vw;
  height: 25vw;
  background-color: #a1ffca;
  border-radius: 1vw;
  border: 1px solid #ff8e43;
}
.tittleModal {
  text-align: center;
  font-weight: lighter;
  font-size: 2vw;
  margin-top: 2vw;
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
</style>
