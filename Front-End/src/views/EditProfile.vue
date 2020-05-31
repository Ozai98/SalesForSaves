<template>
  <div class="container center-content">
    <div id="profile" class="center-content">
      <h1 class="highText">INFORMACIÓN DE PERFIL</h1>
      <div class="profilePicFrame">
        <label for="editProfilePic">
          <img :src="getImage()" alt="profile pic" />
        </label>
        <input type="file" id="editProfilePic" @change="updateImg" />
      </div>
      <label class="desc body-text" for="unField">Nombre</label>

      <input
        type="text"
        id="unField"
        v-model="newUserInfo.name"
        class="input-el field base-border body-text soft-el2"
      />

      <label class="desc body-text" for="pass">Contraseña</label>

      <input
        type="password"
        id="pass"
        v-model="newUserInfo.password"
        class="input-el field base-border body-text soft-el2"
      />

      <label class="desc body-text" for="passX2">Confirmar contraseña</label>

      <input
        id="passX2"
        type="password"
        v-model="newUserInfo.password2"
        class="input-el field base-border body-text soft-el2"
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
export default {
  name: "ProfileView",
  props: {
    UserName: String,
    Mail: String,
    Alt: Boolean,
  },
  data() {
    return {
      newUserInfo: {
        name: "",
        password: "",
        password2: "",
        avatar: null,
      },
    };
  },
  methods: {
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
        } else {
          this.$fire({
            text: "Ocurrió un error al editar el usuario",
            titleText: "ERROR EDITANDO USUARIO",
            icon: "error",
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
          null,
          new_avatar,
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
      return 'data:image/jpeg;base64,' + this.$store.getters.returnUser.imgURL;
    }, 
    updateImg(event) {
      this.newUserInfo.avatar = event.target.files[0];
    },
  },
};
</script>

<style scoped>
#profile {
  background-color: white;
  border-radius: 25px 25px 25px 25px;
  height: 85vh;
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
.profilePicFrame > input {
  display: none;
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
