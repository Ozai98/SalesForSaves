<template>
  <div class="container center-content">
    <div id="profile" class="center-content">
      <h1 class="highText">INFORMACIÓN DE PERFIL</h1>
      <div class="profilePicFrame">
        <label for="editProfilePic">
          <img :src="url" alt="profile pic" v-if="isUploaded" />
          <img :src="getImage()" alt="profile pic" v-else />
        </label>
        <input type="file" id="editProfilePic" @change="updateImg" />
      </div>
      <label class="desc body-text" for="unField">Nombre</label>

      <input
        type="text"
        id="unField"
        v-model="newUserInfo.name"
        class="input-el field2 base-border body-text soft-el2"
      />

      <label class="desc body-text" for="pass">Contraseña</label>

      <input
        type="password"
        id="pass"
        v-model="newUserInfo.password"
        class="input-el field2 base-border body-text soft-el2"
      />

      <label class="desc body-text" for="passX2">Confirmar contraseña</label>

      <input
        id="passX2"
        type="password"
        v-model="newUserInfo.password2"
        class="input-el field2 base-border body-text soft-el2"
      />
      <Map
        @cambio2="actualizar($event)"
        class="mapa2"
        v-bind:id="1"
        v-bind:pos="{ lat: 4.665713941327049, lng: -74.08367224701064 }"
      />

      <button
        id="saveBtn"
        class="accessBtn soft-el2 base-border center-content"
        @click="updateUser()"
      >
        GUARDAR
      </button>
    </div>
  </div>
</template>

<script>
import request from "@/services/request.service.js";
import Map from "../components/maps/Mapa";
export default {
  name: "ProfileView",
  props: {
    UserName: String,
    Mail: String,
    Alt: Boolean,
  },
  data() {
    return {
      url: "",
      isUploaded: false,
      newUserInfo: {
        name: "",
        password: "",
        password2: "",
        avatar: null,
        ubc: {
          lat: "",
          lng: "",
        },
      },
    };
  },
  components: {
    Map,
  },
  methods: {
    actualizar(item) {
      this.newUserInfo.ubc.lat = item.lat;
      this.newUserInfo.ubc.lng = item.lng;
      console.log(this.newUserInfo.ubc, "ubicación nueva");
    },
    updateUser() {
      let isProvider = this.$store.getters.returnUser.isProvider;
      let new_name = null,
        new_pass = null,
        new_avatar = null;
      if (this.newUserInfo.name != "") new_name = this.newUserInfo.name;
      if (this.newUserInfo.password != "") {
        if (this.newUserInfo.password == this.newUserInfo.password2)
          new_pass = this.newUserInfo.password;
      }
      if (this.newUserInfo.avatar != null) new_avatar = this.newUserInfo.avatar;
      const callback = (data) => {
        if (data.ok) {
          data.classX.isProvider = isProvider;
          this.$store.dispatch("storeUser", data.classX);
          this.jumpScreen("ProfileView");
          this.$fire({
            text: "Los cambios se han guardado con éxito",
            titleText: "¡BIEN!",
            type: "success",
            confirmButtonColor: "#ff8e43",
            customClass: "swal2-error",
          });
        } else {
          this.$fire({
            text: "Ocurrió un error al editar el usuario",
            titleText: "ERROR EDITANDO USUARIO",
            type: "error",
            confirmButtonColor: "#ff8e43",
            customClass: "swal2-error",
          });
        }
      };
      if (isProvider) {
        request.updateProvider(
          this.$store.getters.returnUser.id,
          new_name,
          new_pass,
          new_avatar,
          this.newUserInfo.ubc,
          callback
        );
      } else {
        request.updateClient(
          this.$store.getters.returnUser.id,
          new_name,
          new_pass,
          new_avatar,
          callback
        );
      }
    },
    getImage() {
      return "data:image/jpeg;base64," + this.$store.getters.returnUser.imgURL;
    },
    updateImg(event) {
      this.isUploaded = true;
      const file = event.target.files[0];
      this.newUserInfo.avatar = file;
      this.url = URL.createObjectURL(file);
    },
  },
};
</script>

<style scoped>
#profile {
  background-color: white;
  border-radius: 25px 25px 25px 25px;
  width: 60%;
}
.profilePicFrame {
  border: 1px solid #ff8e43;
  display: inline-block;
  width: 10vw;
  height: 10vw;
  overflow: hidden;
  border-radius: 100%;
  margin: auto;
}
.profilePicFrame img {
  width: 100%;
  height: 100%;
  cursor: pointer;
}
.profilePicFrame > input {
  display: none;
}

.highText {
  font-size: 3vw;
  color: #ff8e43;
}
#saveBtn {
  font-size: 2vw;
  color: #ff8e43;
  width: 12vw;
  height: 3vw;
}
.mapa2 {
  height: 20vw;
  width: 25vw;
  margin: 1.5vw 1.5vw 1.5vw 1.5vw;
  border: solid 1px #ff8e43;
  border-radius: 1vw;
  overflow: hidden;
}
</style>
