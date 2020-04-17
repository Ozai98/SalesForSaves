<template>
  <div class="search-bar base-border">
    <button
      id="SearchBtn"
      type="submit"
      class="center-content circular-frame soft-el"
    >
      <img
        src="@/assets/imgs/search.svg"
        class="small-img"
        @click="jumpSearch()"
      />
    </button>
    <div class="space"></div>
    <input
      type="text"
      placeholder="Encuentra aquí lo que necesites"
      name="search"
      id="search"
      class="body-text soft-el"
      @keyup.enter="jumpSearch()"
    />
  </div>
</template>

<script>
export default {
  name: "SearchBar",
  methods: {
    jumpSearch() {
      this.$router.replace({ name: "SearchProduct" });
    }
  }
};
</script>

<style>
#search {
  width: 90%;
  font-size: 0.8vw;
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
  border-radius: 56px;
  font-family: "Verdana", sans-serif;
  width: 40vw;
  margin-top: 4vw;
  height: 2vw;
  background-color: white;
  display: flex;
  flex-flow: row;
}
</style>
