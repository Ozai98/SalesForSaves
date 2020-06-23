<template>
  <CargarMapa
    :configMapa="configMapa"
    apiKey="AIzaSyCx2ZmmyniRQkOvJeFWMFAxguLTkauTrMU"
    v-bind:id="id"
  >
 
    <template slot-scope="{ google, map }" v-if="id==0">
      <Marcadores
        v-for="marker in markers"
        :key="marker.id"
        :marker="marker"
        :google="google"
        :map="map"
        
      />
    </template>
 
  </CargarMapa>
 
</template>
 
<script>
 
  import CargarMapa from "./CargarMapa";
  import Marcadores from "./Marcadores";
  import { configMapa } from "./configMap";
 
  export default {
    components: {
      CargarMapa,
      Marcadores
    },
 
    data() {
      return {
        
        markers: [
          {
            id: "0",
            position: { lat: 4.6412, lng: -74 },
            title: "Bogota-Colombia"
          },
         
        ]
      };
    },
    props:{
      id:Number
    },
    computed: {
      configMapa() {
        return {
          ...configMapa,
          center: this.mapCenter
        };
      },
 
      mapCenter() {
        return this.markers[0].position;
        
      }
    }
  };
  
</script>