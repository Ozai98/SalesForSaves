<template>
  <div class="container center-content">
    <div id="profile" class="center-content">
      <h1 class="highText">INFORMACIÓN DE PERFIL</h1>
      <div class="picFrame">
        <img src="@/assets/imgs/ProfilePhoto.jpg" alt="profile pic" />
      </div>
      <label class="body-text desc" for="unField">Nombre</label>
      <p id="unField" class="highText">{{ name }}</p>
      <label class="body-text desc" for="mailField">Correo</label>
      <p id="mailField" class="highText">{{ correo }}</p>
      <button
        id="editBtn"
        class="button-base accessBtn"
        @click="jumpScreen('EditProfile')"
      >
        EDITAR
      </button>
      <button id="" class="button-base accessBtn" @click="logout()">
        Cerrar Sesión
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProfileView",
  data() {
    return {
      name: this.$store.getters.returnUser.name,
      correo: this.$store.getters.returnUser.mail
    };
  },
  methods: {
    logout() {
      this.jumpScreen("Home");
      this.$store.dispatch("resetUser");
    }
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
  color: #ff8e43;
}
</style>
