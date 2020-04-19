import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ElementUi from 'element-ui'
import Axios from 'axios'
import VueAxios from 'vue-axios'
import Vuex from 'vuex'
import qs from 'qs'

// import css
import 'element-ui/lib/theme-chalk/index.css'

// import js
import routes from './js/router/routes'
import modules from './js/store/store-modules.js'
import {formatDate} from './js/util/date-format'

Vue.use(VueRouter)
Vue.use(ElementUi)
Vue.use(VueAxios, Axios)
Vue.use(Vuex)
Vue.use(qs)

Axios.defaults.baseURL = 'http://localhost:8080'
//Axios.defaults.baseURL = 'http://192.168.56.102:8080'

Axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
Axios.defaults.withCredentials = true

const store = new Vuex.Store({
  modules: modules
})

const router = new VueRouter({
  mode: 'history',
  routes // (缩写) 相当于 routes: routes
})

Vue.filter('date-format', function (value) {
  var date = new Date(value)
  return formatDate(date, 'yyyy-MM-dd hh:mm')
})

new Vue({
  store,
  router,
  el: '#app',
  render: h => h(App)
})
