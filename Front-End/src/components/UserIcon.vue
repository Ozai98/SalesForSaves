<template>
  <div
    id="iconFrame"
    class="base-border circular-frame center-content"
    @click="open()"
  >
    <img
      v-if="this.$store.getters.returnLogState"
      id="userIcon"
      :src="getImage()"
    />
    <img v-else id="userIcon2" src="@/assets/imgs/user.svg" />
  </div>
</template>

<script>
export default {
  name: "UserIcon",
  props: {
    isLogged: Boolean,
  },
  methods: {
    open() {
      if (this.$store.getters.returnLogState) {
        this.jumpScreen("ProfileView");
      } else {
        this.$root.$emit("login");
      }
    },
    getImage() {
      return "data:image/jpeg;base64," + this.$store.getters.returnUser.imgURL;
    },
  },
};
</script>
<style scoped>
#iconFrame {
  background-color: white;
  position: absolute;
  width: 2vw;
  height: 2vw;
  top: 0.5vw;
  right: 0.5vw;
  overflow: hidden;
}
#iconFrame:hover {
  cursor: pointer;
}
#userIcon {
  width: auto;
  height: 2vw;
}
#userIcon2 {
  width: 1vw;
  height: 1vw;
}
</style>
