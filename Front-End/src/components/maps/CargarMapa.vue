<template>
  <div>
    <div
      class="mapa"
      ref="mapa"
    ></div>
    <template v-if="Boolean(this.google) && Boolean(this.map)">
      <slot
        :google="google"
        :map="map"
      />
    </template>
  </div>
</template>
 
<script>
 
  import GoogleMapsApiLoader from "google-maps-api-loader";
 
  export default {
    props: {
      configMapa: Object,
      apiKey: String,
      id:Number,
      pos:Object
    },
 
    data() {
      return {
        google: null,
        map: null,

      };
    },
 
    async mounted() {
      const googleMapApi = await GoogleMapsApiLoader({
        apiKey: this.apiKey
      });
      this.google = googleMapApi;
      this.initializeMap();
      if(this.id!=0){
        this.coords();
      }
    },
 
    methods: {
      initializeMap() {
        const mapContainer = this.$refs.mapa;
        this.map = new this.google.maps.Map(mapContainer, this.configMapa);
      },
      coords(){
      var infoWindow = new google.maps.Marker(
            { position: this.pos});
        infoWindow.setMap(this.map);
      const that= this
        // Configure the click listener.
        this.map.addListener('click', function(mapsMouseEvent) {
          // Close the current InfoWindow.
          infoWindow.setMap(null);

          // Create a new InfoWindow.
          infoWindow = new google.maps.Marker({position: mapsMouseEvent.latLng});
          infoWindow.setMap(that.map);
          var item ={lat:mapsMouseEvent.latLng.lat(),lng:mapsMouseEvent.latLng.lng()};
          that.$emit('cambio',item)
        });
      }
    }
 
  };
</script>
<style scoped>
.mapa{
  width: 100%;
  height: 100%;
}
</style>