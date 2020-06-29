<template>
  <div class="commFrame">
    <div class="commentsList">
      <h1 class="titleComments">COMENTARIOS</h1>
      <div class="commContainer">
        <div v-for="data in dataCOM" :key="data.id">
          <unitComment
            :images="data.img"
            :usuario="data.user"
            :comentario="data.comm"
          ></unitComment>
        </div>
      </div>
    </div>
    <div v-if="this.$store.getters.returnLogState" class="commentBox">
      <div class="commentDialog">
        <div class="imgFrame">
          <img :src="getImage()" class="pictureCOM" />
        </div>
        <h2 class="commentUsername">
          {{ this.$store.getters.returnUser.name }}
        </h2>
        <div class="commentBoxLine">
          <textarea
            class="inpCOM"
            v-model="Text"
            cols="80"
            rows="10"
            v-on:keyup.enter="send()"
          >
            Escribe tu comentario
          </textarea>
        </div>
      </div>
      <button
        class="sendComment button-commR"
        type="button"
        v-on:click="send()"
      >
        ENVIAR
      </button>
    </div>
    <div v-else class="commentBox">
      <div class="commentDialog">
        <p class="loginWarning">
          ¡Inicia sesión para comentar!
        </p>
        <button class="button-commR loginButton" v-on:click="opLog()">
          INICIAR SESIÓN
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import UnitComment from "./UnitComment.vue";
import request from "../services/request.service.js";
export default {
  data() {
    return {
      Text: "",
      dataCOM: Object,
      imgURL: "",
    };
  },
  props: {
    idProvider: Number,
  },
  components: {
    UnitComment,
  },
  methods: {
    getImage() {
      return "data:image/jpeg;base64," + this.$store.getters.returnUser.imgURL;
    },
    getComents(idProvider) {
      console.log(idProvider, "holaaaaa");
      request.getComments(Number(idProvider), (data) => {
        if (data.ok) {
          console.log(data);
          this.dataCOM = [];
          for (const datos of data.classX) {
            this.dataCOM.push({
              id: datos.id,
              user: datos.user.name,
              img: datos.user.avatar,
              comm: datos.comment,
            });
          }
        }
      });
    },
    opLog() {
      this.$root.$emit("login");
    },
    send() {
      request.setComment(
        this.idProvider,
        this.$store.getters.returnUser.id,
        this.Text,
        (data) => {
          this.getComents(this.idProvider);
        }
      );
    },
  },
  mounted() {
    this.getImage();
  },
};
</script>

<style scoped>
.commFrame {
  position: absolute;
  width: 80vw;
  height: 25vw;
}
.commentsList {
  float: left;
  width: 55%;
  height: 88%;
  margin: 1.5vw 0 0 1.5vw;
  background-color: white;
  border-radius: 1vw;
  border: 1px solid #ff8e43;
}
.titleComments {
  color: #ff8e43;
  text-align: left;
  padding: 1vw 0 0.5vw 2vw;
  font-size: 2.3vw;
}
.commContainer {
  border-top: 1px solid #ff8e43;
  width: 90%;
  margin-left: 2vw;
  height: 60%;
  overflow: auto;
}
.commentBox {
  position: absolute;
  right: 0;
  border-radius: 1vw;
  width: 37%;
  height: 96%;
  margin: 1.5vw 2vw 0 0;
}
.commentDialog {
  width: 100%;
  height: 75%;
  background-color: white;
  border-radius: 1vw 1vw 0 1vw;
  border: 1px solid #ff8e43;
}
.commentUsername {
  position: absolute;
  top: 3vw;
  left: 6.5vw;
  color: #ff8e43;
}
.inpCOM {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  border: none;
  outline: none;
  padding: 1vw 0 0 1vw;
  font-family: "Verdana", sans-serif;
  font-size: 0.8vw;
  color: #888;
}
.imgFrame {
  position: absolute;
  height: 5vw;
  width: 5vw;
  overflow: hidden;
  border-radius: 100%;
  border: 1px solid #ff8e43;
  top: 1vw;
  left: 1vw;
}
.pictureCOM {
  height: 5vw;
}

.commentBoxLine {
  position: absolute;
  top: 6.2vw;
  left: 3.5vw;
  width: 70%;
  height: 35%;
  border-left: 1px solid #ff8e43;
}
.loginWarning {
  font-size: 2vw;
  padding: 2vw;
}
.loginButton {
  width: 56%;
  height: 3.5vw;
  font-family: "Oswald", sans-serif;
  font-size: 2vw;
  border: 1px solid #ff8e43;
}
.sendComment {
  width: 56%;
  height: 3.5vw;
  font-family: "Oswald", sans-serif;
  font-size: 2vw;
  border: 1px solid #ff8e43;
}
</style>
