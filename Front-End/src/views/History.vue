<template>
  <div class="ContainerNoGrid" v-if="active">
    <center>
      <p class="titleH" id="T1">TU HISTORIAL</p>
      <div class="totalSave">HAS AHORRADO ${{ this.save }}</div>
      <div id="j" v-for="history in dataH" :key="history.name">
        <HistoryProduct :historicalProduct="history"></HistoryProduct>
      </div>
    </center>
  </div>

  <div class="ContainerGrid" v-else>
    <div class="titleH" id="T2">
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
  </div>
</template>

<script>
import HistoryProduct from "../components/HistoryProduct.vue";
import request from "@/services/request.service.js";
import clf from "@/components/calification.vue";
export default {
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
              time: hist.reserveMoment,
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
  components: {
    HistoryProduct,
    clf,
  },
  mounted() {
    this.Backbb();
  },
};
</script>

<style scoped>
.titleH {
  font-family: Oswald;
  font-size: 3vw;
  font-weight: 700;
  color: #ff8e43;
  text-align: left;
  grid-column: 1/3;
}
#T1 {
  margin-left: 13.5%;
}
#T2 {
  margin-left: 25.5%;
}

.totalSave {
  position: absolute;
  top: 13%;
  right: 10%;
  font-size: 2.5vw;
  font-family: "Oswald", sans-serif;
  font-weight: 700;
  color: #ff8e43;
}
.ContainerNoGrid {
  padding: 1vw 2vw 2vw 2vw;
  align-self: center;
  height: 90%;
  background-color: white;
}
.ContainerGrid {
  padding-bottom: 2vw;
  padding-top: 1vw;
  align-self: center;
  height: 90%;
  background-color: white;
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
