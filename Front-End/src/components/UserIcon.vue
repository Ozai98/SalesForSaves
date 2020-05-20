<template>
  <div id="iconFrame" class="base-border circular-frame center-content">
    <img
      v-if="this.$store.getters.returnLogState"
      id="userIcon"
      src="@/assets/imgs/ProfilePhoto.jpg"
      @click="jumpScreen('ProfileView')"
    />
    <img
      v-else
      id="userIcon"
      src="@/assets/imgs/user.svg"
      @click="jumpScreen('Login')"
    />
  </div>
</template>

<script>
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
  methods: {},
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
