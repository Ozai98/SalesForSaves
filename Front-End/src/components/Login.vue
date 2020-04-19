<template>
  <div id="back">
    <div id="login">
      <div id="title">INICIAR SESION!</div>
      <div id="user">
        <input
          type="text"
          name="username"
          v-model="input.username"
          placeholder="E-mail   |"
          class="soft-el "
        />
      </div>
      <div id="pass">
        <input
          type="password"
          name="password"
          v-model="input.password"
          placeholder="Contraseña    |"
          class="field"
        />
      </div>
      <div id="bot">
        <button
          type="button"
          name="log"
          v-on:click="
            login();
            jumpProfile();
          "
        >
          ENTRAR
        </button>
      </div>
      <div id="regtext">
        O
        <br />si no tienes una cuenta puedes registrarte
      </div>
      <div id="bot2">
        <button type="button" v-on:click="jumpScreen('Register')" name="sin">
          REGISTRARME!
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import * as request from "../services/request.service";

var enter;
export default {
  name: "Login",
  data() {
    return {
      input: {
        username: "",
        password: ""
      }
    };
  },
  methods: {
    jumpProfile() {
      this.$router.replace({ name: "ProfilePage" });
    },
    get_data() {
      /*AQUI OBTENGO LOS DATOS PARA EL LOGIN*/
      request.loginUsuario('email1', '0000', (data) => {
        console.log('Login info recived');
        console.log(data);
        if(data.ok) console.log('Usuario logeado');
        else console.log('Error logeando usuario');
      });
      
    },
    login() {
      
      if (this.input.username != "" && this.input.password != "") {
        this.get_data();
        if (enter) {
          this.$emit("authenticated", true);
          this.$router.replace({ name: "secure" });
        } else {
          console.log("The username and / or password is incorrect");
        }
      } else {
        console.log("A username and password must be present");
      }
    }
  }
};
</script>

<style scoped>
#login {
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
#title {
  font: 53px Oswald, Oswald;
  color: #a1ffca;
  position: relative;
  top: 5%;
  -webkit-text-stroke-color: #ff8e43;
  -webkit-text-stroke-width: 1px;
}

.field {
  outline: none;
  font-family: Verdana, sans-serif;
}

#user {
  position: absolute;
  top: 28%;
  left: 50%;
  margin-left: -254px;
}
#pass {
  position: absolute;
  top: 40%;
  left: 50%;
  margin-left: -254px;
}
#bot {
  position: absolute;
  top: 55%;
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

button[name="log"] {
  background-color: #a1ffca;
  border: 1px solid #ff8e43;
  color: #ff8e43;
  border-radius: 16px;
  height: 78px;
  width: 254px;
  cursor: pointer;
  font: 40px Oswald, Oswald;
}
button[name="sin"] {
  background-color: #ffffff;
  border: 1px solid #ff8e43;
  color: #ff8e43;
  border-radius: 16px;
  height: 78px;
  width: 254px;
  cursor: pointer;
  font: 40px Oswald, Oswald;
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
