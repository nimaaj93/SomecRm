<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
        <span class="col-md-2">
        </span>
        <span class="col-md-8">
          <h2>
            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
            {{'new.mail' | message}}
          </h2>
        </span>
      </div>
    </section>
    <section class="content">
      <lazy :loaded="true">
        <div class="row">
          <div class="col-md-1">
          </div>
          <div class="col-md-10">
            <div class="row" :class="{'form-submit' : submitting}">
              <form v-on:submit.prevent="sendLetter" role="form" novalidate>
                <div class="col-xs-12">
                  <div class="box">
                    <div class="box-header">
                      <h3 class="box-title">
                        {{'mail.contact' | message}}
                        <i class="fa fa-user" aria-hidden="true"></i>
                      </h3>
                    </div>
                    <div class="box-body row">
                      <div class="col-md-1">
                      </div>
                      <div class="col-md-10">
                        <autocomplete
                          :url="getSearchUrl()"
                          anchor="searchAnchor"
                          label="searchTitle"
                          :customParams="{ access_token: getToken() }"
                          :min="3"
                          :debounce="500"
                          :placeholder="'user.search' | message"
                          :on-select="loadSelectedUser">
                        </autocomplete>
                      </div>
                      <div class="col-md-1">
                      </div>
                    </div>
                  </div>


                  <div class="box">
                    <div class="box-body">
                      <div class="col-md-1">
                      </div>
                      <div class="col-md-10">
                        <div class="row">
                          <div class="table-wrapper" v-if="selectedUser.userId != null">
                            <table id="resultTable" class="table table-bordered table-striped">
                              <tbody>
                              <tr>
                                <td width="50%">{{'common.firstName' | message}}</td>
                                <td width="50%">{{ selectedUser.firstNameFa }}</td>
                              </tr>
                              <tr>
                                <td width="50%">{{'common.lastName' | message}}</td>
                                <td width="50%">{{ selectedUser.lastNameFa }}</td>
                              </tr>
                              <tr>
                                <td width="50%">{{'common.nationalCode' | message}}</td>
                                <td width="50%">{{ selectedUser.nationalCode }}</td>
                              </tr>
                              <tr>
                                <td width="50%">{{'common.phoneNumber' | message}}</td>
                                <td width="50%">{{ selectedUser.phoneNumber }}</td>
                              </tr>
                              <tr>
                                <td width="50%">{{'common.email' | message}}</td>
                                <td width="50%">{{ selectedUser.email }}</td>
                              </tr>
                              </tbody>
                            </table>
                          </div>
                          <div v-else>
                            {{'please.choose.user' | message}}
                          </div>
                        </div>
                      </div>
                      <div class="col-md-1">
                      </div>
                    </div>
                  </div>

                  <div class="box">
                    <div class="box-body row">
                      <div class="col-md-1">
                      </div>
                      <div class="col-md-10">

                        <div class="row">
                          <div class="form-group" :class="{'has-error': errors.has('mailHeader')}">
                            <label class="control-label"
                                   v-show="errors.has('mailHeader')">{{'common.required' | error}}</label>
                            <label for="mailHeader"
                                   v-show="!errors.has('mailHeader')">{{'mail.header' | message}} *</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                  <i class="fa fa-phone-square" aria-hidden="true"></i>
                                </span>
                              <input type="text" id="mailHeader" class="form-control"
                                     autocomplete="off"
                                     maxlength="80"
                                     :disabled="submitting"
                                     name="mailHeader"
                                     v-validate="'required'"
                                     :placeholder="'mail.header' | message"
                                     v-model="letter.header">
                            </div>
                          </div>
                        </div>

                        <div class="row">
                          <div class="form-group" :class="{'has-error': errors.has('inputDescription')}">
                            <label class="control-label"
                                   v-show="errors.has('inputDescription')">{{'common.required' | error}}</label>
                            <label for="inputDescription"
                                   v-show="!errors.has('inputDescription')">{{'mail.content' | message}} *</label>
                            <div class="input-group fill">
                              <textarea type="text" id="inputDescription" class="form-control container"
                                        maxlength="1000"
                                        :disabled="submitting"
                                        name="inputDescription"
                                        v-validate="'required'"
                                        :placeholder="'mail.content' | message"
                                        v-model="letter.description">
                              </textarea>
                            </div>
                          </div>
                        </div>

                        <div class="row">
                          <div class="col-md-12 button-container">
                            <button class="btn btn-primary pull-right"
                                    :disabled="submitting"
                                    type="submit">
                              {{'common.send' | message}}
                            </button>
                          </div>
                        </div>

                      </div>
                      <div class="col-md-1">
                      </div>
                    </div>
                  </div>
                </div>
              </form>
            </div>



          </div>
          <div class="col-md-1">
          </div>
        </div>
      </lazy>
    </section>

  </div>
</template>

<script>
  import axios from 'axios';
  import Lazy from '../../Lazy';
  import Autocomplete from 'vue2-autocomplete-js'

  export default {
    name: 'NewMail',
    components: {
      Lazy,Autocomplete
    },
    data () {
      return {
        submitting: false,
        selectedUser: {},
        letter: {}
      }
    },
    created: function () {

    },
    methods: {
      getSearchUrl() {
        return axios.defaults.baseURL + '/rest/s/usermangement/search';
      },
      getToken() {
        return window.localStorage.getItem('access_token');
      },
      loadSelectedUser(obj) {
        this.selectedUser = obj
      },
      sendLetter: function () {
        var submitUrl = '/s/email/send'

        if (!(this.selectedUser && this.selectedUser.nationalCode && this.selectedUser.phoneNumber)) {
          var msg = this.$options.filters.error('please.choose.user');
          this.$toasted.error(msg);
          return;
        }

        this.$validator.validateAll().then((result) => {
          if (result) {
            this.submitting = true
            axios.post(submitUrl, {
              subject: this.letter.header,
              message: this.letter.description,
              receiver: this.selectedUser.email
            })
              .then(response => {
                this.submitting = false
                let msg = this.$options.filters.message('mail.submit.success')
                this.$toasted.success(msg)
              })
              .catch(e => {
                this.submitting = false
              })
            return;
          }
          var msg = this.$options.filters.error('common.form.validation.failed')
          this.$toasted.error(msg)
        });
      },
    }
  }
</script>

<style scoped>
  .button-container {
    margin: 20px 0;
  }
  .fill {
    width: 100%;
  }
  textarea {
    min-height: 250px;
  }
</style>
