<template>
<div>
    COMENTARIOS
    <div>
        <unitComment class="comment" :images="images"></unitComment> 

    </div>
    <div >
        <div id="show">
            <img :src="getImage()" class="pictureCOM" />
            <input class="inpCOM" type="text" placeholder="Escribe tu comentario" v-model="Text" v-on:keyup.enter="send()"/> 
        </div>
    </div>
</div>


</template>

<script>
import unitComment from "./unitComment.vue";
import request from "../services/request.service.js";
export default {
data(){
    return{
    Text:'',
    }
},
     props:['images','idProvider'],            
components:{
    unitComment
},
 
 methods:{
     getImage() {
      return 'data:image/jpeg;base64,' + this.$store.getters.returnUser.imgURL;
    },
    send(){
        request.setComment(this.idProvider,this.$store.getters.returnUser.id,this.Text,(data) => {
            console.log(this.idProvider+"    "+this.$store.getters.returnUser.id+"    "+this.Text);
            if (data.ok){
                console.log("si sirve");

            }
        })
    }
 }
}

</script>

<style scoped>
.comment{
    border: 0.5vw solid black;
}
#show{
    margin: 0.5vw;
    border: 0.2vw solid grey;
    height: 5.5vw;
      display: flex;
  justify-content: flex-end;
  align-items: center;
}
.inpCOM{
    height: 4vw;
    width: auto;
    min-width: 27vw;
    margin: 0 1vw;
}
.pictureCOM{
    width: 5vw;
    height: 5vw;
    float: left;
}
</style>