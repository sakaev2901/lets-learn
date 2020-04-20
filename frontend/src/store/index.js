import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const state = {
  token: localStorage.getItem('user-token') || '',
  username: localStorage.getItem('user-name') || '',
  role: localStorage.getItem('user-role') || '',
};

const getters = {
  isAuthenticated: state => {
    return state.token != null && state.token != '';
  },

  isAdmin: state => {
    return state.role === 'admin'
  },

  getUsername: state => {
    return state.username
  },

  getRole: state => {
    return state.role
  },

  getToken: state => {
    return state.token
  }

};

const mutations = {
  auth_login: (state, user) => {
    localStorage.setItem("user-token", user.token)
    localStorage.setItem("user-name", user.username)
    localStorage.setItem("user-role", () => {
      if (user.role === 'ROLE_USER') {
        return 'user'
      } else if (user.role === 'ROLE_ADMIN') {
        return 'admin'
      }
    })
  },

  auth_logout: () => {
    state.token = '';
    state.role = '';
    state.username = '';
    localStorage.removeItem('user-token');
    localStorage.removeItem('user-role');
    localStorage.removeItem('user-name');
}
};

const actions = {
  login: (context, user) => {
    context.commit('auth_login', user)
  },
  logout: (context) => {
    context.commit('auth_logout')
  }
};

export const store = new Vuex.Store({
  state,
  getters,
  mutations,
  actions
});
