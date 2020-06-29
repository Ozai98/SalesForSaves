<template>
    <div id=all>
        <div id=tittle>
                QUE ONDAAAAAAA
        </div>
        <div id="contacts">
                <div id="SubContacts" v-for="i in dataH" :key="i.id">
                    <img class="imagen" :src="getImage(i.image)" @click="getProv(i.provider)">
                </div>
        </div>
        <div id=chat>
            <div id="msg" v-for="j in msg" :key="j.idU.mail" >
                <div class="contenedor" v-if="j.idU.id==other">
                     <img id="imagen2" :src="getImage(j.idU.avatar)" > 
                     <p id="user">{{j.idU.name}}</p>
                     <p id="hora">ayer</p>
                     <label id="mensaje">{{j.idm}}</label>  
                </div>
                <div class="contenedor" v-if="j.idU.id==id">
                     <img class="imagen3" :src="getImage(j.idU.avatar)" > 
                     <p class="user2">{{j.idU.name}}</p>
                     <p id="hora2">ayer</p>
                     <label id="mensaje2">{{j.idm}}</label> 
                </div>
                
            </div>
            <div class="contenedor" >
                     <img class="imagen3" src="../assets/imgs/user.svg" >
                     <p class="user2">USER</p>
                     <input id="entrada" v-model="m" v-on:keyup.enter="setMsg()"/> 
            </div>
        </div>
    </div>
</template>

<script>
import request from "@/services/request.service.js";
export default {
    data(){
        return{
            dataH:Object,
            msg:Object,
            id: this.$store.getters.returnUser.id,
            state: this.$store.getters.returnUser.isProvider ,
            other:-1,
            m:''
        }
    },
    methods:{
        Backbb() {
      this.dataH = [];
      
      request.getHistoricbyId(this.id, (data) => {
        if (data.ok) {
          for (const hist of data.classX) {
            this.dataH.push({
              time: hist.time,
              price: hist.product.price,
              name: hist.product.name,
              provider: hist.product.provider.id,
              quantity: hist.quantity,
              image: hist.product.provider.avatar,
            });
          }
        }
      });
    },
    BackbbP(){
        this.dataH=[];
        request.getbyProv(this.id, (data)=>{
            if(data.ok){
                console.log(data.class);
               for (const hist of data.classX) {
                 this.dataH.push({
                  time: hist.time,
                  price: hist.product.price,
                  name: hist.product.name,
                  provider: hist.user.id,
                  quantity: hist.quantity,
                  image: hist.user.avatar,
            }); 
            }
            }
        });
    },
    getmsg(){
        this.msg=[]
        if(this.other!=-1){
            console.log(this.id+' '+this.other+' obtene')
            request.getMsg(this.id,this.other,(data)=>{
            if (data.ok){
                console.log("si entro")
                for( const M of data.classX){
                    this.msg.push(
                        {
                            idU: M.user,
                            idP: M.provider,
                            idm: M.content
                        }
                        )
                    }
                }
            });
            
        }
        console.log(this.msg)
    },
    setMsg(){
        if(this.other!=-1){
            var USER,PROV;
             if(this.state){
                USER=this.other;
                PROV=this.id;
            }
            else{
                USER=this.id;
                PROV=this.other;
            }
            console.log(this.id+' '+this.other+' '+this.m)
        request.setMsg(USER,PROV,this.m, (data)=>{
            if(data.ok){
                this.getmsg();
                this.$forceUpdate();
                this.m='';
            }
        });
        }
    },
    getProv(provider){
        this.other=provider;
        this.getmsg();
        this.$forceUpdate();
    },
    getImage(image) {
      return 'data:image/jpeg;base64,' + image;
    }
    },
    mounted(){
        if(this.state){
            this.BackbbP();
        }
        else{
        this.Backbb();
        }
    }
    
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
    width: 7vw;
    height:7vw;
    border-radius: 3vw;
    margin: 2vw;
    border: 0.4vw solid #a1ffca;
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
.imagen3{
    grid-row: 2;
    grid-column: 3;
    width: 4vw;
    height: 4vw;
    justify-self: center;
    margin: 0 0 1vw;
}
.user2{
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
#entrada{
    grid-row: 1/3;
    grid-column: 1/3;
    margin: 1vw;
    border-radius: 4vw;
}
</style>