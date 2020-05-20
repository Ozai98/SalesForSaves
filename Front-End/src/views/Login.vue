<template>

  <div id="back" class="center-content">
    <div id="login">
      <div id="title">INICIAR SESION!</div>
      <div id="user">
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
        <label for="ProviderCheck" class="body-text desc">
          Si eres proveedor dale a la cajita
        </label>
        <input
          type="checkBox"
          id="providerCheck"
          @change="userLog.isProvider = !userLog.isProvider"
        />
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
          O
          <br />si no tienes una cuenta puedes registrarte
        </div>
        <div id="bot2">
          <button
            class="accessBtn button-base"
            type="button"
            
            v-on:click="jumpScreen('Register')" 
            name="sin"
          >
            REGISTRARME!
          </button>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import * as request from "../services/request.service";

import Vue from "vue"
import alet from  "vue-simple-alert" ;
Vue.use(alet);
export default {
  
  name: "Login",
  data() {
    return {
      userLog: {
        username: "",
        password: "",
        isProvider: false
      }
    };
  },
  methods: {
    get_data() {
      /*AQUI OBTENGO LOS DATOS PARA EL LOGIN*/
      let fun_request;
      console.log(this.userLog.isProvider);
      if (this.userLog.isProvider) {
        fun_request = request.loginProveedor;
      } else {
        fun_request = request.loginUsuario;
      }
      console.log(this.userLog);
      fun_request(this.userLog.username, this.userLog.password, data => {
        console.log("Login info received");
        console.log(data);
        if (data.ok) {
          console.log("Usuario logeado");
          console.log(data.clase);
          let builder = {
            id: data.clase.id,
            nombre: data.clase.nombre,
            correo: data.clase.correo,
            avatar: data.clase.avatar,
            isProvider: this.userLog.isProvider
          };
          this.$store.dispatch("storeUser", builder);
          this.$store.dispatch("changeLogState");
          this.jumpScreen("Home");
        } else this.$alert("no se reconoce el usuario o la contraseña","ERROR LOGUEANDO USUARIO",'error');
      });
    },
    login() {
      if (this.userLog.username != "" && this.userLog.password != "") {
        this.get_data();
      } else {
        this.$alert("Un nombre de usuario y contraseña deben ser presentados","ERROR LOGUEANDO USUARIO",'error');
        console.log("A username and password must be present");
      }
    }
  }
};
</script>

<style scoped>
#login {
  background-color: white;
  justify-items: center;
  text-align: center;
  width: 30vw;
  height: 30vw;
  border-radius: 23px;
  display: grid;
  grid-template-columns: 1fr;
}

#title {
  font-size: 4vw;
  color: #a1ffca;
  -webkit-text-stroke-color: #ff8e43;
  -webkit-text-stroke-width: 1px;
  margin-top: 2vw;
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
