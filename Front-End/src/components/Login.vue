<template>
  <div id="back" class="center-content">
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
  font: 53px Oswald, Oswald;
  color: #a1ffca;
  -webkit-text-stroke-color: #ff8e43;
  -webkit-text-stroke-width: 1px;
}

.field {
  outline: none;
  font-family: Verdana, sans-serif;
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
#bot {
  font: 40px Oswald, Oswald;
}
#regtext {
  margin-right: auto;
  margin-left: auto;

  color: #ff8e43;
  font: Italic 14px Verdana, Verdana;
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
  height: 100%;
  width: 100%;
  background-color: #ff8e43;
}
</style>
