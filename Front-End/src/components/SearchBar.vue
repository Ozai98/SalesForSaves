<template>
  <div class="search-bar base-border input-el">
    <button
      id="SearchBtn"
      type="submit"
      class="center-content circular-frame soft-el"
    >
      <img src="@/assets/imgs/search.svg" class="small-img" @click="search()" />
    </button>
    <div class="space"></div>
    <input
      type="text"
      placeholder="Encuentra aquí lo que necesites"
      name="search"
      id="search"
      class="body-text soft-el"
      v-model="sVal"
      @keyup.enter="search()"
    />
  </div>
</template>

<script>
import SP from "@/views/SearchProduct.vue";
export default {
  name: "SearchBar",
  data() {
    return {
      sVal: "",
    };
  },
  methods: {
    search() {
      console.log("trigger");
      if (this.$store.getters.returnView != "SearchProduct") {
        if (this.sVal == "") {
          console.log("triggerempty");
          this.$router.replace("/SearchProduct/" + "&empty");
        } else {
          console.log("triggerurl");
          this.$router.replace("/SearchProduct/" + this.sVal);
        }
        this.$store.dispatch("changeViewState", "SearchProduct");
      } else {
        this.$root.$emit("search", this.sVal);
      }
    },
  },
};
</script>

<style>
#search {
  width: 90%;
}

#SearchBtn {
  background-color: white;
  max-width: 1.9vw;
  max-height: 1.9vw;
  border-radius: 20%;
  margin: 0.2vw 0.5vw;
}

#SearchBtn:hover {
  cursor: pointer;
}

.search-bar {
  width: 42vw;
}
</style>
