<template>
  <div id="all">
    <div id="msgCont">
      <div id="sideBar">
        <div id="tittle">
          Chats
        </div>
        <div id="contacts">
          <div id="SubContacts" v-for="i in dataH" :key="i.id">
            <img
              class="imagen"
              :src="getImage(i.image)"
              @click="getProv(i.provider)"
            />
          </div>
        </div>
      </div>

      <div id="chat">
        <div id="chatBubble">
          <div id="msg" v-for="j in msg" :key="j.idU.mail">
            <div class="contenedor" v-if="j.idU.id == other">
              <img id="imagen2" :src="getImage(j.idProv.avatar)" />
              <p id="user">{{ j.idProv.name }}</p>
              <p id="hora">ayer</p>
              <label id="mensaje">{{ j.idm }}</label>
            </div>
            <div class="contenedor" v-if="j.idU.id == id">
              <img class="imagen3" :src="getImage(j.idU.avatar)" />
              <p class="user2">{{ j.idU.name }}</p>
              <p id="hora2">ayer</p>
              <label id="mensaje2">{{ j.idm }}</label>
            </div>
          </div>
        </div>

        <div class="inputBar">
          <input id="entrada" v-model="m" v-on:keyup.enter="setMsg()" />
        </div>
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
      console.log(this.msg);
    },
    setMsg() {
      if (this.other != -1) {
        console.log(this.id + " " + this.other + " " + this.m);
        request.setMsg(this.id, this.other, this.m, (data) => {
          if (data.ok) {
            this.getmsg();
            this.$forceUpdate();
            this.m = "";
          }
        });
      }
    },
    getProv(provider) {
      this.other = provider;
      this.getmsg();
      this.$forceUpdate();
    },
    getImage(image) {
      return "data:image/jpeg;base64," + image;
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
}
</script>

<style scoped>
#all {
  background-color: white;
  font-family: "Oswald", sans-serif;
  font-weight: bold;
  min-height: 90%;
  display: block;
}
#msgCont {
  display: inline-block;
  width: 80%;
  height: 90vh;
  background-color: #a1ffca;
  margin: 2% 9% 0 9%;
  border: 1px solid #ff8e43;
  border-radius: 1vw 1vw 0 0;
  border-bottom: none;
}
#sideBar {
  float: left;
  height: 100%;
  width: 9%;
}
#chat {
  float: right;
  height: 100%;
  width: 90%;
}
#chatBubble {
  background-color: white;
  border: 1px solid #ff8e43;
  border-radius: 1vw;
  width: 96%;
  height: 80%;
  margin: 1vw 2vw 1vw 1vw;
}

#tittle {
  font-size: 2vw;
}
.imagen {
  width: 4vw;
  height: 4vw;
  border-radius: 3vw;
  border: 0.4vw solid #a1ffca;
}
#contacts {
  margin: 1vw;
  overflow: auto;
}
.contenedor {
  height: 2vw;
  display: grid;
  margin: 1vw;
  border: 1px solid #ff8e43;
  border-radius: 3vw;
}
.inputBar {
  width: 96%;
  height: 2vw;
  display: grid;
  margin: 1vw;
  border: 1px solid #ff8e43;
  border-radius: 3vw;
  background-color: white;
}
#imagen2 {
  grid-row: 2;
  grid-column: 1;
  width: 4vw;
  height: 4vw;
  justify-self: center;
  margin: 0 0 1vw;
}
#user {
  grid-row: 1;
  grid-column: 1;
  margin: 0 0 1vw;
}
#hora {
  grid-row: 1;
  grid-column: 3;
}
#mensaje {
  grid-row: 2;
  grid-column: 2/4;
  text-align: left;
  background-color: gray;
  border-radius: 2vw;
  text-indent: 2vw;
  margin: 1vw;
  font-weight: lighter;
  font-size: 3vw;
  vertical-align: center;
}
.imagen3 {
  grid-row: 2;
  grid-column: 3;
  width: 4vw;
  height: 4vw;
  justify-self: center;
  margin: 0 0 1vw;
}
.user2 {
  grid-row: 1;
  grid-column: 3;
  margin: 0 0 1vw;
}
#hora2 {
  grid-row: 1;
  grid-column: 1;
}
#mensaje2 {
  grid-row: 2;
  grid-column: 1/3;
  text-align: left;
  background-color: blue;
  border-radius: 2vw;
  text-indent: 2vw;
  margin: 1vw;
  font-weight: lighter;
  font-size: 3vw;
  vertical-align: center;
  color: white;
}
#entrada {
  font-family: "Verdana", sans-serif;
  color: #888;
  padding-left: 1vw;
  border-radius: 4vw;
  height: 2vw;
  width: 70%;
  border: none;
  outline: none;
}
</style>
