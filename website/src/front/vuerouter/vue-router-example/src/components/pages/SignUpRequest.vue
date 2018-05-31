<template>
  <div class="boxContent loginBox">
    <div class="logo signup-logo"></div>
    <form v-if="!submitted">
      <div class="group2col">
        <input :placeholder="'common.name' | message | required"
               :class="{'has-error': errors.has('name')}"
               maxlength="50"
               :disabled="submitting"
               v-validate="'required'"
               v-model="signUpRequest.firstNameFa"
                name="name"/>
        <input :placeholder="'common.lastname' | message | required"
               :class="{'has-error': errors.has('lastname')}"
               maxlength="50"
               :disabled="submitting"
               v-validate="'required'"
               name="lastname"
               v-model="signUpRequest.lastNameFa"/>
      </div>
      <div class="group2col">
        <input :placeholder="'common.mobile' | message | required"
               :class="{'has-error': errors.has('mobile')}"
               maxlength="11"
               :disabled="submitting"
               v-validate="'required'"
               name="mobile"
               v-model="signUpRequest.phoneNumber"/>
        <input :placeholder="'common.email' | message | required"
               :class="{'has-error': errors.has('email')}"
               maxlength="100"
               :disabled="submitting"
               v-validate="'required'"
               name="email"
               v-model="signUpRequest.email"
              />
      </div>

      <div class="group2col">
        <input :placeholder="'signup.method' | message"
               style="width: 100%"
               maxlength="100"
               :disabled="submitting"
               v-model="signUpRequest.learnMethod"
        />
      </div>

      <textarea :placeholder="'common.description' | message"
                :disabled="submitting"
                maxlength="200"
                v-model="signUpRequest.description"></textarea>
      <div class="btnBox" :class="{'disabled' : submitting}">
        <a @click="submitRequest" class="btn">{{'signup.submit.request' | message}}</a>
        <router-link to="/login" class="footerlink">{{'login.page' | message}}</router-link>
      </div>
    </form>
    <div v-else class="result-container">

      <span>{{'signup.request.success.1' | message}}</span>
      <br/>
      <span>{{'signup.request.success.2' | message}}</span>
      <br/>
      <a href="http://mataconcierge.com/" class="btn backBtn">{{'back.to.main.page' | message}}</a>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: 'SignUpRequest',
    data () {
      return {
        submitted: false,
        submitting: false,
        signUpRequest: {
        }
      }
    },
    methods: {
      submitRequest() {

        var url = '/ns/signup/request';

        this.$validator.validateAll().then((result) => {
          if (result) {

            this.submitting = true;

            axios.post(url, this.signUpRequest)
              .then((response) => {
                this.submitting = false;
                this.submitted = true;
              })
              .catch((error)  => {
                this.submitting = false;
              });
            return;
          }
          this.submitting = false
          var msg = this.$options.filters.error('common.form.required.error')
          this.$toasted.error(msg)
        });
      }
    }
  }
</script>
<style scoped>
  .signup-logo {
    top: 100px;
    right: 500px;
    position: absolute;
    width: 100px;
    margin: auto;
  }

  .result-container {
    margin: 200px 10px 10px 10px;
    height: 500px;
    text-align: center;
    color: white;
  }
  .backBtn {
    margin-top: 20px;
  }
  .footerlink {
    margin: 20px;
  }
</style>
