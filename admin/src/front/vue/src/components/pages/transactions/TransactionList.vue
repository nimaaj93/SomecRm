<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
          <span class="col-md-2">
          </span>
        <span class="col-md-8">
            <h2>
              <i class="fa fa-list" aria-hidden="true"></i>
                  {{ 'sidebar.menu.transactions.list' | message }}
              <!--<small>Preview</small>-->
            </h2>
          </span>
        <!--<ol class="breadcrumb">-->
        <!--<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>-->
        <!--<li><a href="#">Forms</a></li>-->
        <!--<li class="active">General Elements</li>-->
        <!--</ol>-->
      </div>
    </section>
    <section class="content">
      <div class="row">
        <div class="col-md-1">
        </div>
        <div class="col-md-10">
          <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">
                    {{'common.search' | message}}
                    <i class="fa fa-search" aria-hidden="true"></i>
                  </h3>
                </div>
                <div class="box-body" :class="{'form-submit' : submitting}">
                  <form v-on:submit.prevent="searchTransactions" role="form" novalidate>
                    <div class="row">
                      <div class="col-md-1">

                      </div>

                      <div class="col-md-5">
                        <div class="form-group">
                          <label for="searchPhoneNumber" class="sr-only">{{'common.phoneNumber' | message}}</label>
                          <div class="input-group">
                              <span class="input-group-addon">
                                <i class="fa fa-phone-square" aria-hidden="true"></i>
                              </span>
                            <input type="text" id="searchPhoneNumber" class="form-control"
                                   v-focus
                                   autocomplete="off"
                                   maxlength="11"
                                   :disabled="submitting"
                                   name="searchPhoneNumber"
                                   :placeholder="'common.phoneNumber' | message"
                                   v-model="searchParams.props.phoneNumber">
                          </div>
                        </div>
                      </div>

                      <div class="col-md-5">
                        <div class="form-group">
                          <label for="searchNationalCode" class="sr-only">{{'common.nationalCode' | message}}</label>
                          <div class="input-group">
                              <span class="input-group-addon">
                                <i class="fa fa-shield" aria-hidden="true"></i>
                              </span>
                            <input type="text" id="searchNationalCode" class="form-control"
                                   maxlength="10"
                                   :disabled="submitting"
                                   name="searchNationalCode"
                                   :placeholder="'common.nationalCode' | message"
                                   v-model="searchParams.props.nationalCode">
                          </div>
                        </div>
                      </div>

                    </div>
                    <div class="row">
                      <div class="col-md-1">

                      </div>
                      <div class="col-md-5">
                        <button class="btn btn-primary"
                                :disabled="submitting"
                                type="submit">
                          {{'common.search' | message}}
                        </button>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <lazy :loaded="loaded">
              <div class="col-xs-12">
                <div class="box">
                  <div class="box-body">
                    <div class="table-wrapper" v-if="transactions.length > 0">
                      <ul class="pagination">
                        <li v-for="n in pages" :class="{active : searchParams.page == n}">
                          <a @click="setPage(n)">{{n}}</a>
                        </li>
                      </ul>
                      <table id="usersDataTable" class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                          <th>{{'common.row' | message}}</th>
                          <th>{{'transaction.user.full.name.fa' | message}}</th>
                          <th>{{'transaction.user.full.name.en' | message}}</th>
                          <!--<th>{{'common.nationalCode' | message}}</th>-->
                          <!--<th>{{'common.phoneNumber' | message}}</th>-->
                          <th>{{'transaction.credit' | message}} / {{'transaction.debit' | message}}</th>
                          <th>{{'common.amount' | message}}</th>
                          <th>{{'common.balance' | message}}</th>
                          <th>{{'transaction.create.date' | message}}</th>
                          <th>{{'transaction.due.date' | message}}</th>
                          <th>{{'transaction.number' | message}}</th>
                          <th>{{'transaction.followup.number' | message}}</th>
                          <th>{{'common.status' | message}}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(transaction,index) in transactions">
                          <td>{{((searchParams.page - 1) * searchParams.size) + index + 1}}</td>
                          <td>{{transaction.userPersonalDto.firstNameFa}} {{transaction.userPersonalDto.lastNameFa}}</td>
                          <td>{{transaction.userPersonalDto.firstNameEn}} {{transaction.userPersonalDto.lastNameEn}}</td>
                          <!--<td>{{transaction.userPersonalDto.nationalCode}}</td>-->
                          <!--<td>{{transaction.userPersonalDto.phoneNumber}}</td>-->
                          <td>
                            <span v-if="transaction.debit === true">
                              <i class="fa fa-level-up" aria-hidden="true"></i>
                              {{'transaction.debit' | message}}
                            </span>
                            <span v-else>
                              <i class="fa fa-level-down" aria-hidden="true"></i>
                              {{'transaction.credit' | message}}
                            </span>
                          </td>
                          <td>{{transaction.amount | amount}}</td>
                          <td>{{transaction.lastBalance | amount}}</td>
                          <td>{{transaction.createDate}}</td>
                          <td>{{transaction.transactionDate}}</td>
                          <td>{{transaction.transactionNumber}}</td>
                          <td>{{transaction.transactionFollowupNumber}}</td>
                          <td>
                                <span class="label label-success" v-if="transaction.transactionStatus === 900">
                                  {{'transaction.status.success' | message}}
                                </span>
                                <span class="label label-warning" v-if="transaction.transactionStatus === 901">
                                  {{'transaction.status.failed' | message}}
                                </span>
                          </td>
                        </tr>
                        </tbody>
                      </table>
                      <ul class="pagination">
                        <li v-for="n in pages" :class="{active : searchParams.page == n}">
                          <a @click="setPage(n)">{{n}}</a>
                        </li>
                      </ul>
                    </div>
                    <div v-else>
                      {{'table.no.items.found' | message}}
                    </div>
                  </div>
                </div>
              </div>
            </lazy>
          </div>
        </div>
        <div class="col-md-1">
        </div>
      </div>
    </section>


    <!--<div id="transactionDetailsPopup" class="modal fade" role="dialog">-->
      <!--<div class="modal-dialog">-->
        <!--<lazy>-->
          <!--<div class="modal-content" v-if="selectedUser != null">-->
            <!--<div class="modal-header">-->
              <!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
              <!--<h4 class="modal-title">{{'common.edit' | message}}</h4>-->
            <!--</div>-->
            <!--<div class="modal-body">-->
              <!--<div class="row">-->
                <!--<div class="col-md-6">-->
                  <!--<div class="form-group" :class="{'has-error': errors.has('inputFirstnameFa')}">-->
                    <!--<label class="control-label"-->
                           <!--v-show="errors.has('inputFirstnameFa')">{{'common.required' | error}}</label>-->
                    <!--<label for="inputFirstnameFa"-->
                           <!--v-show="!errors.has('inputFirstnameFa')">{{'common.firstName' | message}} *</label>-->
                    <!--<div class="input-group">-->
                            <!--<span class="input-group-addon">-->
                                <!--<i class="fa fa-user-o" aria-hidden="true"></i>-->
                            <!--</span>-->
                      <!--<input type="text" id="inputFirstnameFa" class="form-control"-->
                             <!--maxlength="50"-->
                             <!--:disabled="userEditSubmitting"-->
                             <!--data-vv-delay="1000"-->
                             <!--name="inputFirstnameFa"-->
                             <!--v-validate="'required'"-->
                             <!--:placeholder="'common.firstName' | message" v-focus-->
                             <!--v-model="selectedUser.firstNameFa">-->
                    <!--</div>-->
                  <!--</div>-->
                <!--</div>-->
                <!--<div class="col-md-6">-->
                  <!--<div class="form-group" :class="{'has-error': errors.has('inputLastnameFa')}">-->
                    <!--<label class="control-label"-->
                           <!--v-show="errors.first('inputLastnameFa')">{{'common.required' | error}}</label>-->
                    <!--<label for="inputLastnameFa"-->
                           <!--v-show="!errors.first('inputLastnameFa')">{{'common.lastName' | message}} *</label>-->
                    <!--<div class="input-group">-->
                            <!--<span class="input-group-addon">-->
                              <!--<i class="fa fa-user-o" aria-hidden="true"></i>-->
                            <!--</span>-->
                      <!--<input type="text" id="inputLastnameFa" class="form-control"-->
                             <!--maxlength="50"-->
                             <!--:disabled="userEditSubmitting"-->
                             <!--data-vv-delay="1000"-->
                             <!--name="inputLastnameFa"-->
                             <!--v-validate="'required'"-->
                             <!--:placeholder="'common.lastName' | message"-->
                             <!--v-model="selectedUser.lastNameFa">-->
                    <!--</div>-->
                  <!--</div>-->
                <!--</div>-->
              <!--</div>-->
              <!--<div class="row">-->
                <!--<div class="col-md-6">-->
                  <!--<div class="form-group" :class="{'has-error': errors.has('inputFirstnameEn')}">-->
                    <!--<label class="control-label"-->
                           <!--v-show="errors.has('inputFirstnameEn')">{{'common.required' | error}}</label>-->
                    <!--<label for="inputFirstnameEn"-->
                           <!--v-show="!errors.has('inputFirstnameEn')">{{'common.firstName.en' | message}} *</label>-->
                    <!--<div class="input-group">-->
                            <!--<span class="input-group-addon">-->
                              <!--<i class="fa fa-user-o" aria-hidden="true"></i>-->
                            <!--</span>-->
                      <!--<input type="text" id="inputFirstnameEn" class="form-control"-->
                             <!--maxlength="50"-->
                             <!--:disabled="userEditSubmitting"-->
                             <!--data-vv-delay="1000"-->
                             <!--name="inputFirstnameEn"-->
                             <!--v-validate="'required'"-->
                             <!--:placeholder="'common.firstName.en' | message"-->
                             <!--v-model="selectedUser.firstNameEn">-->
                    <!--</div>-->
                  <!--</div>-->
                <!--</div>-->
                <!--<div class="col-md-6">-->
                  <!--<div class="form-group" :class="{'has-error': errors.has('inputLastnameEn')}">-->
                    <!--<label class="control-label"-->
                           <!--v-show="errors.has('inputLastnameEn')">{{'common.required' | error}}</label>-->
                    <!--<label for="inputLastnameEn"-->
                           <!--v-show="!errors.has('inputLastnameEn')">{{'common.lastName.en' | message}} *</label>-->
                    <!--<div class="input-group">-->
                            <!--<span class="input-group-addon">-->
                              <!--<i class="fa fa-user-o" aria-hidden="true"></i>-->
                            <!--</span>-->
                      <!--<input type="text" id="inputLastnameEn" class="form-control"-->
                             <!--maxlength="50"-->
                             <!--:disabled="userEditSubmitting"-->
                             <!--data-vv-delay="1000"-->
                             <!--name="inputLastnameEn"-->
                             <!--v-validate="'required'"-->
                             <!--:placeholder="'common.lastName.en' | message"-->
                             <!--v-model="selectedUser.lastNameEn">-->
                    <!--</div>-->
                  <!--</div>-->
                <!--</div>-->
              <!--</div>-->
              <!--<div class="form-group" :class="{'has-error': errors.has('inputNationalCode')}">-->
                <!--<label class="control-label"-->
                       <!--v-show="errors.has('inputNationalCode')">{{'common.required' | error}}</label>-->
                <!--<label for="inputNationalCode"-->
                       <!--v-show="!errors.has('inputNationalCode')">{{'common.nationalCode' | message}}</label>-->
                <!--<div class="input-group">-->
                        <!--<span class="input-group-addon">-->
                          <!--<i class="fa fa-shield" aria-hidden="true"></i>-->
                        <!--</span>-->
                  <!--<input type="text" id="inputNationalCode" class="form-control"-->
                         <!--maxlength="10"-->
                         <!--disabled="true"-->
                         <!--data-vv-delay="1000"-->
                         <!--name="inputNationalCode"-->
                         <!--v-validate="'required'"-->
                         <!--:placeholder="'common.nationalCode' | message"-->
                         <!--v-model="selectedUser.nationalCode">-->
                <!--</div>-->
              <!--</div>-->
              <!--<div class="form-group" :class="{'has-error': errors.has('inputPhoneNumber')}">-->
                <!--<label class="control-label"-->
                       <!--v-show="errors.has('inputPhoneNumber')">{{'common.required' | error}}</label>-->
                <!--<label for="inputPhoneNumber"-->
                       <!--v-show="!errors.has('inputPhoneNumber')">{{'common.phoneNumber' | message}}</label>-->
                <!--<div class="input-group">-->
                        <!--<span class="input-group-addon">-->
                          <!--<i class="fa fa-phone-square" aria-hidden="true"></i>-->
                        <!--</span>-->
                  <!--<input type="text" id="inputPhoneNumber" class="form-control"-->
                         <!--maxlength="11"-->
                         <!--disabled="true"-->
                         <!--data-vv-delay="1000"-->
                         <!--v-validate="'required'"-->
                         <!--name="inputPhoneNumber"-->
                         <!--:placeholder="'common.phoneNumber' | message"-->
                         <!--v-model="selectedUser.phoneNumber">-->
                <!--</div>-->
              <!--</div>-->
              <!--<div class="form-group" :class="{'has-error': errors.has('inputEmail')}">-->
                <!--<label class="control-label" v-show="errors.has('inputEmail')">{{'email.invalid' | error}}</label>-->
                <!--<label for="inputEmail" v-show="!errors.has('inputEmail')">{{'common.email' | message}}</label>-->
                <!--<div class="input-group">-->
                        <!--<span class="input-group-addon">-->
                          <!--<i class="fa fa-envelope"></i>-->
                        <!--</span>-->
                  <!--<input type="text" id="inputEmail" class="form-control"-->
                         <!--maxlength="100"-->
                         <!--:disabled="userEditSubmitting"-->
                         <!--name="inputEmail"-->
                         <!--data-vv-delay="1000"-->
                         <!--v-validate="'email'"-->
                         <!--:placeholder="'common.email' | message"-->
                         <!--v-model="selectedUser.email">-->
                <!--</div>-->
              <!--</div>-->
              <!--&lt;!&ndash;<div class="form-group" :class="{'has-error': errors.has('inputDateOfBirth')}">&ndash;&gt;-->
              <!--&lt;!&ndash;<label class="control-label"&ndash;&gt;-->
              <!--&lt;!&ndash;v-show="errors.has('inputDateOfBirth')">{{'common.required' | error}}</label>&ndash;&gt;-->
              <!--&lt;!&ndash;<label for="inputDateOfBirth"&ndash;&gt;-->
              <!--&lt;!&ndash;v-show="!errors.has('inputDateOfBirth')">{{'common.dateOfBirth' | message}} *</label>&ndash;&gt;-->
              <!--&lt;!&ndash;<div class="input-group">&ndash;&gt;-->
              <!--&lt;!&ndash;<span class="input-group-addon">&ndash;&gt;-->
              <!--&lt;!&ndash;<i class="fa fa-calendar" aria-hidden="true"></i>&ndash;&gt;-->
              <!--&lt;!&ndash;</span>&ndash;&gt;-->
              <!--&lt;!&ndash;<input type="text" id="inputDateOfBirth" class="form-control"&ndash;&gt;-->
              <!--&lt;!&ndash;v-mask="'####/##/##'"&ndash;&gt;-->
              <!--&lt;!&ndash;maxlength="10"&ndash;&gt;-->
              <!--&lt;!&ndash;:disabled="userEditSubmitting"&ndash;&gt;-->
              <!--&lt;!&ndash;data-vv-delay="1000"&ndash;&gt;-->
              <!--&lt;!&ndash;name="inputDateOfBirth"&ndash;&gt;-->
              <!--&lt;!&ndash;v-validate="'required'"&ndash;&gt;-->
              <!--&lt;!&ndash;v-model="selectedUser.birthDate">&ndash;&gt;-->
              <!--&lt;!&ndash;</div>&ndash;&gt;-->
              <!--&lt;!&ndash;</div>&ndash;&gt;-->
              <!--<div class="form-group" :class="{'has-error': errors.has('inputDateOfBirth')}">-->
                <!--<label class="control-label"-->
                       <!--v-show="errors.has('inputDateOfBirth')">{{'common.required' | error}}</label>-->
                <!--<label for="inputDateOfBirth"-->
                       <!--v-show="!errors.has('inputDateOfBirth')">{{'common.dateOfBirth' | message}} *</label>-->
                <!--<div class="input-group">-->
                        <!--<span class="input-group-addon">-->
                          <!--<i class="fa fa-calendar" aria-hidden="true"></i>-->
                        <!--</span>-->
                  <!--<pdatepicker v-model="selectedUser.birthDate"-->
                               <!--open-transition-animation=""-->
                               <!--:placeholder="'common.dateOfBirth' | message"-->
                               <!--id="inputDateOfBirth"-->
                               <!--:disabled="userEditSubmitting"-->
                               <!--name="inputDateOfBirth"-->
                               <!--v-validate="'required'">-->
                  <!--</pdatepicker>-->
                <!--</div>-->
              <!--</div>-->

              <!--<div class="form-group" :class="{'has-error': errors.has('inputExpireDate')}">-->
                <!--<label class="control-label"-->
                       <!--v-show="errors.has('inputExpireDate')">{{'common.required' | error}}</label>-->
                <!--<label for="inputExpireDate"-->
                       <!--v-show="!errors.has('inputExpireDate')">{{'common.expire.date' | message}} *</label>-->
                <!--<div class="input-group">-->
                        <!--<span class="input-group-addon">-->
                          <!--<i class="fa fa-calendar" aria-hidden="true"></i>-->
                        <!--</span>-->
                  <!--<pdatepicker v-model="selectedUser.expireDateStr"-->
                               <!--open-transition-animation=""-->
                               <!--:placeholder="'common.expire.date' | message"-->
                               <!--id="inputExpireDate"-->
                               <!--:disabled="userEditSubmitting"-->
                               <!--name="inputExpireDate"-->
                               <!--v-validate="'required'">-->
                  <!--</pdatepicker>-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
            <!--<div class="modal-footer">-->
              <!--<button :disabled="userEditSubmitting" type="button" class="btn btn-primary float" @click="updateUser">{{'common.update' | message}}</button>-->
              <!--<button :disabled="userEditSubmitting" type="button" class="btn btn-danger float" data-dismiss="modal">{{'common.cancel' | message}}</button>-->
            <!--</div>-->
          <!--</div>-->
        <!--</lazy>-->
      <!--</div>-->
    <!--</div>-->

  </div>
</template>

<script>
  import Lazy from '../../Lazy';
  import axios from 'axios';

  export default {
    components: {Lazy},
    name: 'TransactionList',
    data () {
      return {
        submitting: false,
        loaded: true,
        pages: 1,
        searchParams: {
          props: {},
          size: 10,
          page: 1
        },
        transactions: [],
        selectedTransaction: {}
      }
    },
    created: function () {
      this.searchTransactions()
    },
    methods: {
      searchTransactions: function () {
        this.searchParams.page = 1
        this.loadTransactions();
      },
      loadTransactions: function () {
        var countUrl = '/s/transactions/count'
        var listUrl = '/s/transactions/list'

        this.loaded = false
        this.submitting = true

        axios.get(countUrl, {
          params: {
            nationalCode: this.searchParams.props.nationalCode
          }
        })
          .then(response => {
            this.pages = parseInt(Math.ceil(response.data / this.searchParams.size))
          })
          .catch(e => {
          })

        axios.get(listUrl, {
          params: {
            pageSize: this.searchParams.size,
            pageNumber: this.searchParams.page - 1,
            nationalCode: this.searchParams.props.nationalCode
          }
        })
          .then(response => {
            this.transactions = response.data
            this.loaded = true
            this.submitting = false
          })
          .catch(e => {
            this.loaded = true
            this.submitting = false
          })
      },
      nextPage: function () {
        this.searchParams.page += 1
        this.loadTransactions()
      },
      prevPage: function () {
        this.searchParams.page -= 1
        this.loadTransactions()
      },
      setPage: function (pageNumber) {
        this.searchParams.page = pageNumber
        this.loadTransactions()
      }
    }
  }
</script>
