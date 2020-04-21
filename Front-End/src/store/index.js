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
    STORE_USER(state, user) {
      state.user.id = user.id;
      state.user.name = user.nombre;
      state.user.imgURL = user.avatar;
      state.user.mail = user.correo;
    },
    RESTART_LOGIN(state) {
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
    },
    storeUser({ commit }, user) {
      commit("STORE_USER", user);
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
      if (!state.isLogged) {
        return "There is no logged user";
      } else {
        return state.user;
      }
    }
  },
  modules: {}
});
