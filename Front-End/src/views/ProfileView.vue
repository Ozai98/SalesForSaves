<template>
  <div class="container center-content">
    <div id="profile" class="center-content">
      <h1 class="highText">INFORMACIÓN DE PERFIL</h1>
      <div class="picFrame">
        <img src="@/assets/imgs/ProfilePhoto.jpg" alt="profile pic" />
      </div>
      <label class="body-text desc" for="unField">Nombre</label>
      <p id="unField" class="highText">{{ UserName }}</p>
      <label class="body-text desc" for="mailField">Correo</label>
      <p id="mailField" class="highText">{{ Mail }}</p>
      <button
        id="editBtn"
        class="button-base accessBtn"
        @click="jumpScreen('EditProfile')"
      >
        EDITAR
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProfileView",
  props: {
    UserName: String,
    Mail: String,
    Alt: Boolean
  }
};
</script>

<style scoped>
#profile {
  background-color: white;
  border-radius: 25px 25px 25px 25px;
  max-height: 80vh;
  width: 60%;
}
.picFrame img {
  width: auto;
  height: 100%;
  margin-left: -15%;
}

.highText {
  font-size: 4vw;
}
#editBtn {
  min-width: 120px;
  min-height: 40px;
  font-size: 2vw;
  color: #ff8e43;
}
</style>
