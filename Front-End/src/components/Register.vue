<template>
  <div id="back" class="center-content">
    <div id="register" class="center-content">
      <div id="title">REGISTRARSE</div>
      <div id="name" class="space input-el field base-border">
        <p class="body-text label">Nombre</p>
        <div class="space"></div>
        <input
          type="text"
          name="name"
          v-model="newUser.name"
          class="soft-el body-text input"
        />
      </div>
      <div id="user" class="space input-el field base-border body-text">
        <p class="label">Correo</p>
        <div class="space"></div>
        <input
          type="text"
          name="User"
          v-model="newUser.username"
          class="soft-el body-text input"
        />
      </div>
      <div id="pass" class="space input-el field base-border body-text">
        <p class="label">Contraseña</p>
        <div class="space"></div>
        <input
          type="password"
          name="password"
          v-model="newUser.password"
          class="soft-el body-text input"
        />
      </div>
      <div id="pass2" class="space input-el field base-border body-text">
        <p class="label">Repita Contraseña</p>
        <div class="space"></div>
        <input
          type="password"
          name="password2"
          v-model="newUser.password2"
          class="soft-el body-text"
          id="rep"
        />
      </div>
      <button class="button-base" v-on:click="register()">
        REGISTRARSE!
      </button>
    </div>
  </div>
</template>

<script>
import request from "@/services/request.service.js";
export default {
  name: "Register",
  data() {
    return {
      newUser: {
        name: "",
        username: "",
        password: "",
        password2: ""
      }
    };
  },
  methods: {
    register() {
      if (
        this.newUser.username != "" &&
        this.newUser.password != "" &&
        this.newUser.password2 != "" &&
        this.newUser.name != ""
      ) {
        if (this.newUser.password == this.newUser.password2) {
          request.crearUsuario(
            this.newUser.name,
            this.newUser.username,
            this.newUser.password,
            data => {
              if (data.ok) console.log("Usuario registrado correctamente");
              else console.log("No se pudo registrar el usuario");
            }
          );

          /*if(!(request.makeRequest('/usuario/crear',newUser).then(result => console.log(result.data.ok)))){
                console.log("No se pudo registrar el usuario");
            }*/
        } else {
          console.log("Contraseña incorrecta");
        }
      } else {
        console.log("Faltan datos");
      }
    }
  }
};
</script>

<style scoped>
#register {
  background-color: white;
  justify-items: center;
  text-align: center;
  width: 30vw;
  height: 40vw;
  border-radius: 23px;
  display: grid;
  grid-template-columns: 1fr;
}

.field {
  height: 2vw;
  width: 25vw;
  color: #ff8e43;
  font-size: 0.8vw;
}
.label {
  margin: auto;
  font-size: 0.6vw;
  margin-right: 10px;
  margin-left: 10px;
}
.input {
  width: 19vw;
}
#rep {
  width: 15vw;
}
#title {
  font-size: 4vw;
  color: #a1ffca;
  -webkit-text-stroke-color: #ff8e43;
  -webkit-text-stroke-width: 1px;
}
button {
  background-color: #a1ffca;
  color: #ff8e43;
  border-radius: 16px;
  height: 78px;
  width: 254px;
  cursor: pointer;
  font: 40px Oswald, Oswald;
}
#back {
  height: 100%;
  width: 100%;
  background-color: #ff8e43;
}
</style>
