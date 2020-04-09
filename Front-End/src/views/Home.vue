<template>
  <div class="home">
    <ProfileView UserName="Daniel Pallares" Mail="julio.rojas@correofalso.com" />
  </div>
</template>

<script>
// @ is an alias to /src
import ProfileView from "@/components/ProfileView.vue";

export default {
  name: "Home",
  components: {
    ProfileView
  }
};
</script>
