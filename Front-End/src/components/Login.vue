<template>
  <div id="login" class="center-content">
    <div id="title">INICIAR SESION!</div>
    <div id="user" class="center-content">
      <div id="user" class="input-el field base-border body-text">
        <p class="label">E-mail</p>
        <div class="space"></div>
        <input
          type="text"
          name="User"
          v-model="userLog.username"
          class="soft-el body-text input"
        />
      </div>
      <div id="pass" class="input-el field base-border body-text">
        <p class="label">Contraseña</p>
        <div class="space"></div>
        <input
          type="password"
          name="password"
          v-model="userLog.password"
          class="soft-el body-text input"
        />
      </div>

      <div>
        <button
          type="button"
          name="log"
          v-on:click="login()"
          class="accessBtn button-base"
        >
          ENTRAR
        </button>
      </div>
      <div id="regtext">
        si no tienes una cuenta puedes registrarte
      </div>
      <div id="bot2">
        <button
          class="accessBtn button-base"
          type="button"
          v-on:click="goToRegister()"
          name="sin"
        >
          REGISTRARME!
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import * as request from "../services/request.service";
import ModalComponent from "@/components/ModalComponent.vue";
import alert from "vue-simple-alert";
import Vue from "vue";
Vue.use(alert);
export default {
  name: "Login",
  data() {
    return {
      userLog: {
        username: "",
        password: "",
        isProvider: false,
      },
    };
  },
  methods: {
    get_data() {
      let pos = { lat: null, longitud: null };
      /*AQUI OBTENGO LOS DATOS PARA EL LOGIN*/
      request.generalLogin(
        this.userLog.username,
        this.userLog.password,
        (data) => {
          if (data.ok) {
            if (data.classX.isProvider) {
              request.getProviderById(data.classX.client.id, (dataD) => {
                if (dataD.ok) {
                  pos.lat = dataD.classX.ubication.lat;
                  pos.longitud = dataD.classX.ubication.longitud;
                  console.log(pos, "cambio");
                  let builder = {
                    id: data.classX.client.id,
                    name: data.classX.client.name,
                    mail: data.classX.client.mail,
                    avatar: data.classX.client.avatar,
                    isProvider: data.classX.isProvider,
                    ubc: pos,
                  };
                  this.$store.dispatch("storeUser", builder);
                  this.$store.dispatch("changeLogState");
                  this.$emit("closeLogin");
                }
              });
            } else {
              let builder = {
                id: data.classX.client.id,
                name: data.classX.client.name,
                mail: data.classX.client.mail,
                avatar: data.classX.client.avatar,
                isProvider: data.classX.isProvider,
                ubc: null,
              };
              this.$store.dispatch("storeUser", builder);
              this.$store.dispatch("changeLogState");
              this.$emit("closeLogin");
            }
          } else {
            this.$fire({
              text: "no se reconoce el usuario o la contraseña",
              titleText: "ERROR LOGUEANDO USUARIO",
              type: "error",
              confirmButtonColor: "#ff8e43",
              customClass: "swal2-error",
            });
          }
        }
      );
    },
    login() {
      if (this.userLog.username != "" && this.userLog.password != "") {
        this.get_data();
      } else {
        this.$fire({
          text: "Un nombre de usuario y contraseña deben ser presentados",
          titleText: "ERROR LOGUEANDO USUARIO",
          type: "error",
          confirmButtonColor: "#ff8e43",
          customClass: "swal2-error",
        });
      }
    },
    goToRegister() {
      this.$emit("goToRegister");
    },
  },
  components: {
    ModalComponent,
  },
};
</script>

<style scoped>
#login {
  background-color: white;
  justify-items: center;
  text-align: center;
  border-radius: 2vw;
  display: grid;
  grid-template-columns: 1fr;
}
#providerCheck {
  margin: 0.2vw;
}

#title {
  font-size: 3.5vw;
  color: #a1ffca;
  -webkit-text-stroke-color: #ff8e43;
  -webkit-text-stroke-width: 1px;
  font-weight: 700;
  font-family: "Oswald", sans-serif;
}

#back {
  height: 100%;
  width: 100%;
  background-color: #ff8e43;
}

#regtext {
  color: #ff8e43;
  font-family: Verdana;
  font-style: italic;
  margin: 0.5vw 0 0.5vw 0;
}

.label {
  margin: auto;
  font-size: 0.6vw;
  margin-right: 10px;
  margin-left: 10px;
  min-width: 4vw;
}

button {
  min-width: 8vw;
  font-size: 1.7vw;
  font-weight: 700;
}

button[name="log"] {
  background-color: #a1ffca;
  color: #ff8e43;
}

button[name="sin"] {
  background-color: #ffffff;
  color: #ff8e43;
}
</style>
