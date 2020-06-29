<template>
  <div id="allCOM">
    <div class="imgContainer">
      <div class="picFrame">
        <img :src="getImage()" class="pictureCOM" />
      </div>
    </div>
    <div id="show">
      <p id="commentName">{{ usuario }}</p>
      <p class="textCOM">{{ comentario }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: ["images", "comentario", "usuario"],
  methods: {
    getImage() {
      return "data:image/jpeg;base64," + this.images;
    },
  },
};
</script>

<style scoped>
#allCOM {
  display: flex;
  width: 99%;
  height: 100%;
  border-bottom: 1px solid #bfbfbf;
}
#show {
  display: inline-block;
}
#commentName {
  font-family: "Oswald", sans-serif;
  font-weight: 700;
  font-size: 1.5vw;
  color: #ff8e43;
  text-align: left;
  margin: 1vw 0 0 1vw;
}
.imgContainer {
  display: inline-block;
  width: 13%;
}
.picFrame {
  height: 5vw;
  width: 5vw;
  overflow: hidden;
  border: 1px solid #ff8e43;
}
.pictureCOM {
  height: 5vw;
}
.textCOM {
  display: inline-block;
  margin: 1vw 1vw;
  width: 31.5vw;
  border-radius: 0.1vw;
  text-align: justify;
  font-family: "Verdana", sans-serif;
  color: #888;
}
</style>
