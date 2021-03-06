import Vue from 'vue'
  import App from './App.vue'
import router from './router'
import {store} from './store'
import vuetify from './plugins/vuetify';
import axios from 'axios'


export const AXIOS = axios.create({
  baseURL: 'http://localhost:8080/api'
});

// AXIOS.interceptors.response.use(undefined, function (error) {
//   if(error.response.status === 403) {
//     router.push("/signIn");
//     return Promise.reject(error);
//   }
// });

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: function (h) { return h(App) }
}).$mount('#app')
