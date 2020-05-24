<template>
  <div id="container" class="container center-content">
    <div id="sellProduct" class="center-content">
      <h1 id="hTitle" class="highText">¿QUÉ QUIERES VENDER?</h1>
      <div id="productPicFrame" class="base-border center-content inputIMG">
        <label for="fileInput">
          <img src="@/assets/imgs/photo-camera.svg" alt="profile pic" />
        </label>
        <input type="file" id="fileInput" @change="onFileSelected()" />
      </div>
      <label class="body-text desc">Nombre</label>
      <div class="input-el prodField base-border body-text">
        <input
          v-model="product.name"
          type="text"
          class="soft-el spacer input-center"
        />
      </div>
      <label class="body-text desc">Cantidad</label>
      <div class="input-el prodField base-border body-text">
        <input
          v-model="product.quantity"
          type="number"
          class="soft-el spacer input-center"
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
        <input
          v-model="product.price"
          type="number"
          class="soft-el spacer input-center"
        />
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
import alert from "vue-simple-alert";
import Vue from "vue";
Vue.use(alert);
export default {
  name: "SellProduct",
  data() {
    return {
      product: {
        name: "",
        quantity: 0,
        price: 0,
        imgURL: "",
        provID: this.$store.getters.returnUser.id,
      },
    };
  },
  methods: {
    sellProduct() {
      console.log(this.product);
      request.createProduct(
        this.product.name,
        Number(this.product.price),
        Number(this.product.quantity),
        this.product.provID,
        this.product.imgURL,
        (data) => {
          console.log(data);
          if (data.ok) {
            this.$fire({
              text: "Producto creado",
              titleText: "SE CREO EL PRODUCTO",
              icon: "success",
              confirmButtonColor: "#ff8e43",
              customClass: "swal2-error",
            });
          } else {
            this.$fire({
              text: "No se pudo crear el producto, intenta nuevamente",
              titleText: "ERROR AL CREAR PRODUCTO",
              icon: "error",
              confirmButtonColor: "#ff8e43",
              customClass: "swal2-error",
            });
          }
        }
      );
    },
    onFileSected() {},
  },
};
</script>

<style scoped>
#sellProduct {
  background-color: white;
  border-radius: 25px 25px 25px 25px;
  width: 50%;
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
#hTitle {
  margin: 2vw 0;
  font-size: 3vw;
}

.spacer {
  margin-left: 1vw;
}
.prodField {
  width: 13vw;
}
.inputIMG > input {
  display: none;
}
</style>
