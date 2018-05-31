<template>
  <div class="boxContent loginBox">
    <div class="mainBox">
      <div class="logo"></div>
        <form>
          <span :class="{'has-error': errors.has('inputUsername')}">
            <input :placeholder="'login.username' | message"
                   maxlength="10"
                   :disabled="submitting"
                   name="inputUsername" id="inputUsername"
                   v-validate="'required'"
                   v-model="userCredential.username"/>
          </span>
          <div class="btnBox" :class="{'disabled' : submitting}">
            <a class="btn" @click="verifyUsername">{{'common.confirm' | message}}</a>
          </div>
        </form>
        <div class="boxFooter" :class="{'disabled' : submitting}">
          <router-link class="footerlink" to="/userpass">{{'user.pass.login.link' | message}}</router-link>
          <router-link class="footerlink" to="/signup">{{'signup.submit.request' | message}}</router-link>
        </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import 'babel-polyfill';
  export default {
    name: 'UsernameVerification',
    data () {
      return {
        submitting: false,
        userCredential: {}
      }
    },
    methods: {
      verifyUsername: function () {
        this.$validator.validateAll().then((result) => {
          if (result) {
            var url = '/ns/login/verify';
            var querystring = require('querystring');
            var router = this.$router;

            var username = this.userCredential.username;

            this.submitting = true;

            axios.post(url, querystring.stringify({
              username: username
            }), {
              headers: {
                "Authorization": "Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0"
              }
            })
              .then((response) => {
                this.submitting = false;
                let phoneNumber = response.data
                router.push({path: '/authorizationcode/' + username + '/' + phoneNumber});
              })
              .catch((error) => {
                this.submitting = false;
                console.log('verification failed');
              });
            return
          }
          var msg = this.$options.filters.error('common.form.required.error')
          this.$toasted.error(msg)
        });
      }
    }
  }
</script>
<style scoped>
  .mainBox {
    left: auto;
    right: auto;
    position: relative;
  }
  .footerlink {
    margin: 20px;
  }
</style>
