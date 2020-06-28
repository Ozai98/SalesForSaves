<template>
  <div
    id="iconFrame"
    :style="style"
    class="base-border circular-frame"
    @click="jump()"
  >
    <img
      v-if="this.$store.getters.returnLogState"
      id="userIcon"
      :src="getImage()"
      :style="style"
    />
    <img v-else id="userIcon" :style="style" src="@/assets/imgs/user.svg" />
  </div>
</template>

<script>
export default {
  name: "UserIcon",
  props: {
    isLogged: Boolean,
    frameSize: "",
  },
  methods: {
    open() {
      this.$root.$emit("login");
    },
    getImage() {
      return "data:image/jpeg;base64," + this.$store.getters.returnUser.imgURL;
    },
    jump() {
      if (this.$store.getters.returnLogState) {
        this.jumpScreen("ProfileView");
      } else {
        open();
      }
    },
  },
  computed: {
    style() {
      return (
        "width: " +
        Number(this.frameSize) +
        "vw\n" +
        "height: " +
        Number(this.frameSize) +
        "vw"
      );
    },
  },
};
</script>
<style scoped>
#iconFrame {
  background-color: white;
  overflow: hidden;
}
#iconFrame:hover {
  cursor: pointer;
}
#userIcon {
  position: relative;
  top: 3px;
}
</style>
