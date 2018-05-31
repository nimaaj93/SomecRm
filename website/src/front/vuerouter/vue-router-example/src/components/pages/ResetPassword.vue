<template>
  <div class="boxContent">
    <div class="boxContent">
      <div class="tabBox">
        <div class="tabContent" style="padding: 10px 10px 10px 5px;">
          <div id="tab1" class="tab">
            <div class="boxContentMain">
              <div class="center-form">
                <lazy :loaded="true">

                  <div class="boxContentTitle" style="font-size: 20px">{{'reset.password.link' | message}}</div>

                  <div class="innerRow">
                    <span>{{'reset.password.description.1' | message}}</span>
                    <br/>
                    <span>{{'reset.password.description.2' | message}}</span>
                  </div>

                  <div class="innerRow">
                    <input type="password"
                           id="newpass"
                           name="newpass"
                           :placeholder="'reset.password.newpass' | message | required"
                           :class="{'has-error': errors.has('newpass')}"
                           maxlength="30"
                           v-validate="'required'"
                           :disabled="submitting"
                           v-model="password.pass" />
                  </div>
                  <div class="innerRow">
                    <input type="password"
                           id="confirmpass"
                           name="confirmpass"
                           :placeholder="'reset.password.confirmpass' | message | required"
                           :class="{'has-error': errors.has('confirmpass')}"
                           maxlength="30"
                           v-validate="'required'"
                           :disabled="submitting"
                           v-model="password.confirm" />
                  </div>

                  <a class="btn inlineBtn right" :class="{'disabled' : submitting}" @click="resetPassword">
                    {{'submit.change.password' | message}}
                  </a>

                  <router-link class="btn inlineBtn left" :class="{'disabled' : submitting}" to="/profile">
                    {{'common.back' | message}}
                  </router-link>

                </lazy>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import Lazy from '../Lazy'
  import axios from 'axios'

  export default {
    name: 'ResetPassword',
    components: {
      Lazy
    },
    data () {
      return {
        submitting: false,
        password: {}
      }
    },
    created: function () {

    },
    methods: {
      resetPassword() {
        let url = '/s/resetpass/submit'
        this.$validator.validateAll().then((result) => {
          if (result) {

              if (this.password.pass !== this.password.confirm) {
                let err = this.$options.filters.error('pass.confirm.not.matched');
                this.$toasted.error(err);
                this.password = {};
                return;
              }

            if (this.password.pass.length < 6) {
              let err = this.$options.filters.error('pass.min.length.violated');
              this.$toasted.error(err);
              this.password = {};
              return;
            }

            this.submitting = true;

            var querystring = require('querystring');

            axios.post(url, querystring.stringify({
              newPass: this.password.pass
            }))
              .then(response => {
                this.submitting = false;
                let msg = this.$options.filters.message('reset.password.successful');
                this.$toasted.success(msg);
                this.$router.push({path: '/profile'});
              })
              .catch(e => {
                this.submitting = false;
                this.password = {};
              })
            return
          }
          var msg = this.$options.filters.error('common.form.required.error');
          this.$toasted.error(msg);
        });
      }
    }
  }
</script>
