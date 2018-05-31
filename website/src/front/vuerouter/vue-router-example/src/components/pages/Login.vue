<template>
    <div class="boxContent loginBox">
      <div class="mainBox">
        <div class="logo"></div>
        <div>
          <form>
            <span>
              <input :placeholder="'login.username' | message"
                     v-validate="'required'"
                     :disabled="submitting"
                     maxlength="10"
                     v-model="userCredential.username"/>
            </span>
            <span>
              <input type="password"
                     maxlength="50"
                     :disabled="submitting"
                     :placeholder="'login.password' | message"
                     v-model="userCredential.password"/>
            </span>
            <div class="btnBox" :class="{'disabled' : submitting}">
              <a class="btn" @click="doLogin">{{'login.button' | message}}</a>
            </div>
          </form>
          <!--<div class="boxFooter" :class="{'disabled' : submitting}">-->
            <!--<router-link to="/login" class="footerlink">{{'sms.login.link' | message}}</router-link>-->
            <!--<router-link to="/signup" class="footerlink">{{'signup.submit.request' | message}}</router-link>-->
          <!--</div>-->
        </div>
      </div>
    </div>
</template>

<script>
  import axios from 'axios';
  import 'babel-polyfill';
  export default {
    name: 'Login',
    data () {
      return {
          submitting: false,
        userCredential: {
            username: '',
            password: ''
        }
      }
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
            grant_type: 'password',
            username: this.userCredential.username,
            password: this.userCredential.password
          }),{
            headers: {
              "Authorization": "Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0"
            }
          })
            .then((response) => {
              this.submitting = false;
              var jwtDecode = require('jwt-decode');
              var token = jwtDecode(response.data.access_token)
              console.log(token)
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
    }
  }
</script>
<style scoped>
  .mainBox {
    right: auto;
    left: auto;
    position: relative;
  }
  .footerlink {
    margin: 20px;
  }
</style>
