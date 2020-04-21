<template>
  <div id="container" class="container center-content">
    <div id="sellProduct" class="center-content">
      <h1 class="highText">¿Que quieres vender?</h1>
      <form action="requestHandler.crearProducto()" class="center-content">
        <div id="productPicFrame" class="base-border center-content">
          <img src="@/assets/imgs/photo-camera.svg" alt="profile pic" />
        </div>
        <label class="body-text desc">Nombre</label>
        <div class="input-el prodField base-border body-text">
          <input type="text" class="soft-el spacer" />
        </div>
        <label class="body-text desc">Cantidad</label>
        <div class="input-el prodField base-border body-text">
          <input type="number" class="soft-el spacer" />
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
          <input type="number" class="soft-el spacer" />
        </div>
        <label class="body-text desc">Ubicación</label>
        <div class="input-el prodField base-border body-text">
          <input type="text" class="soft-el spacer" />
        </div>
        <button
          type="submit"
          class="editBtn button-base accessBtn"
          @click="jumpEdit()"
        >
          EDITAR
        </button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "SellProduct"
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
