<template>
  <div id="iconFrame" class="base-border circular-frame center-content">
    <img
      v-if="this.$store.getters.returnLogState"
      id="userIcon"
      :src="getImage()"
      @click="jumpScreen('ProfileView')"
    />
    <img v-else id="userIcon" src="@/assets/imgs/user.svg" @click="open()" />
  </div>
</template>

<script>
import request from "@/services/request.service.js";
import ModalComponent from "@/components/ModalComponent.vue";
export default {
  name: "UserIcon",
  props: {
    isLogged: Boolean,
  },
  computed: {},
  components: {
    ModalComponent,
  },
  methods: {
    open() {
      this.$emit("login");
    },
    getImage() {
      return 'data:image/jpeg;base64,' + this.$store.getters.returnUser.imgURL;
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
}
#iconFrame:hover {
  cursor: pointer;
}
#userIcon {
  width: 1.2vw;
  height: 1.2vw;
}
</style>
