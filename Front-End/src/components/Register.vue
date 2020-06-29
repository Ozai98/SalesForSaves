<template>
  <div>
    <div id="chooseRole" class="center-content" v-if="first">
      <div id="title">VAS A...</div>
      <div class="buttonFrame">
        <button id="buy" class="btnC base-border" v-on:click="ch1()">
          COMPRAR
        </button>
        <button id="sell" class="btnC base-border" v-on:click="ch2()">
          VENDER
        </button>
      </div>
    </div>

    <div v-else id="register" class="center-content">
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
          <img id="picInput" :src="url" alt="profile pic" v-if="isUploaded" />
          <img
            v-else
            id="picInput"
            src="@/assets/imgs/photo-camera.svg"
            alt="profile pic"
          />
        </label>
        <input type="file" id="fileInput" @change="onFileSelected" />
      </div>
      <button class="button-base accessBtn" id="regBtn" v-on:click="register()">
        REGISTRARSE!
      </button>
    </div>
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
      isUploaded: false,
      url: "",
      first: true,
      isProvider: false,
      newUser: {
        name: "",
        mail: "",
        password: "",
        password2: "",
        avatar: Object,
      },
      ubc: {
        lat: 4,
        lng: -72,
      },
    };
  },
  props: {},
  methods: {
    mapa() {
      const that = this;
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
          (that.ubc.lat = position.coords.latitude),
            (that.ubc.lng = position.coords.longitude);
        });
      }
    },
    onFileSelected(event) {
      console.log("hey");
      this.isUploaded = true;
      const file = event.target.files[0];
      this.newUser.avatar = file;
      this.url = URL.createObjectURL(file);
    },
    register() {
      if (
        this.newUser.mail != "" &&
        this.newUser.password != "" &&
        this.newUser.password2 != "" &&
        this.newUser.name != ""
      ) {
        console.log(this.newUser);
        console.log(this.ubc);
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
            this.ubc,
            this.newUser.avatar,
            (data) => {
              if (data.ok) {
                this.$store.dispatch("storeUser", data.classX);
                this.$fire({
                  text: "Usuario creado exitosamente",
                  titleText: "¡BIEN!",
                  type: "success",
                  confirmButtonColor: "#ff8e43",
                  customClass: "swal2-error",
                });
                this.$emit("goToLogin");
              } else {
                this.$fire({
                  text: "No se pudo crear usuario",
                  titleText: "ERROR CREANDO USUARIO",
                  type: "error",
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
            type: "error",
            confirmButtonColor: "#ff8e43",
            customClass: "swal2-error",
          });
        }
      } else {
        this.$fire({
          text: "Hacen falta datos",
          titleText: "ERROR CREANDO USUARIO",
          type: "error",
          confirmButtonColor: "#ff8e43",
          customClass: "swal2-error",
        });
      }
    },
    ch1() {
      this.first = false;
    },
    ch2() {
      this.first = false;
      this.isProvider = true;
    },
  },
  mounted() {
    this.mapa();
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
  text-align: right;
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
.buttonFrame {
  width: 100%;
  height: 100%;
}
.btnC {
  width: 48%;
  border-radius: 1vw;
  height: 100%;
  background-color: white;
  color: #ff8e43;
  font-size: 2vw;
  font-family: "Oswald", sans-serif;
  font-weight: 700;
  cursor: pointer;
}
.btnC:hover {
  background-color: #a1ffca;
}
#buy {
  float: left;
}
#sell {
  float: right;
}
#chooseRole {
  background-color: white;
  justify-items: center;
  text-align: center;
  height: 30vw;
  border-radius: 2vw;
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
</style>
