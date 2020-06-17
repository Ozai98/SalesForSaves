<template>
  <div class="center-content" id="framing">
    <h1>FILTRAR</h1>
    <svg height="1" width="500">
      <line
        x1="170"
        y1="0"
        x2="360"
        y2="0"
        style="stroke: #ff8e43; stroke-width: 4;"
      />
    </svg>
    <ul>
      <li
        v-for="category in categories"
        :key="category"
        class="listItem desc"
        v-on:click="search(category)"
      >
        {{ category }}
      </li>
    </ul>
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
    search(query) {
      this.$root.$emit("searchCat", query);
    },
  },
};
</script>

<style scoped>
#framing {
  position: fixed;
  border: 1px solid #ff8e43;
  max-width: 20vw;
  max-height: 60vh;
  border-radius: 0 2vw 0 0;
  margin: 5vw 0;
  padding: 2vw 0;
}
#framing h1 {
  color: #ff8e43;
  margin-top: 1vw;
}
ul {
  list-style-type: none;
  border-left: solid 1px #ff8e43;
  text-align: left;
}
li {
  font-size: 1.1vw;
  font-family: Verdana, sans-serif;
  padding-left: 0.5vw;
}
li:hover {
  cursor: pointer;
  color: #ff8e43;
}
</style>
