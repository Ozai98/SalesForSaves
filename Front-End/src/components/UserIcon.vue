<template>
  <router-link :to="{ name: 'Login' }">
    <div id="iconFrame" class="base-border circular-frame center-content">
      <img id="userIcon" src="@/assets/imgs/user.svg" />
    </div>
  </router-link>
</template>

<script>
export default {
  name: "UserIcon",
  props: {
    isLogged: Boolean
  }
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
#userIcon {
  width: 1.2vw;
  height: 1.2vw;
}
</style>
