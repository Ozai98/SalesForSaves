<template>
  <div>
    <ProfileData UserName="Daniel Pallares" Mail="juas@mail.com"></ProfileData>
  </div>
</template>

<script>
import ProfileData from "@/components/ProfileData.vue";
export default {
  name: "ProfilePage",
  components: {
    ProfileData
  }
};
</script>
