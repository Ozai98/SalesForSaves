<template>
  <div class="home">
    <div class="titulo">
          <h1>SALES FOR SAVES</h1>
    </div>


        <searchbar class="search-bar"></searchbar>
  </div>

</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import searchbar from '@/components/search-bar.vue';

export default {
  name: 'Home',
  components: {
    HelloWorld,
    searchbar
  }
}
</script>

<style>

.titulo{
  background-color: #A1FFCA;
  display: flex;
  font-family: Oswald;
  font-size: 65px;
}

.search-bar input[type=text] {

  padding: 6px;
  border: none;
  margin-top: 8px;
  font-size: 17px;
}
.search-bar button {

  padding: 6px 10px;
  background: #ddd;
  font-size: 17px;
  border: none;
  cursor: pointer;
}
</style>