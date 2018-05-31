// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueAuth from '@websanova/vue-auth'
import FaLang from './messages/fa'
import EnLang from './messages/en'
import FaError from './errors/fa'
import VeeValidate from 'vee-validate'
import Toasted from 'vue-toasted'
import VueMask from 'v-mask'
import { VueMaskDirective } from 'v-mask'
import PDatePicker from 'vue2-persian-datepicker'

Vue.component('pdatepicker', PDatePicker)
Vue.use(VueMask);
Vue.directive('mask', VueMaskDirective);
Vue.router = router;
Vue.use(VeeValidate);
Vue.use(Toasted, {
  position: 'top-left',
  duration: 5000
})
Vue.use(VueAxios, axios);
// for production
// Vue.axios.defaults.baseURL = 'http://admin.mataconcierge.com:9080/admin/';
// axios.defaults.baseURL = 'http://admin.mataconcierge.com:9080/admin/';
//for dev
Vue.axios.defaults.baseURL = 'http://localhost:8083/';
axios.defaults.baseURL = 'http://localhost:8083/';

Vue.axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
Vue.config.productionTip = false;

Vue.use(require('@websanova/vue-auth'), {
  auth: require('@websanova/vue-auth/drivers/auth/bearer.js'),
  http: require('@websanova/vue-auth/drivers/http/axios.1.x.js'),
  router: require('@websanova/vue-auth/drivers/router/vue-router.2.x.js'),
  fetchData: {url: '/oauth/token?grant_type=password&username=bill&password=abc123', method: 'POST', enabled: true}
})

Vue.directive('focus', {
  inserted: function (el) {
    el.focus()
  }
})

Vue.axios.interceptors.request.use(
  function (request) {
    if (request.url && request.url.indexOf('oauth/token') === -1) {
      request.url = request.url + '?access_token=' + window.localStorage.getItem('access_token')
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
      router.replace('/login')
    }
    return response;
  },
  function (error) {
    if (error.response) {
      if (error.response.status && error.response.status === 401) {
        console.log('redirecting to login')
        router.replace('/login')
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
    return Promise.reject(error.response);
  }
)

// Fonts
require('./assets/css/fonts/glyphicons-halflings-regular.eot')
require('./assets/css/fonts/glyphicons-halflings-regular.svg')
require('./assets/css/fonts/glyphicons-halflings-regular.ttf')
require('./assets/css/fonts/glyphicons-halflings-regular.woff')
require('./assets/css/fonts/glyphicons-halflings-regular.woff2')

require('./assets/css/fonts/ionicons.eot')
require('./assets/css/fonts/ionicons.svg')
require('./assets/css/fonts/ionicons.ttf')
require('./assets/css/fonts/ionicons.woff')

require('./assets/css/fonts/fontawesome-webfont.eot')
require('./assets/css/fonts/fontawesome-webfont.svg')
require('./assets/css/fonts/fontawesome-webfont.ttf')
require('./assets/css/fonts/fontawesome-webfont.woff')
require('./assets/css/fonts/fontawesome-webfont.woff2')

// CSS files
require('./assets/css/animations.css')
require('./assets/css/bootstrap.min.css')
require('./assets/css/AdminLTE.css')
require('./assets/css/skins/custom-mata-skin.css')
require('./assets/css/bootstrap-rtl.min.css')
require('./assets/css/fontcss/font-awesome.min.css')
require('./assets/css/fontcss/ionicons.min.css')
require('./assets/css/persianDatepicker-default.css')
require('./assets/css/theme.css')
require('./assets/css/bootzard.css')
require('./assets/css/vue2-autocomplete.css')
require('./assets/css/spinner/csspin-bubble.css')

// JavaScript
require('./assets/js/jquery.min')
require('./assets/js/bootstrap.min')
require('./assets/js/app.min')
require('./assets/js/persianDatepicker.min')


// Vue filters
Vue.filter('message', function (value) {
  if (FaLang[value]) {
    return FaLang[value]
  }
  console.log('value for key [' + value + '] not defined')
  return ''
})

Vue.filter('error', function (value) {
  if (FaError[value]) {
    return FaError[value]
  }
  console.log('value for key [' + value + '] not defined')
  return ''
})

Vue.filter('reverse', function(value) {
  return value.slice().reverse();
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

Vue.filter('amount', function(value) {
  if (value) {
    return value.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
  }
  return value;
});

window.eventBus = new Vue({})

var globalInstance = new Vue({
  router,
  template: '<App :authenticated="authenticated" :token="token"/>',
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
          var jwtDecode = require('jwt-decode');
          var token = jwtDecode(response.data.access_token)
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
    token: {},
    authenticated: false,
    headerDisplayed: false,
    footerDisplayed: false,
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
  globalInstance.authenticated = true;
  globalInstance.refreshTokenInterval = setInterval(function () {
    globalInstance.refreshToken();
  },30000);
})

window.eventBus.$on('lang-changed', data => {
  globalInstance.language = data
})

window.eventBus.$on('login-displayed', function () {
  globalInstance.headerDisplayed = false
  globalInstance.footerDisplayed = false
})

router.beforeEach((to, from, next) => {

  globalInstance.headerDisplayed = true;
  globalInstance.footerDisplayed = true;

  if (to.name === 'Login') {
    globalInstance.headerDisplayed = false;
    globalInstance.footerDisplayed = false;
    globalInstance.authenticated = false;
  }

  if (to.meta.authenticated === true && globalInstance.authenticated === false) {
    globalInstance.headerDisplayed = false;
    globalInstance.footerDisplayed = false;
    next('/login')
  } else {
    next()
  }
})

globalInstance.$mount("#app");
