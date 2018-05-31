<template>
  <div class="content-wrapper">
      <section class="content-header">
        <div class="row">
          <span class="col-md-2">
          </span>
          <span class="col-md-8">
            <h2>
              <i class="fa fa-users" aria-hidden="true"></i>
              {{ 'sidebar.menu.user.management.list' | message }}
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
                    <form v-on:submit.prevent="searchUsers" role="form" novalidate>
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
                      <div class="table-wrapper" v-if="users.length > 0">
                        <ul class="pagination">
                          <li v-for="n in pages" :class="{active : searchParams.page == n}">
                            <a @click="setPage(n)">{{n}}</a>
                          </li>
                        </ul>
                        <table id="usersDataTable" class="table table-bordered table-hover table-striped">
                          <thead>
                            <tr>
                              <th>{{'common.row' | message}}</th>
                              <th>{{'common.fullName' | message}}</th>
                              <th>{{'common.fullName.en' | message}}</th>
                              <th>{{'common.nationalCode' | message}}</th>
                              <th>{{'common.phoneNumber' | message}}</th>
                              <th>{{'common.email' | message}}</th>
                              <th>{{'common.dateOfBirth' | message}}</th>
                              <th>{{'common.status' | message}}</th>
                              <th>{{'common.expire.date' | message}}</th>
                              <th>{{'common.actions' | message}}</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr v-for="(user,index) in users">
                              <td>{{((searchParams.page - 1) * 10) + index + 1}}</td>
                              <td>{{user.firstNameFa}} {{user.lastNameFa}}</td>
                              <td>{{user.firstNameEn}} {{user.lastNameEn}}</td>
                              <td>{{user.nationalCode}}</td>
                              <td>{{user.phoneNumber}}</td>
                              <td>{{user.email}}</td>
                              <td>{{user.birthDate}}</td>
                              <td>
                                <span class="label label-success" v-if="user.status === 100">
                                  {{'common.active' | message}}
                                </span>
                                <span class="label label-warning" v-if="user.status === 101">
                                  {{'common.inactive' | message}}
                                </span>
                              </td>
                              <td>
                                {{user.expireDate}}
                                <span v-if="user.nearExpireDate === true" style="color: red">
                                  <i class="fa fa-hourglass-end" aria-hidden="true"></i>
                                </span>
                              </td>
                              <td>
                                <div class="input-group">
                                  <div class="input-group-btn">
                                    <a class="btn dropdown-toggle" data-toggle="dropdown">
                                      <i class="fa fa-cog" aria-hidden="true"></i>
                                    </a>
                                    <ul class="dropdown-menu">
                                      <li><a @click="setSelectedUser(user.nationalCode)" data-toggle="modal" data-target="#editUserPopup">
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                        {{'common.edit' | message}}</a>
                                      </li>
                                      <li v-if="user.status === 101"><a @click="enableUser(user.nationalCode)">
                                        <i class="fa fa-ticket" aria-hidden="true"></i>
                                        {{'common.enable' | message}}
                                      </a></li>
                                      <li v-if="user.status === 100"><a @click="disableUser(user.nationalCode)">
                                        <i class="fa fa-ticket" aria-hidden="true"></i>
                                        {{'common.disable' | message}}
                                      </a></li>
                                      <li>
                                        <a @click="loadBalanceForUser(user.nationalCode)" data-toggle="modal" data-target="#balancePopup">
                                          <i class="fa fa-money" aria-hidden="true"></i>
                                          {{'show.balance' | message}}
                                        </a>
                                      </li>
                                    </ul>
                                  </div>
                                </div>
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


    <div id="editUserPopup" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <lazy :loaded="userEditLoaded">
        <div class="modal-content" v-if="selectedUser != null">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">{{'common.edit' | message}}</h4>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class="col-md-6">
                <div class="form-group" :class="{'has-error': errors.has('inputFirstnameFa')}">
                  <label class="control-label"
                         v-show="errors.has('inputFirstnameFa')">{{'common.required' | error}}</label>
                  <label for="inputFirstnameFa"
                         v-show="!errors.has('inputFirstnameFa')">{{'common.firstName' | message}} *</label>
                  <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-user-o" aria-hidden="true"></i>
                            </span>
                    <input type="text" id="inputFirstnameFa" class="form-control"
                           maxlength="50"
                           :disabled="userEditSubmitting"
                           data-vv-delay="1000"
                           name="inputFirstnameFa"
                           v-validate="'required'"
                           :placeholder="'common.firstName' | message" v-focus
                           v-model="selectedUser.firstNameFa">
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group" :class="{'has-error': errors.has('inputLastnameFa')}">
                  <label class="control-label"
                         v-show="errors.first('inputLastnameFa')">{{'common.required' | error}}</label>
                  <label for="inputLastnameFa"
                         v-show="!errors.first('inputLastnameFa')">{{'common.lastName' | message}} *</label>
                  <div class="input-group">
                            <span class="input-group-addon">
                              <i class="fa fa-user-o" aria-hidden="true"></i>
                            </span>
                    <input type="text" id="inputLastnameFa" class="form-control"
                           maxlength="50"
                           :disabled="userEditSubmitting"
                           data-vv-delay="1000"
                           name="inputLastnameFa"
                           v-validate="'required'"
                           :placeholder="'common.lastName' | message"
                           v-model="selectedUser.lastNameFa">
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group" :class="{'has-error': errors.has('inputFirstnameEn')}">
                  <label class="control-label"
                         v-show="errors.has('inputFirstnameEn')">{{'common.required' | error}}</label>
                  <label for="inputFirstnameEn"
                         v-show="!errors.has('inputFirstnameEn')">{{'common.firstName.en' | message}} *</label>
                  <div class="input-group">
                            <span class="input-group-addon">
                              <i class="fa fa-user-o" aria-hidden="true"></i>
                            </span>
                    <input type="text" id="inputFirstnameEn" class="form-control"
                           maxlength="50"
                           :disabled="userEditSubmitting"
                           data-vv-delay="1000"
                           name="inputFirstnameEn"
                           v-validate="'required'"
                           :placeholder="'common.firstName.en' | message"
                           v-model="selectedUser.firstNameEn">
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group" :class="{'has-error': errors.has('inputLastnameEn')}">
                  <label class="control-label"
                         v-show="errors.has('inputLastnameEn')">{{'common.required' | error}}</label>
                  <label for="inputLastnameEn"
                         v-show="!errors.has('inputLastnameEn')">{{'common.lastName.en' | message}} *</label>
                  <div class="input-group">
                            <span class="input-group-addon">
                              <i class="fa fa-user-o" aria-hidden="true"></i>
                            </span>
                    <input type="text" id="inputLastnameEn" class="form-control"
                           maxlength="50"
                           :disabled="userEditSubmitting"
                           data-vv-delay="1000"
                           name="inputLastnameEn"
                           v-validate="'required'"
                           :placeholder="'common.lastName.en' | message"
                           v-model="selectedUser.lastNameEn">
                  </div>
                </div>
              </div>
            </div>
            <div class="form-group" :class="{'has-error': errors.has('inputNationalCode')}">
              <label class="control-label"
                     v-show="errors.has('inputNationalCode')">{{'common.required' | error}}</label>
              <label for="inputNationalCode"
                     v-show="!errors.has('inputNationalCode')">{{'common.nationalCode' | message}}</label>
              <div class="input-group">
                        <span class="input-group-addon">
                          <i class="fa fa-shield" aria-hidden="true"></i>
                        </span>
                <input type="text" id="inputNationalCode" class="form-control"
                       maxlength="10"
                       disabled="true"
                       data-vv-delay="1000"
                       name="inputNationalCode"
                       v-validate="'required'"
                       :placeholder="'common.nationalCode' | message"
                       v-model="selectedUser.nationalCode">
              </div>
            </div>
            <div class="form-group" :class="{'has-error': errors.has('inputPhoneNumber')}">
              <label class="control-label"
                     v-show="errors.has('inputPhoneNumber')">{{'common.required' | error}}</label>
              <label for="inputPhoneNumber"
                     v-show="!errors.has('inputPhoneNumber')">{{'common.phoneNumber' | message}}</label>
              <div class="input-group">
                        <span class="input-group-addon">
                          <i class="fa fa-phone-square" aria-hidden="true"></i>
                        </span>
                <input type="text" id="inputPhoneNumber" class="form-control"
                       maxlength="11"
                       disabled="true"
                       data-vv-delay="1000"
                       v-validate="'required'"
                       name="inputPhoneNumber"
                       :placeholder="'common.phoneNumber' | message"
                       v-model="selectedUser.phoneNumber">
              </div>
            </div>
            <div class="form-group" :class="{'has-error': errors.has('inputEmail')}">
              <label class="control-label" v-show="errors.has('inputEmail')">{{'email.invalid' | error}}</label>
              <label for="inputEmail" v-show="!errors.has('inputEmail')">{{'common.email' | message}}</label>
              <div class="input-group">
                        <span class="input-group-addon">
                          <i class="fa fa-envelope"></i>
                        </span>
                <input type="text" id="inputEmail" class="form-control"
                       maxlength="100"
                       :disabled="userEditSubmitting"
                       name="inputEmail"
                       data-vv-delay="1000"
                       v-validate="'email'"
                       :placeholder="'common.email' | message"
                       v-model="selectedUser.email">
              </div>
            </div>
            <!--<div class="form-group" :class="{'has-error': errors.has('inputDateOfBirth')}">-->
              <!--<label class="control-label"-->
                     <!--v-show="errors.has('inputDateOfBirth')">{{'common.required' | error}}</label>-->
              <!--<label for="inputDateOfBirth"-->
                     <!--v-show="!errors.has('inputDateOfBirth')">{{'common.dateOfBirth' | message}} *</label>-->
              <!--<div class="input-group">-->
                        <!--<span class="input-group-addon">-->
                          <!--<i class="fa fa-calendar" aria-hidden="true"></i>-->
                        <!--</span>-->
                <!--<input type="text" id="inputDateOfBirth" class="form-control"-->
                       <!--v-mask="'####/##/##'"-->
                       <!--maxlength="10"-->
                       <!--:disabled="userEditSubmitting"-->
                       <!--data-vv-delay="1000"-->
                       <!--name="inputDateOfBirth"-->
                       <!--v-validate="'required'"-->
                       <!--v-model="selectedUser.birthDate">-->
              <!--</div>-->
            <!--</div>-->
            <div class="form-group" :class="{'has-error': errors.has('inputDateOfBirth')}">
              <label class="control-label"
                     v-show="errors.has('inputDateOfBirth')">{{'common.required' | error}}</label>
              <label for="inputDateOfBirth"
                     v-show="!errors.has('inputDateOfBirth')">{{'common.dateOfBirth' | message}} *</label>
              <div class="input-group">
                        <span class="input-group-addon">
                          <i class="fa fa-calendar" aria-hidden="true"></i>
                        </span>
                <pdatepicker v-model="selectedUser.birthDate"
                             open-transition-animation=""
                             :placeholder="'common.dateOfBirth' | message"
                             id="inputDateOfBirth"
                             :disabled="userEditSubmitting"
                             name="inputDateOfBirth"
                             v-validate="'required'">
                </pdatepicker>
              </div>
            </div>

            <div class="form-group" :class="{'has-error': errors.has('inputExpireDate')}">
              <label class="control-label"
                     v-show="errors.has('inputExpireDate')">{{'common.required' | error}}</label>
              <label for="inputExpireDate"
                     v-show="!errors.has('inputExpireDate')">{{'common.expire.date' | message}} *</label>
              <div class="input-group">
                        <span class="input-group-addon">
                          <i class="fa fa-calendar" aria-hidden="true"></i>
                        </span>
                <pdatepicker v-model="selectedUser.expireDateStr"
                             open-transition-animation=""
                             :placeholder="'common.expire.date' | message"
                             id="inputExpireDate"
                             :disabled="userEditSubmitting"
                             name="inputExpireDate"
                             v-validate="'required'">
                </pdatepicker>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button :disabled="userEditSubmitting" type="button" class="btn btn-primary float" @click="updateUser">{{'common.update' | message}}</button>
            <button :disabled="userEditSubmitting" type="button" class="btn btn-danger float" data-dismiss="modal">{{'common.cancel' | message}}</button>
          </div>
        </div>
        </lazy>
      </div>
    </div>

    <div id="balancePopup" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <lazy :loaded="balanceLoaded">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">{{'show.balance' | message}}</h4>
            </div>
            <div class="modal-body">

              <div class="row">
                <div class="col-md-12">
                  <strong>
                    {{'common.balance' | message}}
                  </strong>
                  <p class="text-muted" v-if="userBalance != null">
                    {{userBalance.balance}}
                  </p>
                </div>
              </div>

            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-danger float" data-dismiss="modal">{{'common.close' | message}}</button>
            </div>
          </div>
        </lazy>
      </div>
    </div>


    </div>
</template>

<script>
  import Lazy from '../../Lazy';
  import axios from 'axios';

  export default {
    components: {Lazy},
    name: 'UserList',
    data () {
      return {
        submitting: false,
        loaded: true,
        pages: 1,
        searchParams: {
          props: {
            nationalCode: '',
            phoneNumber: ''
          },
          size: 10,
          page: 1
        },
        users: [],
        selectedUser: {},
        userEditSubmitting: false,
        userEditLoaded: true,
        balanceLoaded: true,
        userBalance: {}
      }
    },
    created: function () {
      this.loadUsers()
    },
    methods: {
      searchUsers: function () {
        this.searchParams.page = 1
        this.loadUsers()
      },
      loadUsers: function () {
        var countUrl = '/s/usermangement/count'
        var listUrl = '/s/usermangement/list'

        this.loaded = false
        this.submitting = true

        axios.get(countUrl, {
          params: {
            nationalCode: this.searchParams.props.nationalCode,
            phoneNumber: this.searchParams.props.phoneNumber
          }
        })
          .then(response => {
            this.pages = parseInt(Math.ceil(response.data / this.searchParams.size))
          })
          .catch(e => {
          })

        axios.get(listUrl, {
          params: {
            size: this.searchParams.size,
            page: this.searchParams.page - 1,
            nationalCode: this.searchParams.props.nationalCode,
            phoneNumber: this.searchParams.props.phoneNumber
          }
        })
          .then(response => {
            this.users = response.data
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
        this.loadUsers()
      },
      prevPage: function () {
        this.searchParams.page -= 1
        this.loadUsers()
      },
      setPage: function (pageNumber) {
        this.searchParams.page = pageNumber
        this.loadUsers()
      },
      setSelectedUser: function (nationalCode) {

        var url = '/s/user/userinfo'

        this.userEditLoaded = false

        axios.get(url, {
          params: {
            nationalCode: nationalCode
          }
        })
          .then(response => {
            this.selectedUser = response.data
            this.userEditLoaded = true
          })
          .catch(e => {
            this.selectedUser = null
            this.userEditLoaded = true
          })
      },
      updateUser: function () {

        var updateUrl = '/s/useredit/update'

        this.$validator.validateAll().then((result) => {
          if (result) {
            this.userEditSubmitting = true
            axios.post(updateUrl, {
              firstNameFa: this.selectedUser.firstNameFa,
              lastNameFa: this.selectedUser.lastNameFa,
              firstNameEn: this.selectedUser.firstNameEn,
              lastNameEn: this.selectedUser.lastNameEn,
              nationalCode: this.selectedUser.nationalCode,
              phoneNumber: this.selectedUser.phoneNumber,
              email: this.selectedUser.email,
              birthDate: this.selectedUser.birthDate,
              expireDateStr: this.selectedUser.expireDateStr
            })
              .then(response => {
                this.userEditSubmitting = false
                let msg = this.$options.filters.message('user.update.success')
                this.$toasted.success(msg)
                $('#editUserPopup').modal('hide')
                this.loadUsers()
              })
              .catch(e => {
                this.userEditSubmitting = false
              })
            return;
          }
          this.userEditSubmitting = false
          var msg = this.$options.filters.error('common.form.validation.failed')
          this.$toasted.error(msg)
        });
      },
      enableUser: function (nationalCode) {
        var enableUrl = '/s/usermangement/enable'
        var querystring = require('querystring');
        this.submitting = true
        axios.post(enableUrl, querystring.stringify({
          nationalCode: nationalCode
        }))
          .then(response => {
            this.submitting = false
            let msg = this.$options.filters.message('user.enable.success')
            this.$toasted.success(msg)
            this.loadUsers()
          })
          .catch(e => {
            this.submitting = false
            this.loadUsers()
          })
      },
      disableUser: function (nationalCode) {
        var disableUrl = '/s/usermangement/disable'
        var querystring = require('querystring');
        this.submitting = true
        axios.post(disableUrl, querystring.stringify({
          nationalCode: nationalCode
        }))
          .then(response => {
            this.submitting = false
            let msg = this.$options.filters.message('user.disable.success')
            this.$toasted.success(msg)
            this.loadUsers()
          })
          .catch(e => {
            this.submitting = false
            this.loadUsers()
          })
      },
      loadBalanceForUser(nationalCode) {
          let url = '/s/balance/get';
        this.balanceLoaded = false;
        this.userBalance = {};

        axios.get(url, {
          params: {
            nationalCode: nationalCode
          }
        })
          .then(response => {
            this.userBalance = response.data;
            this.balanceLoaded = true
          })
          .catch(e => {
            this.userBalance = {};
            this.balanceLoaded = true;
          })
      }
    }
  }
</script>
