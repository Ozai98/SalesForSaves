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
            <div class="singleMsgFrame left" v-if="j.idA != state">
              <div class="imgMsgCont right">
                <img class="msgImg" :src="getImage(j.idU.avatar)" />
              </div>
              <div class="msgContent right">
                <p class="userName">{{ j.idU.name }}</p>
                <p class="hora">ayer</p>
                <label class="mensaje">{{ j.idm }}</label>
              </div>
            </div>
            <div class="singleMsgFrame right" v-if="j.idU.id == id">
              <div class="imgMsgCont left">
                <img class="msgImg" :src="getImage(j.idU.avatar)" />
              </div>
              <div class="msgContent left">
                <p class="userName">{{ j.idU.name }}</p>
                <p class="hora">ayer</p>
                <label class="mensaje">{{ j.idm }}</label>
              </div>
            </div>
          </div>
        </div>

        <div class="inputBar">
          <img
            src="@/assets/imgs/send-message.svg"
            id="send"
            alt="send"
            v-on:click="setMsg"
          />
          <textarea
            class="entrada"
            cols="95"
            rows="2"
            v-model="m"
            v-on:keyup.enter="setMsg()"
          ></textarea>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import request from "@/services/request.service.js";
export default {
  data() {
    return {
      dataH: Object,
      msg: Object,
      id: this.$store.getters.returnUser.id,
      state: this.$store.getters.returnUser.isProvider,
      other: -1,
      m: "",
    };
  },
  methods: {
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
    BackbbP() {
      this.dataH = [];
      request.getbyProv(this.id, (data) => {
        if (data.ok) {
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
    getmsg() {
      this.msg = [];
      if (this.other != -1) {
        request.getMsg(this.id, this.other, (data) => {
          if (data.ok) {
            console.log("si entro");
            for (const M of data.classX) {
              this.msg.push({
                idU: M.user,
                idP: M.provider,
                idm: M.content,
                idA: M.senderProvider,
              });
            }
          }
        });
      }
      console.log(this.msg);
    },
    setMsg() {
      console.log("aaaaaaaaaaaaaa");
      if (this.other != -1) {
        var USER, PROV, AUT;
        if (this.state) {
          USER = this.other;
          PROV = this.id;
          AUT = true;
        } else {
          USER = this.id;
          PROV = this.other;
          AUT = false;
        }
        console.log(this.id + " " + this.other + " " + this.m);
        request.setMsg(USER, PROV, this.m, AUT, (data) => {
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
  },
  mounted() {
    if (this.state) {
      this.BackbbP();
    } else {
      this.Backbb();
    }
  },
};
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
.right {
  float: right;
  margin-right: 0;
}
.left {
  float: left;
  margin-left: 0;
}
#sideBar {
  float: left;
  height: 100%;
  width: 9%;
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
  overflow: auto;
}

.singleMsgFrame {
  width: 51%;
  height: 10vh;
  margin: 1vw 0;
}
.imgMsgCont {
  width: 4vw;
}
.msgImg {
  width: 4vw;
  height: 4vw;
  border-radius: 100%;
  overflow: hidden;
}
.msgContent {
  position: relative;
  border: 1px solid #ff8e43;
  width: 80%;
  height: 95%;
  border-radius: 1vw;
}
.userName {
  position: absolute;
  top: 0;
  left: 0.8vw;
  font-size: 1.2vw;
  color: #ff8e43;
}
.hora {
  position: absolute;
  top: 0.4vw;
  right: 0.6vw;
  font-family: "Verdana", sans-serif;
  font-weight: lighter;
  color: #ff8e43;
}
.mensaje {
  position: absolute;
  top: 40%;
  left: 0.8vw;
  font-family: "Verdana", sans-serif;
  font-weight: lighter;
  color: #888;
}

.inputBar {
  position: relative;
  width: 96%;
  height: 10%;
  display: grid;
  margin: 1vw;
  border: 1px solid #ff8e43;
  border-radius: 3vw;
  background-color: white;
  overflow: hidden;
  max-height: 100%;
}
.entrada {
  border: none;
  outline: none;
  width: 80%;
  padding: 1vw;
  font-family: "Verdana", sans-serif;
  font-weight: lighter;
  color: #888;
  overflow: auto;
  font-size: 1vw;
}
#send {
  width: 4vw;
  height: 4vh;
  position: absolute;
  top: 1vw;
  right: 5%;
  cursor: pointer;
}
</style>
