<template>
  <div id="container">
    <NavBar idPage="ProfileData"></NavBar>
    <div id="SellProduct">
      <h1 class="highText">¿Que quieres vender?</h1>
      <div class="ProductPicFrame" >
        <img src="@/assets/photo-camera.svg" alt="profile pic" />
      </div>
      <br />
      <label for="unField">Nombre</label>
      <p id="unField" class="highText">{{ UserName }}</p>
      <label for="mailField">Correo</label>
      <p id="mailField" class="highText">{{ Mail }}</p>
      <button class="editBtn" @click="jumpEdit()">EDITAR</button>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "SellProduct",
  components: {
    NavBar
  }
};
</script>

<style scoped>
.productPicFrame {
  height: 450px;
  width: 600px;
}

</style>