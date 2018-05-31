// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import axios from 'axios';
import VueAxios from 'vue-axios';
import VueAuth from '@websanova/vue-auth';
import FaLang from './messages/fa'
import EnLang from './messages/en'
import FaError from './errors/fa'
import EnError from './errors/en'
import VeeValidate from 'vee-validate'
import Toasted from 'vue-toasted'
import VueMask from 'v-mask'
import { VueMaskDirective } from 'v-mask'
import VueCountdown from '@xkeshi/vue-countdown'
import ScrollBar from 'vue2-scrollbar'

Vue.use(Vuex);
Vue.component('countdown', VueCountdown);
Vue.component('scrollbar', ScrollBar);
Vue.use(VueMask);
Vue.directive('mask', VueMaskDirective);
Vue.router = router;
Vue.use(VeeValidate);
Vue.use(VueAxios, axios);
// for producton
// Vue.axios.defaults.baseURL = 'http://members.mataconcierge.com:9080/web/';
// axios.defaults.baseURL = 'http://members.mataconcierge.com:9080/web/';
// for dev
Vue.axios.defaults.baseURL = 'http://localhost:8081/';
axios.defaults.baseURL = 'http://localhost:8081/';

Vue.axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
Vue.config.productionTip = false;

Vue.use(require('@websanova/vue-auth'), {
  auth: require('@websanova/vue-auth/drivers/auth/bearer.js'),
  http: require('@websanova/vue-auth/drivers/http/axios.1.x.js'),
  router: require('@websanova/vue-auth/drivers/router/vue-router.2.x.js'),
  fetchData: {url: '/oauth/token?grant_type=password&username=bill&password=abc123', method: 'POST', enabled: true}
})

window.eventBus = new Vue({})

Vue.directive('focus', {
  inserted: function (el) {
    el.focus()
  }
})

Vue.axios.interceptors.request.use(
  function (request) {
    if (request.url && request.url.indexOf('oauth/token') === -1 && request.url.indexOf('ns/login') === -1 &&
      request.url.indexOf('ns/signup') === -1 ) {
      if (window.localStorage.getItem('access_token')) {
        request.url = request.url + '?access_token=' + window.localStorage.getItem('access_token')
      }
    }

    if (request.url && request.url.indexOf('oauth/token') === -1) {
      if (request.url.substring(0,1) !== '/') {
        request.url = '/' + request.url;
      }
      request.url = '/rest' + request.url;
    }

    return request;
  }
)

Vue.axios.interceptors.response.use(
  function (response) {

    if (response.status === 401) {
      console.log('redirecting to login')
      window.eventBus.$emit('logout')
    }
    return response;
  },
  function (error) {
    if (error.response) {
      if (error.response.status && error.response.status === 401) {
        console.log('redirecting to login')
        window.eventBus.$emit('logout')
      } else if (error.response.status && error.response.status === 504) {
        let msg = globalInstance.$options.filters.error('server.connection.failed')
        globalInstance.$toasted.error(msg)
      } else {
        var msg
        if (error.response.data.reason) {
          msg = globalInstance.$options.filters.error(error.response.data.reason)
          globalInstance.$toasted.error(msg)
        }
        // else {
        //   msg = globalInstance.$options.filters.error('common.action.failed')
        // }
      }
    }
    return Promise.reject(error.response);;
  }
)

require('./assets/css/animations.css')
require('./assets/css/normalize.css')
require('./assets/css/jquery.jscrollpane.css')
require('./assets/css/global.css')
require('./assets/css/members.css')
require('./assets/css/login.css')
require('./assets/css/vazir.css')
require('./assets/css/font-awesome.min.css')
require('./assets/css/vue2-scrollbar.css')
require('./assets/css/ltr.css')

require('./assets/js/jquery-3.2.1.min')
require('./assets/js/jquery.mousewheel')
require('./assets/js/jquery.jscrollpane.min')

const store = new Vuex.Store({
  state: {
    newMessagesCount: 0,
    newOffersCount: 0,
    token: {},
    language: 'fa'
  },
  mutations: {
    setNewMessagesCount (state,count) {
      state.newMessagesCount = count
    },
    setNewOffersCount (state,count) {
      state.newOffersCount = count
    },
    setToken (state,token) {
      state.token = token
    },
    changeLanguage (state, lang) {
      state.language = lang
    }
  }
})

// Vue filters
Vue.filter('message', function (value) {
  if (store.state.language === 'fa') {
    if (FaLang[value]) {
      return FaLang[value]
    }
  } else {
    if (EnLang[value]) {
      return EnLang[value]
    }
  }
  console.log('value for key [' + value + '] not defined')
  return ''
})

Vue.filter('error', function (value) {
  if (store.state.language === 'fa') {
    if (FaError[value]) {
      return FaError[value]
    }
  } else {
    if (EnError[value]) {
      return EnError[value]
    }
  }
  console.log('value for key [' + value + '] not defined')
  return ''
})

Vue.filter('reverse', function(value) {
  return value.slice().reverse();
});

Vue.filter('required', function(value) {
  if (store.state.language === 'fa') {
    return value + ' (اجباری) ';
  } else {
    return value + ' (required) ';
  }
});

Vue.filter('summarize', function(value,length) {
  if (value && length) {
    if (value.length > length) {
      return value.slice(0, length) + ' ...'
    }
    return value
  }
  return ''
});

Vue.filter('phonenumber', function(value) {
  if (value && value.length === 11) {
    return value.slice(0,4) + ' ' + value.slice(4,7) + ' ' + value.slice(7,9) + ' ' + value.slice(9,11)
  }
  return value;
});

Vue.filter('amount', function(value) {
  if (value) {
    return value.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
  }
  return value;
});

var globalInstance = new Vue({
  router,
  store,
  template: '<App :authenticated="authenticated"/>',
  components: { App },
  mounted() {
    let accessToken = window.localStorage.getItem('access_token');
    let refreshToken = window.localStorage.getItem('refresh_token');
    if (accessToken && refreshToken) {
      var jwtDecode = require('jwt-decode');
      var token = jwtDecode(accessToken)
      var url = '/oauth/token';
      var querystring = require('querystring');

      axios.post(url, querystring.stringify({
        grant_type: 'refresh_token',
        refresh_token: window.localStorage.getItem('refresh_token')
      }),{
        headers: {
          "Authorization": "Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0"
        }
      })
        .then(function (response) {
          window.localStorage.setItem('access_token', response.data.access_token);
          window.localStorage.setItem('refresh_token', response.data.refresh_token);
          window.eventBus.$emit('user-authenticated', token);
        })
        .catch(function (error) {
          window.eventBus.$emit('logout');
        });
    } else {
      window.eventBus.$emit('logout');
    }
  },
  data: {
    token: null,
    authenticated: false,
    refreshTokenInterval: null,
    language: 'fa'
  },
  methods: {
    refreshToken: function () {
      console.log('refresh token called!')
      var url = '/oauth/token';
      var querystring = require('querystring');

      axios.post(url, querystring.stringify({
        grant_type: 'refresh_token',
        refresh_token: window.localStorage.getItem('refresh_token')
      }),{
        headers: {
          "Authorization": "Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0"
        }
      })
        .then(function (response) {
          window.localStorage.setItem('access_token', response.data.access_token);
          window.localStorage.setItem('refresh_token', response.data.refresh_token);
        })
        .catch(function (error) {
          console.log('Error refreshing token');
        });
    }
  }
})

Vue.use(Toasted, {
  position: 'top-right',
  duration: 5000
})

window.eventBus.$on('logout', function () {
  globalInstance.token = null;
  globalInstance.authenticated = false;
  clearInterval(globalInstance.refreshTokenInterval);
  window.localStorage.removeItem('access_token');
  window.localStorage.removeItem('refresh_token');
  router.replace('/login')
})

window.eventBus.$on('user-authenticated', data => {
  globalInstance.token = data;
  store.commit('setToken', data);
  globalInstance.authenticated = true;
  globalInstance.refreshTokenInterval = setInterval(function () {
    globalInstance.refreshToken();
  },30000);
  window.eventBus.$emit('new-msg-load');
  window.eventBus.$emit('new-offer-load');
})

window.eventBus.$on('new-msg-load', () => {

  axios.get('s/promotion/newmessagecount')
    .then(response => {
      console.log('new msg count: ' + response.data )
      store.commit('setNewMessagesCount',response.data);
  })
    .catch(e => {
    })

})

window.eventBus.$on('new-offer-load', () => {

  axios.get('s/promotion/newoffercount')
    .then(response => {
      console.log('new offer count: ' + response.data )
      store.commit('setNewOffersCount',response.data);
    })
    .catch(e => {
    })

})

router.beforeEach((to, from, next) => {
  if (to.name === 'Login' || to.name === 'AuthorizationCode' || to.name === 'UsernameVerification') {
    if (globalInstance.authenticated) {
      next('/orders')
    }
  }
  if (to.meta.authenticated === true && globalInstance.authenticated === false) {
    console.log('globalInstance.authenticated: ' + globalInstance.authenticated)
    console.log('to.meta.authenticated: ' + to.meta.authenticated)
    next('/login')
  } else {
    next()
  }
})

globalInstance.$mount("#app");
