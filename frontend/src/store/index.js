import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const state = {
  token: localStorage.getItem('user-token') || '',
  username: localStorage.getItem('user-username') || '',
  role: localStorage.getItem('user-role') || '',
  name: localStorage.getItem('user-name') || '',
  surname: localStorage.getItem('user-surname') || '',
  avatar: localStorage.getItem('user-avatar') || ''
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
  },

  getName: state => {
    return state.name
  },

  getSurname: state => {
    return state.surname
  },

  getAvatar: state => {
    return state.avatar
  }

};

const mutations = {
  auth_login: (state, user) => {
    console.log("SAVIIIIIIIIIIIIIIIIIIING")
    localStorage.setItem("user-token", user.token)
    localStorage.setItem("user-username", user.username)
    localStorage.setItem("user-role", () => {
      if (user.role === 'ROLE_USER') {
        return 'user'
      } else if (user.role === 'ROLE_ADMIN') {
        return 'admin'
      }
    })
    localStorage.setItem('user-name', user.name)
    localStorage.setItem('user-surname', user.surname)
    localStorage.setItem('user-avatar', user.avatar)
    console.log("SAVEEEEEEEEEEEED ")
  },

  auth_logout: () => {
    state.token = '';
    state.role = '';
    state.username = '';
    state.name = '';
    state.surname = '',
    state.avatar = ''
    localStorage.removeItem('user-token');
    localStorage.removeItem('user-role');
    localStorage.removeItem('user-username');
    localStorage.removeItem('user-name')
    localStorage.removeItem('user-surname')
    localStorage.removeItem('user-avatar')
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
