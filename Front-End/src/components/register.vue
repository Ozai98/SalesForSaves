<template>
  <div id="back">
    <div id="register">
      <div id="tittle">REGISTRARSE</div>
      <div id="name">
        <input
          type="text"
          name="name"
          v-model="input2.name"
          placeholder="Nombre   |"
        />
      </div>
      <div id="user">
        <input
          type="text"
          name="User"
          v-model="input2.username"
          placeholder="E-mail   |"
        />
      </div>
      <div id="pass">
        <input
          type="password"
          name="password"
          v-model="input2.password"
          placeholder="Contraseña    |"
        />
      </div>
      <div id="pass2">
        <input
          type="password"
          name="password2"
          v-model="input2.password"
          placeholder="Contraseña    |"
        />
      </div>
      <div id="bot">
        <button tipe="button" name="reg" v-on:click="register()">
          REGISTRARSE!
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Register",
  data() {
    return {
      input2: {
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
        this.input2.username != "" &&
        this.input2.password != "" &&
        this.input2.password2 != "" &&
        this.input2.name != ""
      ) {
        if (this.input2.password == this.input2.password2) {
            axios
            .post('http://savesforsales-back.herokuapp.com/usuario/crear',this.input2)
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
  position: absolute;
  top: 50%;
  left: 50%;
  text-align: center;
  width: 658px;
  height: 602px;
  border: 1px solid #cccccc;
  background-color: #ffffff;
  margin-left: -329px; /*half width*/
  margin-top: -301px; /*half height*/
  border-radius: 23px;
}
#tittle {
  font: 53px Oswald, Oswald;
  color: #a1ffca;
  position: relative;
  top: 5%;
  -webkit-text-stroke-color: #ff8e43;
  -webkit-text-stroke-width: 1px;
}
#name {
  position: absolute;
  top: 28%;
  left: 50%;
  margin-left: -254px;
}
#user {
  position: absolute;
  top: 40%;
  left: 50%;
  margin-left: -254px;
}
#pass {
  position: absolute;
  top: 52%;
  left: 50%;
  margin-left: -254px;
}
#pass2 {
  position: absolute;
  top: 64%;
  left: 50%;
  margin-left: -254px;
}
#bot {
  position: absolute;
  top: 80%;
  left: 50%;
  margin-left: -140px;
  font: 40px Oswald, Oswald;
}
#regtext {
  margin-right: auto;
  margin-left: auto;
  margin-top: 51%;
  width: 50%;
  color: #ff8e43;
  font: Italic 14px Verdana, Verdana;
}
#bot2 {
  position: absolute;
  top: 77%;
  left: 50%;
  margin-left: -140px;
}
input {
  height: 50px;
  width: 504px;
  border: 1px solid #ff8e43;
  border-radius: 25px;
}
input::placeholder {
  position: relative;
  color: #ff8e43;
  right: -10px;
}

button {
  background-color: #a1ffca;
  border: 1px solid #ff8e43;
  color: #ff8e43;
  border-radius: 16px;
  height: 78px;
  width: 254px;
  cursor: pointer;
  font: 40px Oswald, Oswald;
}
body {
  background-color: #ff8e43;
}
#back {
  position: absolute;
  top: 0%;
  left: 0%;
  height: 100%;
  width: 100%;
  background-color: #ff8e43;
}
</style>
