<template>
  <div>
    COMENTARIOS
    <div class="comment" v-for="data in dataCOM" :key="data.id">
      <unitComment
        :images="data.img"
        :usuario="data.user"
        :comentario="data.comm"
      ></unitComment>
    </div>
    <div>
      <div id="show">
        <img :src="getImage()" class="pictureCOM" />
        <input
          class="inpCOM"
          type="text"
          placeholder="Escribe tu comentario"
          v-model="Text"
          v-on:keyup.enter="send()"
        />
      </div>
    </div>
  </div>
</template>

<script>
import unitComment from "./unitComment.vue";
import request from "../services/request.service.js";
export default {
  data() {
    return {
      Text: "",
      dataCOM: Object,
    };
  },
  props: ["images", "idProvider"],
  components: {
    unitComment,
  },
  mounted() {
    this.getComents();
  },
  methods: {
    getImage() {
      return "data:image/jpeg;base64," + this.$store.getters.returnUser.imgURL;
    },
    getComents() {
      console.log(Number(this.idProvider));
      request.getComments(Number(this.idProvider), (data) => {
        if (data.ok) {
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
    send() {
      request.setComment(
        this.idProvider,
        this.$store.getters.returnUser.id,
        this.Text,
        (data) => {}
      );
    },
  },
};
</script>

<style scoped>
.comment {
  border: 0.2vw solid grey;
  border-radius: 0.2vw;
  margin: 0.5vw;
}
#show {
  margin: 0.5vw;
  border: 0.2vw solid grey;
  border-radius: 0.1vw;
  height: 7.5vw;
}
.inpCOM {
  height: 4vw;
  width: auto;
  min-width: 27vw;
  margin: 0 1vw;
}
.pictureCOM {
  width: 4vw;
  height: 4vw;
  float: left;
  margin: 1vw 0 1vw 1vw;
}
</style>
