import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    currentView: "Home",
    isLogged: false,
    user: { id: "", name: "", imgURL: "", mail: "" }
  },
  mutations: {
    CHANGE_VIEW_DISPLAYING(state, newView) {
      state.currentView = newView;
    },
    CHANGE_LOG_STATE(state) {
      state.isLogged = !state.isLogged;
    },
    RESET_USER(state) {
      state.user.id = "";
      state.user.name = "";
      state.user.imgURL = "";
      state.user.mail = "";
    },
    STORE_USER(state, id, name, imgURL, mail) {
      state.user.id = id;
      state.user.name = name;
      state.user.imgURL = imgURL;
      state.user.mail = mail;
    },
    RESTART_LOGIN(state){
      state.isLogged = false;
    }
  },
  actions: {
    changeViewState({ commit }, newView) {
      commit("CHANGE_VIEW_DISPLAYING", newView);
      console.log(newView);
    },
    changeLogState({ state, commit }) {
      commit("CHANGE_LOG_STATE", state);
    },
    resetUser({ state, commit }) {
      commit("RESET_USER", state);
    },
    restartLogin({ state, commit }) {
      commit("RESTART_LOGIN", state);
    }
  },
  getters: {
    returnLogState: state => {
      return state.isLogged;
    },
    returnView: state => {
      return state.currentView;
    },
    returnUser: state => {
      if (state.user.id === "") {
        return "There is no logged user";
      } else {
        return state.user;
      }
    }
  },
  modules: {}
});
