<template>
    <div id=all>
        <div id=tittle>
                QUE ONDAAAAAAA
        </div>
        <div id="contacts">
                <div id="SubContacts" v-for="i in 20" :key="i">
                    <img class="imagen" src="@/assets/imgs/user.svg">
                </div>
        </div>
        <div id=chat>
            <div id="msg" v-for="j in 10" :key="j" >
                <div class="contenedor" v-if="j%2==0">
                     <img id="imagen2" src="../assets/imgs/user.svg" >
                     <p id="user">USER</p>
                     <p id="hora">ayer</p>
                     <label id="mensaje">Hola soy un mensaje</label>  
                </div>
                <div class="contenedor" v-if="j%2==1">
                     <img id="imagen3" src="../assets/imgs/user.svg" >
                     <p id="user2">USER</p>
                     <p id="hora2">ayer</p>
                     <label id="mensaje2">Hola soy un mensaje</label> 
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    
}
</script>

<style scoped>
#all{
    background-color: white;
    font-family: "Oswald", sans-serif;
    font-weight: bold;
}
#tittle{
    font-size: 5vw;
}
.imagen{
    width: 6vw;
    height: 6vw;
    border-radius: 3vw;
    margin: 2vw;
}
#contacts{
    display: flex;
    align-items: flex-start;
    margin: 2vw;
    overflow:scroll;
    overflow-y: hidden;
}
.contenedor{
    display: grid;
    margin: 1vw;
    border: 0.2vw solid gray;
    border-radius: 3vw;
}
#imagen2{
    grid-row: 2;
    grid-column: 1;
    width: 4vw;
    height: 4vw;
    justify-self: center;
    margin: 0 0 1vw;
}
#user{
    grid-row: 1;
    grid-column: 1;
    margin: 0 0 1vw;
}
#hora{
    grid-row: 1;
    grid-column: 3;
}
#mensaje{
    grid-row: 2;
    grid-column: 2/4;
    text-align: left;
    background-color: gray;
    border-radius: 2vw;
    text-indent: 2vw ;
    margin: 1vw;
    font-weight: lighter;
    font-size: 3vw;
    vertical-align: center;
}
#imagen3{
    grid-row: 2;
    grid-column: 3;
    width: 4vw;
    height: 4vw;
    justify-self: center;
    margin: 0 0 1vw;
}
#user2{
    grid-row: 1;
    grid-column: 3;
    margin: 0 0 1vw;
}
#hora2{
    grid-row: 1;
    grid-column: 1;
}
#mensaje2{
    grid-row: 2;
    grid-column: 1/3;
    text-align: left;
    background-color:blue;
    border-radius: 2vw;
    text-indent: 2vw ;
    margin: 1vw;
    font-weight: lighter;
    font-size: 3vw;
    vertical-align: center;
    color: white;
}
</style>