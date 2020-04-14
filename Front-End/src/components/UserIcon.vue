<template>
  <div id="iconFrame">
    <router-link to="{name: login}">
      <img id="userIcon" src="@/assets/user.svg" />
    </router-link>
  </div>
</template>

<script>
export default {
  name: "UserIcon",
  
  props: {
    isLogged: Boolean
  },
  methods: {
    checkLog: function() {
      if (this.$isLogged == true) {
        this.$linkTo = "/ProfileMain";
      } else {
        this.$linkTo = "/Login";
      }
    }
  }
};
</script>
<style scoped>
#iconFrame {
  border-color: #ff8e43;
  border-width: 1px;
  border-style: solid;
  overflow: hidden;
  border-radius: 50%;
  background-color: white;
  position: absolute;
  right: 20px;
  top: 20px;
  width: 32px;
  height: 32px;
}
#userIcon {
  padding-top: 5px;
  width: 20px;
  height: 20px;
}
</style>
