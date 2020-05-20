<template>
  <div id="container" class="container center-content">
    <div id="sellProduct" class="center-content">
      <h1 class="highText">¿Que quieres vender?</h1>
      <div id="productPicFrame" class="base-border center-content">
        <img src="@/assets/imgs/photo-camera.svg" alt="profile pic" />
      </div>
      <label class="body-text desc">Nombre</label>
      <div class="input-el prodField base-border body-text">
        <input v-model="product.name" type="text" class="soft-el spacer" />
      </div>
      <label class="body-text desc">Cantidad</label>
      <div class="input-el prodField base-border body-text">
        <input
          v-model="product.quantity"
          type="number"
          class="soft-el spacer"
        />
        <div class="space"></div>
        <p class="center-content">kg</p>
      </div>
      <label class="body-text desc">Precio por kg</label>
      <div class="input-el prodField base-border body-text center-content">
        <p class="center-content">$</p>
        <div
          class="space center-content"
          style="margin: 0 0.5vw 0 0.5vw;"
        ></div>
        <input v-model="product.price" type="number" class="soft-el spacer" />
      </div>
      <button
        type="button"
        class="editBtn button-base accessBtn"
        @click="sellProduct()"
      >
        PONER EN VENTA
      </button>
    </div>
  </div>
</template>

<script>
import request from "@/services/request.service.js";
import Vue from "vue"
import alet from  "vue-simple-alert" ;
Vue.use(alet);
export default {
  name: "SellProduct",
  data() {
    return {
      product: {
        name: "",
        quantity: 0,
        price: 0,
        imgURL: "",
        provID: this.$store.getters.returnUser.id
      }
    };
  },
  methods: {
    sellProduct() {
      console.log(this.product);
      request.crearProducto(
        this.product.name,
        Number(this.product.price),
        Number(this.product.quantity),
        this.product.provID,
        this.product.imgURL,
        data => {
          console.log(data);
          if (data.ok) {
            console.log("Producto creado");
            this.$alert("SE CREO EL RPODUCTO",'succes');
          } else this.$alert("ERROR AL CREAR PRODUCTO",'error');
        }
      );
    }
  }
};
</script>

<style scoped>
#sellProduct {
  background-color: white;
  border-radius: 25px 25px 25px 25px;
  max-height: 80vh;
  width: 60%;
}
#productPicFrame {
  width: 30vw;
  height: 20vw;
  overflow: hidden;
  margin: auto;
}
#productPicFrame img {
  width: 70%;
  height: 70%;
}
.spacer {
  margin-left: 2vw;
}
.prodField {
  width: 13vw;
}
</style>
