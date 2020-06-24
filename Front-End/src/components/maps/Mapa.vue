<template>
  <CargarMapa @cambio="cambiarPos($event)"
    :configMapa="configMapa"
    apiKey="AIzaSyCx2ZmmyniRQkOvJeFWMFAxguLTkauTrMU"
    v-bind:id="id"
    v-bind:pos="pos"
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
            position: this.pos,
            title: "Bogota-Colombia"
          },
         
        ]
      };
    },
    props:{
      id:Number,
      pos:Object
    },
    methods:{
      cambiarPos(item){
        this.$emit('cambio2',item);
      }
    }
    ,
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