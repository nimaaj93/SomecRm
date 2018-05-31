<template>
  <div class="boxContent loginBox">
    <div class="mainBox">
      <div class="logo"></div>
        <form>
          <transition name="fade">
            <div class="countdown-timer" v-if="!counterFinished">
              <countdown v-on:countdownend="countdownEnd()"
                         v-bind:auto-start="false"
                         ref="countdown"
                         :time="2 * 60 * 1000">
                  <template slot-scope="props">
                      {{props.minutes }}:{{ props.seconds }}
                  </template>
              </countdown>
            </div>
          </transition>

          <span class="inputLable" style="text-align: center">{{'sms.login.header.desc' | message}}</span>
          <span class="justNumber" style="text-align: center">{{phonenumber | phonenumber}}</span>
          <input class="letterSpace" maxlength="5" placeholder="_____"
                 :disabled="submitting"
                 v-model="userCredential.password" />
          <div class="btnBox" :class="{'disabled' : submitting}">
            <a @click="doLogin" class="btn">{{'common.login' | message}}</a>
          </div>
        </form>
        <div class="boxFooter" :class="{'disabled' : submitting}">
          <router-link to="/userpass">{{'user.pass.login.link' | message}}</router-link>
          <a @click="verifyUsername">{{'sms.login.resend.code' | message}}
            <i class="fa fa-refresh" aria-hidden="true"></i></a>
        </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import 'babel-polyfill';
  let instance = {
    name: 'AuthorizationCode',
    data () {
      return {
        submitting: false,
        userCredential: {},
        counterFinished: false,
        phonenumber: ''
      }
    },
    mounted () {
      if (this.$route.params.username && this.$route.params.phonenumber) {
        this.userCredential.username = this.$route.params.username;
        this.phonenumber = this.$route.params.phonenumber;
      } else {
        this.$router.replace({path: '/login'});
      }
      this.$refs.countdown.start();
      this.counterFinished = false;
    },
    methods: {
      doLogin: function () {

        var url = '/oauth/token';
        var querystring = require('querystring');
        var redirect = '';
        var router = this.$router;
        var options = this.$options;
        var toasted = this.$toasted;

        var accessTokenSaved = window.localStorage.getItem('access_token');

        if (accessTokenSaved) {
          window.localStorage.removeItem('access_token');
        }

        this.submitting = true;

        axios.post(url, querystring.stringify({
          grant_type: 'sms',
          username: this.userCredential.username,
          authorization_code: this.userCredential.password
        }),{
          headers: {
            "Authorization": "Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0"
          }
        })
          .then((response) => {
            this.submitting = false;
            var jwtDecode = require('jwt-decode');
            var token = jwtDecode(response.data.access_token)
            window.localStorage.setItem('access_token', response.data.access_token);
            window.localStorage.setItem('refresh_token', response.data.refresh_token);
            window.eventBus.$emit('user-authenticated', token);
            router.push({path: '/orders'});
          })
          .catch((error) => {
            this.submitting = false;
            if (error.data.error_description) {
              let errorMsg = options.filters.error(error.data.error_description)
              toasted.error(errorMsg)
            }
          });

      },
      verifyUsername: function () {
        var url = '/ns/login/verify';
        var querystring = require('querystring');

        var username = this.userCredential.username;

        this.submitting = true;

        axios.post(url, querystring.stringify({
          username: username
        }),{
          headers: {
            "Authorization": "Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0"
          }
        })
          .then((response) => {
            this.submitting = false;
            this.$refs.countdown.init();
            if (this.counterFinished) {
              this.$refs.countdown.start();
            }
            let msg = this.$options.filters.message('sms.login.send.code.success')
            this.$toasted.success(msg)
          })
          .catch((error) => {
            this.submitting = false;
            this.$refs.countdown.stop();
            this.counterFinished = true;
          });
      },
      countdownEnd: function () {
        this.counterFinished = true
      }
    }
  }

  export default instance;
</script>
<style scoped>

  .countdown-timer {
    text-align: center;
    font-size: 40px;
    color: red;
  }

  .boxFooter a {
    margin: 20px;
  }

  .mainBox {
    right: auto;
    left: auto;
    position: relative;
  }

</style>
