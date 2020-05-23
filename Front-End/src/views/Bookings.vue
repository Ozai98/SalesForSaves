<template>
  <div id="container">
    <p class="titleR">RESERVA YA!</p>
    <div class="summaryR">
      <img src="@/assets/imgs/banano.jpg" class="pictureR" />
      <div class="cardBodyR">
        <p class="timeR"></p>
        <p class="nameR">{{ preview.name }}</p>
        <p class="const1R">PRECIO :</p>
        <p class="priceR">${{ preview.price }}/kg</p>
        <p class="const2R">PROVEEDOR :</p>
        <p class="storeR">{{ preview.provider }}</p>
        <p class="const3R">CANTIDAD DISPONIBLE :</p>
        <p class="quantityR">{{ preview.quantity }} UNIDADES</p>
      </div>
    </div>
    <div class="calculatorR">
      <label class="inputR">CANTIDAD:</label>
      <input v-model="to_buy" type="number" class="inputR" />
      <div>
        <label class="inputR">PRECIO FINAL:</label>
        <label class="inputR"></label>
      </div>
      <div id="bot2">
        <button class="button-base" type="button" v-on:click="book()">
          RESERVAR
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/services/request.service.js";
import Product from "@/components/Product.vue";
export default {
  name: "SellProduct",
  data() {
    return {
      to_buy: Number,
      preview: {
        quantity: 0,
        price: 0,
        name: "",
        provider: "",
        id: null,
      },
    };
  },
  components: {
    Product,
  },
  methods: {
    bringFromBackR() {
      request.getProductoById(this.$route.params.id, (data) => {
        if (data.ok) {
          (this.preview.quantity = data.classX.quantity),
            (this.preview.price = data.classX.price),
            (this.preview.name = data.classX.name),
            (this.preview.provider = data.classX.provider.name),
            (this.preview.id = data.classX.id);
        } else console.log("Error al encontrar producto");
        console.log(data);
      });
    },
    book() {
      if (!(this.to_buy > this.preview.quantity)) {
        request.newReserve(
          this.$store.getters.returnUser.id,
          Number(this.preview.id),
          this.preview.quantity,
          (data) => {
            if (data.ok) {
              console.log(data);
              console.log("Reserva creada");
            } else {
              console.log("Error al crear reserva");
              console.log(data.msg);
            }
          }
        );
      } else {
        console.log("a<b por tanto A jamas va a querer a B como B quiere a A");
      }
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
.inputR {
  font-size: 2vw;
  margin: 2vw;
}
.calculatorR {
  width: 55vw;
  height: 10vw;
  float: right;
}
button {
  background-color: #ffffff;
  color: #ff8e43;
  width: 20vw;
  height: 10vw;
  border-radius: 16px;
  padding: 0 0.2vw;
  cursor: pointer;
  font-size: 1.2vw;
  font-family: "Oswald", sans-serif;
  margin: 3vw 0 0.5vw 0;
  font-weight: bold;
}
</style>
