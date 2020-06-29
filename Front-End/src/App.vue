<template>
  <div id="app">
    <head>
      <link
        href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400;500;600;700&display=swap"
        rel="stylesheet"
      />
      <meta
        name="viewport"
        content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height"
      />
    </head>
    <NavBar :idPage="page"></NavBar>
    <router-view />
    <ModalComponent ref="modalLogin">
      <template v-slot:body>
        <Login
          @goToRegister="
            $refs.modalLogin.closeModal();
            $refs.modalRegister.openModal();
          "
          @closeLogin="$refs.modalLogin.closeModal()"
        ></Login>
      </template>
    </ModalComponent>
    <ModalComponent ref="modalRegister">
      <template v-slot:body>
        <Register
          @goToLogin="
            $refs.modalRegister.closeModal();
            $refs.modalLogin.openModal();
          "
        ></Register>
      </template>
    </ModalComponent>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import ModalComponent from "@/components/ModalComponent.vue";
import Login from "@/components/Login.vue";
import Register from "@/components/Register.vue";
export default {
  name: "App",
  data() {
    return {
      idPage: this.$store.getters.returnView,
    };
  },
  components: {
    NavBar,
    ModalComponent,
    Login,
    Register,
  },
  computed: {
    page() {
      return this.$store.getters.returnView;
    },
  },
  mounted() {
    this.$router.replace("Home");
    this.$root.$on("login", (query) => {
      this.$refs.modalLogin.openModal();
    });
  },
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
}
#app {
  position: absolute;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  font-family: "Oswald", sans-serif;
  height: 100%;
  width: 100%;
  bottom: 0;
  overflow: auto;
  margin: auto;
  background-color: #a1ffca;
}

/*Fuente secundaria*/
.body-text {
  font-family: Verdana, sans-serif;
  font-size: 0.8vw;
}
/*Borde que se usa recurrentemente*/
.base-border {
  border-width: 1px;
  border-style: solid;
  border-color: #ff8e43;
}
/*Hacer circular un elemento*/
.circular-frame {
  border-radius: 50%;
}
NavBar {
  z-index: 10;
}
::-webkit-scrollbar {
  width: 7px;
}
::-webkit-scrollbar-track {
  background: #f1f1f1;
}
/* Handle */
::-webkit-scrollbar-thumb {
  background: #ff8e43;
}
/*Centrar el contenido del contenedor*/
.center-content {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.desc {
  color: #888;
  margin: 0.7vw 0;
}
.small-img {
  width: 1vw;
  height: 1vw;
}
/*Elimina resaltados al interactuar y bordes*/
.soft-el {
  outline: none;
  border: none;
  justify-self: center;
}
.soft-el2 {
  outline: none;
}
/*Div que actúa como separador*/
.space {
  background-color: #ff8e43;
  width: 1px;
  height: 1.7vw;
  margin-top: 0.2vw;
  margin-right: 0.2vw;
}
/*Logo de la página*/
.logo {
  width: 2.5vw;
  height: 2.5vw;
  position: absolute;
  top: 1vw;
  left: 1vw;
  background-color: white;
}

.logo:hover {
  cursor: pointer;
}
/*Estilado base de botones*/
.button-base {
  outline: none;
  border-width: 1px;
  border-style: solid;
  border-color: #ff8e43;
}
/*Estilado de los elementos contenedores del input, no el input mismo*/
.input-el {
  border-radius: 56px;
  height: 2vw;
  background-color: white;
  display: flex;
  flex-flow: row;
  font-size: 0.8vw;
}
/*Estilado del elemento input*/
.input {
  margin-right: 0.8vw;
  width: 19vw;
}
/*Estilado de contenedores de input un poco distinto*/
.field {
  height: 2vw;
  width: 25vw;
  color: #ff8e43;
  font-size: 1vw;
  margin: 0.5vw 0 0.5vw 0;
  justify-self: center;
}
/*Estilado de los botones de acceso de login y register*/
.accessBtn {
  border-radius: 16px;
  height: 3vw;
  padding: 0 0.2vw;
  cursor: pointer;
  font-size: 1.5vw;
  font-family: "Oswald", sans-serif;
  margin: 0.5vw 0 0.5vw 0;
  font-weight: bold;
}
.picFrame {
  border-color: #ff8e43;
  border-width: 1px;
  display: inline-block;
  width: 10vw;
  height: 10vw;
  overflow: hidden;
  border-radius: 50%;
  margin: auto;
}
.swal2-error {
  font-family: "Oswald", sans-serif !important;
}
.input-center {
  text-align: center;
}
</style>
