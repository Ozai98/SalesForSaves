<template>
    <div id="allCOM">
        <p id="tittle">PEDRO EL VERGAS</p>
        <div id="show">
            <img :src="getImage()" class="pictureCOM" />
            <label class="textCOM" >COMENTARIO PERRON</label>
        </div>
    </div>
</template>

<script>
export default {
 data(){
     return{

     }
 },
 props:['images'],
        
 
 methods:{
     getImage() {
      return 'data:image/jpeg;base64,' + this.images;
    },
 }
 
}
</script>

<style scoped>
#show{
    display: flex;
    height: 5.5vw;
}
.pictureCOM{
    width: 5vw;
    height: 5vw;
    float: left;
}
.textCOM{
    margin:  0vw  1vw  0vw 2vw;
    width: auto;
    align-self: center;
}
</style>