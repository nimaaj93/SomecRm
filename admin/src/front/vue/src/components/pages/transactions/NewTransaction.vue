<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
        <span class="col-md-2">
        </span>
        <span class="col-md-8">
          <h2>
            <i class="fa fa-money" aria-hidden="true"></i>
            {{ 'sidebar.menu.transactions.submit' | message }}
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
            <form>
              <div class="col-xs-12">
                <div class="box">
                  <div class="box-header">
                    <div class="col-md-1">
                    </div>
                    <div class="col-md-10">
                      <h3 class="box-title">
                        {{'common.search' | message}}
                        <i class="fa fa-search" aria-hidden="true"></i>
                      </h3>
                    </div>
                  </div>
                  <div class="box-body">
                    <div class="col-md-1">
                    </div>
                    <div class="col-md-10">
                      <div class="row">
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
                    </div>
                    <div class="col-md-1">
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="col-md-1">
          </div>
        </div>

        <div class="row">
          <div class="col-md-1">
          </div>
          <div class="col-md-10">
            <form>
              <div class="col-xs-12">
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
              </div>
            </form>
          </div>
          <div class="col-md-1">
          </div>
        </div>

        <div class="row">
          <div class="col-md-1">
          </div>
          <div class="col-md-10">
            <div class="row" :class="{'form-submit' : submitting}">
              <form v-on:submit.prevent="submitTransaction" role="form" novalidate>
                <div class="col-xs-12">
                  <div class="box">
                    <div class="box-body row">
                      <div class="col-md-1">
                      </div>
                      <div class="col-md-10">

                        <div class="row">
                          <div class="form-group" :class="{'has-error': errors.has('transactionDate')}">
                            <label class="control-label"
                                   v-show="errors.has('transactionDate')">{{'common.required' | error}}</label>
                            <label for="transactionDate"
                                   v-show="!errors.has('transactionDate')">{{'transaction.due.date' | message}} *</label>
                            <div class="input-group">
                            <span class="input-group-addon">
                              <i class="fa fa-calendar" aria-hidden="true"></i>
                            </span>
                              <pdatepicker v-model="transaction.transactionDate"
                                           open-transition-animation=""
                                           :placeholder="'transaction.due.date' | message"
                                           id="transactionDate"
                                           :disabled="submitting"
                                           name="transactionDate"
                                           v-validate="'required'">
                              </pdatepicker>
                            </div>
                          </div>
                        </div>

                        <div class="row">
                          <div class="form-group" :class="{'has-error': errors.has('amount')}">
                            <label class="control-label"
                                   v-show="errors.has('amount')">{{'common.required' | error}}</label>
                            <label for="amount"
                                   v-show="!errors.has('amount')">{{'common.amount' | message}} *</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                  <i class="fa fa-phone-square" aria-hidden="true"></i>
                                </span>
                              <input type="text" id="amount" class="form-control"
                                     autocomplete="off"
                                     maxlength="20"
                                     :disabled="submitting"
                                     name="amount"
                                     v-validate="'required'"
                                     :placeholder="'common.amount' | message"
                                     v-model="transaction.amount">
                            </div>
                          </div>
                        </div>

                        <div class="row">
                          <div class="form-group" :class="{'has-error': errors.has('transactionNumber')}">
                            <label class="control-label"
                                   v-show="errors.has('transactionNumber')">{{'common.required' | error}}</label>
                            <label for="transactionNumber"
                                   v-show="!errors.has('transactionNumber')">{{'transaction.number' | message}} *</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                  <i class="fa fa-phone-square" aria-hidden="true"></i>
                                </span>
                              <input type="text" id="transactionNumber" class="form-control"
                                     autocomplete="off"
                                     maxlength="30"
                                     :disabled="submitting"
                                     name="transactionNumber"
                                     v-validate="'required'"
                                     :placeholder="'transaction.number' | message"
                                     v-model="transaction.transactionNumber">
                            </div>
                          </div>
                        </div>

                        <div class="row">
                          <div class="form-group" :class="{'has-error': errors.has('transactionFollowupNumber')}">
                            <label class="control-label"
                                   v-show="errors.has('transactionFollowupNumber')">{{'common.required' | error}}</label>
                            <label for="transactionFollowupNumber"
                                   v-show="!errors.has('transactionFollowupNumber')">{{'transaction.followup.number' | message}} *</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                  <i class="fa fa-phone-square" aria-hidden="true"></i>
                                </span>
                              <input type="text" id="transactionFollowupNumber" class="form-control"
                                     autocomplete="off"
                                     maxlength="30"
                                     :disabled="submitting"
                                     name="transactionFollowupNumber"
                                     v-validate="'required'"
                                     :placeholder="'transaction.followup.number' | message"
                                     v-model="transaction.transactionFollowupNumber">
                            </div>
                          </div>
                        </div>

                        <div class="row">
                          <div class="col-md-12 button-container">
                            <button class="btn btn-primary pull-right"
                                    :disabled="submitting"
                                    type="submit">
                              {{'common.submit' | message}}
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
    name: 'NewTransaction',
    components: {
      Lazy,Autocomplete
    },
    data () {
      return {
        submitting: false,
        selectedUser: {},
        transaction: {}
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
      submitTransaction: function () {
        var submitUrl = '/s/transactions/create';

        if (!(this.selectedUser && this.selectedUser.userId)) {
          var msg = this.$options.filters.error('please.choose.user');
          this.$toasted.error(msg);
          return;
        }

        this.$validator.validateAll().then((result) => {
          if (result) {
            this.submitting = true
            axios.post(submitUrl, {
              userId: this.selectedUser.userId,
              amount: this.transaction.amount,
              transactionDate: this.transaction.transactionDate,
              transactionNumber: this.transaction.transactionNumber,
              transactionFollowupNumber: this.transaction.transactionFollowupNumber
            })
              .then(response => {
                this.submitting = false
                let msg = this.$options.filters.message('transaction.submit.success')
                this.$toasted.success(msg)
                this.$router.push({path: '/transactions/list'});
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
