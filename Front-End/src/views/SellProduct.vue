<template>
  <div id="container" class="container center-content">
    <div id="sellProduct" class="center-content">
      <h1 id="hTitle" class="highText">¿QUÉ QUIERES VENDER?</h1>
      <div id="productPicFrame" class="base-border center-content inputIMG">
        <label for="fileInputProd">
          <img v-if="isUploaded" :src="url" alt="profile pic" />
          <img v-else src="@/assets/imgs/photo-camera.svg" alt="" />
        </label>
        <input type="file" id="fileInputProd" @change="onFileChange" />
      </div>
      <label class="body-text desc">Nombre</label>
      <div class="input-el prodField base-border body-text">
        <input
          v-model="product.name"
          type="text"
          class="inSell soft-el spacer"
        />
      </div>
      <label class="body-text desc">Cantidad(en kg)</label>
      <div class="input-el prodField base-border body-text">
        <input
          v-model="product.quantity"
          type="number"
          class="inSell soft-el spacer"
        />
      </div>
      <label class="body-text desc">Precio normal en el mercado</label>
      <div class="input-el prodField base-border body-text">
        <input
          v-model="product.NormalPrice"
          type="number"
          class="inSell soft-el spacer"
        />
      </div>
      <label class="body-text desc">Precio por kg</label>
      <div class="input-el prodField base-border body-text">
        <input
          class="inSell soft-el spacer"
          v-model="product.price"
          type="number"
        />
      </div>

      <label class="body-text desc" for="dateUntil"
        >¿Hasta que día deseas vender este producto?</label
      >
      <input
        v-model="product.timeLimit"
        type="date"
        class="soft-el input-center desc"
        id="dateUntil"
      />
      <label class="body-text desc" for="dateUntil"
        >Selecciona una categoría</label
      >
      <select v-model="product.category" name="categories" id="categories">
        <option v-for="category in categories" :key="category">{{
          category
        }}</option>
      </select>
      <button
        id="sellButton"
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
import Map from "../components/maps/Mapa";
import alert from "vue-simple-alert";
import Vue from "vue";
Vue.use(alert);
export default {
  data() {
    return {
      isUploaded: false,
      url: "",
      categories: [],
      product: {
        name: "",
        quantity: 0,
        price: 0,
        NormalPrice: 0,
        imgProduct: Object,
        idProvider: this.$store.getters.returnUser.id,
        timeLimit: new Date(),
        category: "",
      },
    };
  },
  components: {
    Map,
  },
  mounted() {
    this.getCategories();
  },
  methods: {
    getCategories() {
      request.getCategories((data) => {
        if (data.ok) this.categories = data.classX;
      });
    },
    onFileChange(e) {
      this.isUploaded = true;
      const file = e.target.files[0];
      this.url = URL.createObjectURL(file);
      this.product.imgProduct = file;
    },
    sellProduct() {
      request.createProduct(
        this.product.name,
        Number(this.product.price),
        Number(this.product.quantity),
        this.product.idProvider,
        this.product.imgProduct,
        new Date(this.product.timeLimit),
        this.product.category,
        Number(this.product.NormalPrice),
        (data) => {
          if (data.ok) {
            this.$fire({
              text: "Producto creado",
              titleText: "SE CREO EL PRODUCTO",
              type: "success",
              confirmButtonColor: "#ff8e43",
              customClass: "swal2-error",
            });
          } else {
            this.$fire({
              text: "No se pudo crear el producto, intenta nuevamente",
              titleText: "ERROR AL CREAR PRODUCTO",
              type: "error",
              confirmButtonColor: "#ff8e43",
              customClass: "swal2-error",
            });
          }
        }
      );
    },
    onFileSelected(event) {
      this.product.imgProduct = event.target.files[0];
    },
  },
};
</script>

<style scoped>
.inSell {
  width: 85%;
  color: #888;
  font-size: 1vw;
  font-family: "Verdana", sans-serif;
}
#categories {
  border: 1px solid #ff8e43;
  outline: none;
  font-size: 1vw;
  color: #888;
  border-radius: 1vw;
}
#dateUntil {
  font-family: "Verdana", sans-serif;
  border: 1px solid #ff8e43;
  border-radius: 1vw;
  font-size: 1vw;
  padding: 0.4vw;
}
#sellProduct {
  background-color: white;
  border-radius: 1vw;
  width: 50%;
  height: 120%;
}
#productPicFrame {
  width: 30vw;
  height: 20vw;
  overflow: hidden;
  margin: auto;
  border-radius: 1vw;
}
#productPicFrame img {
  width: 100%;
  height: 100%;
}
#hTitle {
  margin: 2vw 0;
  font-size: 3vw;
  color: #ff8e43;
}
#sellButton {
  color: #ff8e43;
  padding: 0.3vw;
  border-radius: 1vw;
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
