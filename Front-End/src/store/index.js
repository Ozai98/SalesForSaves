import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    currentView: "Home",
    isLogged: false,
    user: { id: -1, name: null, imgURL: null, mail: null, isProvider: false },
    searchedValue: "",
    stateH: true,
    ProvH:""
  },
  mutations: {
    CHANGE_VIEW_DISPLAYING(state, newView) {
      state.currentView = newView;
    },
    CHANGE_LOG_STATE(state) {
      state.isLogged = !state.isLogged;
    },
    RESET_USER(state) {
      state.user.id = -1;
      state.user.name = "";
      state.user.imgURL = "";
      state.user.mail = "";
    },
    STORE_USER(state, user) {
      state.user.id = user.id;
      state.user.name = user.name;
      state.user.imgURL = user.avatar;
      state.user.mail = user.mail;
      state.user.isProvider = user.isProvider;
    },
    RESTART_LOGIN(state) {
      state.isLogged = false;
    },
    UPDATE_SEARCH(state, newVal) {
      state.searchedValue = newVal;
    },
    CHANGE_STATE_H(state,prov){
      state.stateH=!state.stateH;
      state.ProvH=prov;
    }
  },
  actions: {
    changeViewState({ commit }, newView) {
      commit("CHANGE_VIEW_DISPLAYING", newView);
    },
    changeLogState({ state, commit }) {
      commit("CHANGE_LOG_STATE", state);
    },
    resetUser({ state, commit }) {
      commit("RESET_USER", state);
      commit("RESTART_LOGIN", state);
    },
    storeUser({ commit }, user) {
      commit("STORE_USER", user);
    },
    updateSearch({ commit }, val) {
      commit("UPDATE_SEARCH", val);
    },
    updateStateH({commit,state},prov){
      commit("CHANGE_STATE_H",(state,prov));
    }
  },
  getters: {
    returnLogState: (state) => {
      return state.isLogged;
    },
    returnHysState: (state) => {
      return state.stateH;
    },
    returnHysProv: (state) => {
      return state.ProvH;
    },
    returnView: (state) => {
      return state.currentView;
    },
    returnUser: (state) => {
      if (!state.isLogged) {
        return "There is no logged user";
      } else {
        return state.user;
      }
    },
    returnSearchedValue: (state) => {
      if (state.searchedValue == "") {
        return "There is no searched value";
      } else {
        return state.searchedValue;
      }
    },
  },
  modules: {},
});
