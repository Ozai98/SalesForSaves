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
export default {
  name: "SearchBar",
  data() {
    return {
      sVal: ""
    };
  },
  methods: {
    search() {
      this.$store.dispatch("updateSearch", this.sVal);
      this.$emit("search");
      console.log("Hace algo")
    }
  }
};
</script>

<style>
#search {
  width: 90%;
}

#SearchBtn {
  background-color: white;
  width: 2vw;
  height: 2vw;
}

#SearchBtn:hover {
  cursor: pointer;
}

.search-bar {
  width: 40vw;
}
</style>
