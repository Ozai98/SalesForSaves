<template>
    <div id="allCOM">
        <p id="tittle">{{usuario}}</p>
        <div id="show">
            <img :src="getImage()" class="pictureCOM" />
            <label class="textCOM" >{{comentario}}</label>
        </div>
    </div>
</template>

<script>
export default {
 data(){
     return{

     }
 },
 props:['images','comentario','usuario'],
        
 
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
    height: 6.5vw;
}
.pictureCOM{
    width: 5vw;
    height: 5vw;
    float: left;
    margin: 0vw 1vw ;
}
.textCOM{
    margin:  0vw  1vw  0vw 1vw;
    width: 50vw;
    height: 3vw;
    align-self: center;
    align-content: center;
    align-items: center;
    border: 0.1vw gray solid;
    border-radius: 0.1vw;
}
</style>