<template>
<div>
<form>
  <p class="clasification">
    <input id="radio1" type="radio" name="estrellas" value="5" @click="Cosas(5)"><!--
    --><label for="radio1">★</label><!--
    --><input id="radio2" type="radio" name="estrellas" value="4" @click="Cosas(4)"><!--
    --><label for="radio2">★</label><!--
    --><input id="radio3" type="radio" name="estrellas" value="3" @click="Cosas(3)"><!--
    --><label for="radio3">★</label><!--
    --><input id="radio4" type="radio" name="estrellas" value="2" @click="Cosas(2)"><!--
    --><label for="radio4">★</label><!--
    --><input id="radio5" type="radio" name="estrellas" value="1" @click="Cosas(1)"><!--
    --><label for="radio5">★</label>
  </p>
</form>
<button class="button-base accessBtn">ENVIAR</button>
</div>
</template>

<script>
export default {
 data(){
   return{
   final:Number}
 },
 methods:{
   Cosas(puntuacion){
     console.log(puntuacion);
     this.final=puntuacion;
     console.log(this.final);
   }
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