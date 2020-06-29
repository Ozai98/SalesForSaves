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
    <img v-else id="userIcon" src="@/assets/imgs/user.svg" />
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
      if (this.$store.getters.returnUser.returnLogState) {
        jumpScreen("ProfileView");
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
  width: 3vw;
  height: 3vw;
}
</style>
