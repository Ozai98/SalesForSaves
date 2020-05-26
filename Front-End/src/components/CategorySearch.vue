<template>
  <div class="center-content">
    <label for="comboBox" class="desc">
      Tambien puedes buscar por categoria!
    </label>
    <select v-model="sVal" @change="search" id="comboBox">
      <option v-for="category in categories">{{ category }}</option>
      ></select
    >
  </div>
</template>

<script>
import request from "@/services/request.service.js";
export default {
  mounted() {
    this.getOptions();
  },
  data() {
    return {
      sVal: "",
      categories: [],
    };
  },
  methods: {
    getOptions() {
      request.getCategories((data) => {
        if (data.ok) this.categories = data.classX;
      });
    },
    search() {
      this.$store.dispatch("updateSearch", this.sVal);
      console.log(this.sVal);
      this.$emit("search2");
    },
  },
};
</script>

<style></style>
