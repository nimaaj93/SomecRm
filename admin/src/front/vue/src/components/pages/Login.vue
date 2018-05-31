<template>
  <div class="login-page">
    <!--<submit-wrapper :submitting="submitting">-->
      <div class="login-box">
        <div class="login-logo">
            <span class="image-container">
              <img src="../../assets/logo.png"/>
            </span>
        </div>
        <div class="login-box-body">
          <h4 class="login-box-msg"><b>{{'login.header' | message}}</b></h4>
          <form v-on:submit.prevent="doLogin" autocomplete="no">

            <div style="display: none">
                <input type="password"/>
            </div>

            <div class="form-group has-feedback" :class="{'has-error': errors.has('inputUsername')}">
              <span class="glyphicon glyphicon glyphicon-user form-control-feedback ltr pull-right"></span>
              <input type="text" class="form-control ltr" :placeholder="'login.username' | message"
                     autocomplete="new-password"
                     :disabled="submitting"
                     v-focus
                     id="inputUsername"
                     name="inputUsername"
                     v-validate="'required'"
                     v-model="userCredential.username">
            </div>
            <div class="form-group has-feedback" :class="{'has-error': errors.has('inputPassword')}">
              <span class="glyphicon glyphicon-lock form-control-feedback ltr pull-right"></span>
              <input type="password" class="form-control ltr" :placeholder="'login.password' | message"
                     autocomplete="new-password"
                     :disabled="submitting"
                     id="inputPassword"
                     name="inputPassword"
                     v-validate="'required'"
                     v-model="userCredential.password">
            </div>
            <div class="row">
              <div class="col-xs-4">
              </div>
              <div class="col-xs-4">
                <button type="submit" class="btn btn-primary btn-block btn-flat btn-lg login-btn">
                  {{'login.signin' | message}}
                  <i class="fa fa-sign-in" aria-hidden="true"></i>
                </button>
              </div>

            </div>
          </form>

        </div>
      </div>
    <!--</submit-wrapper>-->
  </div>
</template>

<script>
  import axios from 'axios';
  import 'babel-polyfill';
  import SubmitWrapper from '../SubmitWrapper'

  export default {
    name: 'Login',
    components: {
      SubmitWrapper
    },
    data () {
      return {
        userCredential: {
            username: '',
            password: ''
        },
        submitting: false,
        rememberMe: false
      }
    },
    created: function () {
      window.eventBus.$emit('login-displayed')
    },
    methods: {
        doLogin: function () {
          this.$validator.validateAll().then((result) => {
            if (result) {
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
              }), {
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
                  router.push({path: '/home'});
                })
                .catch((error) => {
                  this.submitting = false;
                  if (error.status == 400) {
                    let errorMsg = options.filters.error('authentication.failed')
                    toasted.error(errorMsg)
                  } else if (error.data.error_description) {
                    let errorMsg = options.filters.error(error.data.error_description)
                    toasted.error(errorMsg)
                  }
                });
              return;
            }
            var msg = this.$options.filters.error('common.form.validation.failed')
            this.$toasted.error(msg)
        })
        }
    }
  }
</script>

<style scoped>
  .login-page {
    background-color: #FFFFFF;
    height: 100vh;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
  }

  .login-box-body {
    background-color: transparent;
  }

  .login-logo .image-container img {
    display: block;
    margin: auto;
    max-width: 200px;
    height: auto;
  }

  .has-error input {
    background-color: #ffffb3;
  }

  .form-control-feedback {
    float: right;
    position: absolute;
    right: 5px;
  }

  .has-feedback input {
    padding-left: 10px;
  }

</style>
