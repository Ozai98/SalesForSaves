<template>
  <div class="search-bar">
    <button id="btn" type="submit">
      <img src="@/assets/search.svg" id="searchIcon" />
    </button>
    <p id="space">|</p>
    <input type="text" placeholder="Search.." name="search" id="search" />
  </div>
</template>

<script>
export default {
  name: "SearchBar"
};
</script>

<style>
#searchIcon {
  width: 18px;
  height: 18px;
  padding-top: 3px;
  margin: 0;
}
#search {
  width: 550px;
  padding: 0;
  border: none;
}
#search:focus {
  border: none;
  outline: none;
}
#btn {
  background-color: white;
  border-radius: 56px;
}
#btn:focus {
  outline: none;
}
#space {
  color: #ff8e43;
  display: inline-block;
  font-size: 22px;
}

.search-bar {
  border-style: solid;
  border-radius: 56px;
  border-color: #ff8e43;
  border-width: 1px;
  font-size: 16px;
  font-family: "Verdana", sans-serif;
  width: 600px;
  background-color: white;
  margin-left: 35%;
}
</style>
