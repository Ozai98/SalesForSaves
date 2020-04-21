<template>
  <div class="container center-content">
    <div id="profile" class="center-content">
      <h1 class="highText">INFORMACIÓN DE PERFIL</h1>
      <div class="profilePicFrame">
        <img src="@/assets/imgs/ProfilePhoto.jpg" alt="profile pic" />
      </div>
      <label class="desc" for="unField">Nombre</label>

      <input
        type="text"
        id="unField"
        :value="UserName"
        class="input-el field base-border body-text soft-el2"
      />

      <label class="desc" for="mailField">Correo</label>

      <input
        type="text"
        id="mailField"
        class="input-el field base-border body-text soft-el2"
      />

      <label class="desc" for="pass">Contraseña</label>

      <input
        type="password"
        id="pass"
        class="input-el field base-border body-text soft-el2"
      />

      <label class="desc" for="passX2">Confirmar contraseña</label>

      <input
        id="passX2"
        type="password"
        class="input-el field base-border body-text soft-el2"
      />

      <button
        id="saveBtn"
        class="accessBtn soft-el2 base-border center-content"
        @click="jumpScreen('ProfileView')"
      >
        GUARDAR
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
  },
  data() {
    return {
      input3: {
        name: "",
        username: "",
        password: "",
        password2: ""
      }
    };
  }
};
</script>

<style scoped>
#profile {
  background-color: white;
  border-radius: 25px 25px 25px 25px;
  max-height: 90vh;
  width: 60%;
}
.profilePicFrame {
  border-color: #ff8e43;
  border-width: 1px;
  display: inline-block;
  width: 10vw;
  height: 10vw;
  overflow: hidden;
  border-radius: 50%;
  margin: auto;
}
.profilePicFrame img {
  width: auto;
  height: 100%;
  margin-left: -15%;
}

.highText {
  font-size: 4vw;
}
#saveBtn {
  min-width: 120px;
  min-height: 40px;
  font-size: 2vw;
  color: #ff8e43;
  width: 12vw;
  height: 3vw;
}
#profile {
  background-color: white;
  width: 60%;
}
</style>
