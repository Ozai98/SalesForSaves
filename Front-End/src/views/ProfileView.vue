<template>
  <div class="container center-content">
    <div id="profile" class="center-content">
      <h1 class="highText1">INFORMACIÓN DE PERFIL</h1>
      <div class="picFrame">
        <img :src="getImage()" alt="profile pic" />
      </div>
      <label class="body-text desc" for="unField">Nombre</label>
      <p id="unField" class="highText2">{{ name }}</p>
      <label class="body-text desc" for="mailField">Correo</label>
      <p id="mailField" class="highText2">{{ mail }}</p>
      <button
        class="button-base accessBtn editBtn"
        @click="jumpScreen('EditProfile')"
      >
        EDITAR
      </button>
      <button
        class="button-base accessBtn editBtn"
        @click="jumpScreen('History')"
      >
        HISTORIAL
      </button>
      <button class="button-base accessBtn editBtn" @click="logout()">
        SALIR
      </button>
    </div>
  </div>
</template>

<script>
import request from "@/services/request.service.js";
export default {
  data() {
    return {
      name: this.$store.getters.returnUser.name,
      mail: this.$store.getters.returnUser.mail,
    };
  },
  methods: {
    logout() {
      this.jumpScreen("Home");
      this.$store.dispatch("resetUser");
    },
    getImage() {
      return "data:image/jpeg;base64," + this.$store.getters.returnUser.imgURL;
    },
  },
};
</script>

<style scoped>
#profile {
  background-color: white;
  border-radius: 1vw;
  width: 60%;
}
.picFrame img {
  width: auto;
  height: 100%;
}

.highText1 {
  font-size: 3vw;
  color: #ff8e43;
}
.highText2 {
  font-size: 3vw;
}
.editBtn {
  min-width: 120px;
  min-height: 40px;
  color: #ff8e43;
  width: 8vw;
}
</style>
