<template>
  <div>
    <div class="ContainerNoGrid">
      <center>
        <p class="titleH">TU HISTORIAL</p>
        <div class="totalSave">Tu ahorro : {{ this.save }}</div>
        <div id="j" v-for="history in dataH" :key="history.name">
          <HistoryProduct :historicalProduct="history"></HistoryProduct>
        </div>
      </center>
    </div>

    <!-- <div class="ContainerGrid" v-else>
      <div class="titleH">
        <p>TU HISTORIAL</p>
      </div>
      <div class="encierro">
        <clf :clasication="this.test" class="calificacion"></clf>

        <div class="table" @click="exit()">
          <div id="j" v-for="history in dataH" :key="history.name">
            <HistoryProduct :historicalProduct="history"></HistoryProduct>
          </div>
        </div>
      </div>
    </div> -->
    <div class="profileSummary">
      <UserIcon class="icontainer" frameSize="5"></UserIcon>
    </div>
  </div>
</template>

<script>
import HistoryProduct from "../components/HistoryProduct.vue";
import request from "@/services/request.service.js";
import clf from "@/components/Calification.vue";
import UserIcon from "@/components/UserIcon.vue";
export default {
  components: {
    UserIcon,
    HistoryProduct,
    clf,
  },
  data() {
    return {
      id: this.$store.getters.returnUser.id,
      dataH: Object,
      save: 0,
      test: {
        nombre: String,
        picture: String,
        Userid: this.$store.getters.returnUser.id,
        Provid: Number,
      },
      active: true,
    };
  },
  computed: {
    state() {
      return this.$store.getters.returnHysState;
    },
    prov() {
      return this.$store.getters.returnHysProv;
    },
  },
  watch: {
    state: function (val) {
      console.log("EN HYS" + this.test.nombre);
      this.active = !this.active;
    },
    prov: function (val) {
      if (val != null) {
        this.test.nombre = val.name;
        this.test.Provid = val.id;
        this.test.picture = val.avatar;
      }
    },
  },
  methods: {
    //this.$store.getters.returnHysState
    Backbb() {
      this.dataH = [];

      request.getHistoricbyId(this.id, (data) => {
        if (data.ok) {
          console.log(data.classX);
          for (const hist of data.classX) {
            this.dataH.push({
              time: hist.time,
              price: hist.product.price,
              name: hist.product.name,
              provider: hist.product.provider,
              quantity: hist.quantity,
              image: hist.product.image,
            });
            this.save = hist.product.saved * hist.quantity + this.save;
          }
        }
      });
    },
    exit() {
      if (!this.active) {
        this.$store.dispatch("updateStateH", "");
      }
    },
  },
  mounted() {
    this.Backbb();
  },
};
</script>

<style scoped>
.titleH {
  font-family: Oswald;
  font-size: 70px;
  margin: 0%;
  text-align: center;
  grid-column: 1/3;
}
.totalSave {
  display: flex;
  align-items: flex-end;
  flex-direction: column;
  font-size: 3vw;
}
.ContainerNoGrid {
  padding: 2vw;
  align-self: center;
  height: 90%;
  background-color: white;
}
.ContainerGrid {
  padding-bottom: 2vw;
  padding-top: 2vw;
  align-self: center;
  height: 90%;
  background-color: white;
}
.profileSummary {
  width: 25%;
  height: 60%;
  position: fixed;
  top: 12%;
  left: 0;
  border: 1px solid #ff8e43;
  border-left: none;
  border-radius: 0 2vw 0 0;
  background-color: white;
}
.icontainer {
  width: 5vw;
  height: 5vw;
}
.encierro {
  display: flex;
}
.calificacion {
  position: sticky;
  top: 10vw;
  max-width: 25vw;
  max-height: 35vw;
}
#j {
  margin: 1vw;
}
.que {
  width: 50px;
  height: 4vw;
}

.table {
  display: flex;
  align-items: flex-end;
  flex-direction: column;
}
</style>
