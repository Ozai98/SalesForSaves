<template>
  <div id="container">
    <NavBar idPage="EditProfile"></NavBar>
    <div id="profile">
      <h1 class="highText">INFORMACIÓN DE PERFIL</h1>
      <div class="profilePicFrame">
        <img src="@/assets/imgs/13440.jpg" alt="profile pic" />
      </div>
      <br />
      <label for="unField">Nombre</label>
      <br />
      <input type="text" id="unField" :value="UserName" class="field" />
      <br />
      <label for="mailField">Correo</label>
      <br />
      <input type="text" id="mailField" class="field" />
      <br />
      <label for="pass">Contraseña</label>
      <br />
      <input type="password" id="pass" class="field" />
      <br />
      <label for="passX2">Confirmar contraseña</label>
      <br />
      <input id="passX2" type="password" class="field" />
      <br />
      <button id="saveBtn"@click="jumpProfile()">GUARDAR</button>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "ProfileView",
  props: {
    UserName: String,
    Mail: String,
    Alt: Boolean
  },
  data() {
    return {
      input3: {
        name: "",
        username: "",
        password: "",
        password2: ""
      }
    };
  },
  components: {
    NavBar
  },
  methods:{
    jumpProfile(){
      this.$router.replace({ name: "ProfilePage" });
    },
    register() {
      if (
        this.input3.username != "" &&
        this.input3.password != "" &&
        this.input3.password2 != "" &&
        this.input3.name != ""
      ) {
        if (this.input3.password == this.input3.password2) {
            if(!(request.makeRequest('/usuario/update',input2).then(result => console.log( result.data.ok)))){
                console.log("No se pudo registrar el usuario");
            }
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
* {
  margin: 0;
  padding: 0;
}

label {
  font-size: 14px;
  font-family: Verdana, sans-serif;
  color: #888888;
}

.profilePicFrame {
  border-color: #ff8e43;
  border-width: 1px;
  border-style: solid;
  display: inline-block;
  width: 270px;
  height: 270px;
  overflow: hidden;
  border-radius: 50%;
  margin-top: 50px;
  margin-bottom: 20px;
}
.profilePicFrame img {
  width: auto;
  height: 100%;
  margin-left: -15%;
}
.highText {
  margin-top: 100px;
  font-size: 53px;
}

.field {
  border-radius: 5px;
  margin-top: 10px;
  margin-bottom: 20px;
  padding: 4px;
  font-family: "Oswald", sans-serif;
  font-size: 41px;
  color: #888888;
  border-style: solid;
  border-color: #ff8e43;
  border-radius: 50px;
}

#saveBtn {
  background-color: #a1ffca;
  min-width: 120px;
  min-height: 40px;
  font-size: 26px;
  color: #ff8e43;
  border-style: solid;
  border-width: 1px;
  border-color: #ff8e43;
  font-family: "Oswald", sans-serif;
  border-radius: 14px;
  margin-bottom: 20px;
  padding: 3px 6px 3px 6px;
}

#container {
  background-color: #a1ffca;
  position: absolute;
  top: 0%;
  left: 0%;
  height: 100%;
  width: 100%;
  overflow: auto;
}
#profile {
  background-color: white;
  font-family: "Oswald", sans-serif;
  text-align: center;
  border-radius: 56px 56px 56px 56px;
  margin: 50px 0 50px 20%;
  position: absolute;
  width: 60%;
}
</style>
