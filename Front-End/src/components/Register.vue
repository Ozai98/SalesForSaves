<template>
  <div id="register" class="center-content">
    <div id="title">REGISTRARSE</div>
    <div id="name" class="space input-el field base-border">
      <p class="body-text label">Nombre</p>
      <div class="space"></div>
      <input
        type="text"
        name="name"
        v-model="newUser.name"
        class="soft-el body-text regField"
      />
    </div>
    <div id="user" class="space input-el field base-border body-text">
      <p class="label">Correo</p>
      <div class="space"></div>
      <input
        type="text"
        name="User"
        v-model="newUser.mail"
        class="soft-el body-text regField"
      />
    </div>
    <div id="pass" class="space input-el field base-border body-text">
      <p class="label">Contraseña</p>
      <div class="space"></div>
      <input
        type="password"
        name="password"
        v-model="newUser.password"
        class="soft-el body-text regField"
      />
    </div>
    <div id="pass2" class="space input-el field base-border body-text">
      <p class="label">Repita Contraseña</p>
      <div class="space"></div>
      <input
        type="password"
        name="password2"
        v-model="newUser.password2"
        class="soft-el body-text regField"
      />
    </div>
    <div class="base-border center-content inputIMG">
      <label for="fileInput">
        <img
          id="picInput"
          src="@/assets/imgs/photo-camera.svg"
          alt="profile pic"
        />
      </label>
      <input type="file" id="fileInput" @change="onFileSelected" />
    </div>
    <label for="ProviderCheck" class="body-text desc"
      >¿Desea vender productos como proveedor?</label
    >
    <input type="checkBox" id="providerCheck" v-model="isProvider" />
    <button class="button-base accessBtn" id="regBtn" v-on:click="register()">
      REGISTRARSE!
    </button>
  </div>
</template>

<script>
import request from "@/services/request.service.js";
import alert from "vue-simple-alert";
import Vue from "vue";
Vue.use(alert);
export default {
  name: "Register",
  data() {
    return {
      newUser: {
        name: "",
        mail: "",
        password: "",
        password2: "",
        avatar: Object,
      },
      isProvider: false,
    };
  },
  methods: {
    onFileSelected(event) {
      this.newUser.avatar = event.target.files[0];
    },
    register() {
      if (
        this.newUser.mail != "" &&
        this.newUser.password != "" &&
        this.newUser.password2 != "" &&
        this.newUser.name != ""
      ) {
        if (this.newUser.password == this.newUser.password2) {
          let fun_request;
          if (this.isProvider) {
            fun_request = request.createProvider;
          } else {
            fun_request = request.createClient;
          }
          fun_request(
            this.newUser.name,
            this.newUser.mail,
            this.newUser.password,
            this.newUser.avatar,
            (data) => {
              if (data.ok) {
                this.$store.dispatch("storeUser", data.classX);
                this.$emit("goToLogin");
              } else {
                this.$fire({
                  text: "No se pudo crear usuario",
                  titleText: "ERROR CREANDO USUARIO",
                  icon: "error",
                  confirmButtonColor: "#ff8e43",
                  customClass: "swal2-error",
                });
              }
            }
          );
        } else {
          this.$fire({
            text: "Contraseña incorrecta",
            titleText: "ERROR CREANDO USUARIO",
            icon: "error",
            confirmButtonColor: "#ff8e43",
            customClass: "swal2-error",
          });
        }
      } else {
        this.$fire({
          text: "Hacen falta datos",
          titleText: "ERROR CREANDO USUARIO",
          icon: "error",
          confirmButtonColor: "#ff8e43",
          customClass: "swal2-error",
        });
      }
    },
  },
};
</script>

<style scoped>
#register {
  background-color: white;
  justify-items: center;
  text-align: center;

  height: 30vw;
  border-radius: 2vw;
  display: grid;
  grid-template-columns: 1fr;
}
#picInput {
  height: 3vw;
  width: 3vw;
  padding: 0.5vw;
}

.label {
  margin: auto;
  font-size: 0.6vw;
  margin-right: 10px;
  margin-left: 10px;
  min-width: 6vw;
}

.regField {
  width: 15vw;
}
.inputIMG > input {
  display: none;
}
.inputIMG {
  margin-bottom: 0.5vw;
}

#title {
  font-size: 4vw;
  color: #a1ffca;
  -webkit-text-stroke-color: #ff8e43;
  -webkit-text-stroke-width: 1px;
  font-family: "Oswald", sans-serif;
  font-weight: 700;
}

#regBtn {
  background-color: #a1ffca;
  color: #ff8e43;
}

#back {
  height: 100%;
  width: 100%;
  background-color: #ff8e43;
}
</style>
