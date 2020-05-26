<template>
<div>
<form>
  <p class="clasification">
    <input id="radio1" type="radio" name="estrellas" value="5" @click="SendRate(5)"><!--
    --><label for="radio1">★</label><!--
    --><input id="radio2" type="radio" name="estrellas" value="4" @click="SendRate(4)"><!--
    --><label for="radio2">★</label><!--
    --><input id="radio3" type="radio" name="estrellas" value="3" @click="SendRate(3)"><!--
    --><label for="radio3">★</label><!--
    --><input id="radio4" type="radio" name="estrellas" value="2" @click="SendRate(2)"><!--
    --><label for="radio4">★</label><!--
    --><input id="radio5" type="radio" name="estrellas" value="1" @click="SendRate(1)"><!--
    --><label for="radio5">★</label>
  </p>
</form>
<button class="button-base accessBtn" @click="Rate()">ENVIAR!</button>
</div>
</template>

<script>
import request from "../services/request.service.js";
export default {
  props:{
    mainData:{
            nombre:String,
            picture:String,
            Userid:Number,
            Provid:Number
    }
    
  },
 data(){
   
   return{
   final:Number}
 },
 methods:{
   SendRate(puntuacion){
     console.log(puntuacion);
     this.final=puntuacion;
     console.log(this.final);
   },
   Rate(){
     request.addRate(this.mainData.Provid,this.mainData.Userid,this.final,(data)=>{ })
   },

 },   mounted(){
   }
}
</script>


<style scoped>
#form {

}

#form p {
  text-align: center;
}

#form label {
  font-size: 100px;
}

input[type="radio"] {
  display: none;
}

label {
  color: grey;
  font-size: 50px;
}

.clasification {
  direction: rtl;
  unicode-bidi: bidi-override;

}

label:hover,
label:hover ~ label {
  color: orange;
}

input[type="radio"]:checked ~ label {
  color: orange;
}
</style>