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
                          <label for="searchPhoneNumber" class="sr-only">{{'corporate.customer.name.fa' | message}}</label>
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
                                   :placeholder="'corporate.customer.name.fa' | message"
                                   v-model="searchParams.props.nameFa">
                          </div>
                        </div>
                      </div>

                      <div class="col-md-5">
                        <div class="form-group">
                          <label for="searchNationalCode" class="sr-only">{{'corporate.customer.name.en' | message}}</label>
                          <div class="input-group">
                              <span class="input-group-addon">
                                <i class="fa fa-shield" aria-hidden="true"></i>
                              </span>
                            <input type="text" id="searchNationalCode" class="form-control"
                                   maxlength="10"
                                   :disabled="submitting"
                                   name="searchNationalCode"
                                   :placeholder="'corporate.customer.name.en' | message"
                                   v-model="searchParams.props.nameEn">
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
                          <th>{{'corporate.customer.name.fa' | message}}</th>
                          <th>{{'corporate.customer.name.en' | message}}</th>
                          <th>{{'corporate.customer.id' | message}}</th>
                          <th>{{'corporate.customer.phonenumber' | message}}</th>
                          <th>{{'common.address' | message}}</th>
                          <th>{{'common.status' | message}}</th>
                          <th>{{'common.expire.date' | message}}</th>
                          <th>{{'common.actions' | message}}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(user,index) in users">
                          <td>{{((searchParams.page - 1) * 10) + index + 1}}</td>
                          <td>{{user.nameFa}}</td>
                          <td>{{user.nameEn}}</td>
                          <td>{{user.companyId}}</td>
                          <td>{{user.phoneNumber}}</td>
                          <td>{{user.cityFa + ', ' + user.addressFa | summarize(40)}}</td>
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
                          </td>
                          <td>
                            <a @click="goToDetails(user.corporateUserId)">
                              <i class="fa fa-eye" aria-hidden="true"></i>
                            </a>
                            <!--<div class="input-group">-->
                              <!--<div class="input-group-btn">-->
                                <!--<a class="btn dropdown-toggle" data-toggle="dropdown">-->
                                  <!--<i class="fa fa-cog" aria-hidden="true"></i>-->
                                <!--</a>-->
                                <!--<ul class="dropdown-menu">-->
                                  <!--<li><a @click="setSelectedUser(user.nationalCode)" data-toggle="modal" data-target="#editUserPopup">-->
                                    <!--<i class="fa fa-pencil-square-o" aria-hidden="true"></i>-->
                                    <!--{{'common.edit' | message}}</a>-->
                                  <!--</li>-->
                                  <!--<li v-if="user.status === 101"><a @click="enableUser(user.nationalCode)">-->
                                    <!--<i class="fa fa-ticket" aria-hidden="true"></i>-->
                                    <!--{{'common.enable' | message}}-->
                                  <!--</a></li>-->
                                  <!--<li v-if="user.status === 100"><a @click="disableUser(user.nationalCode)">-->
                                    <!--<i class="fa fa-ticket" aria-hidden="true"></i>-->
                                    <!--{{'common.disable' | message}}-->
                                  <!--</a></li>-->
                                <!--</ul>-->
                              <!--</div>-->
                            <!--</div>-->
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


  </div>
</template>

<script>
  import Lazy from '../../Lazy';
  import axios from 'axios';

  export default {
    components: {Lazy},
    name: 'CorporateCustomersList',
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
        users: []
      }
    },
    created: function () {
      this.loadCorporateCustomers()
    },
    methods: {
      searchUsers: function () {
        this.searchParams.page = 1
        this.loadCorporateCustomers()
      },
      loadCorporateCustomers: function () {
        var countUrl = '/s/corporateuser/count'
        var listUrl = '/s/corporateuser/list'

        this.loaded = false
        this.submitting = true

        axios.get(countUrl, {
          params: {
            nameFa: this.searchParams.props.nameFa,
            nameEn: this.searchParams.props.nameEn
          }
        })
          .then(response => {
            this.pages = parseInt(Math.ceil(response.data / this.searchParams.size))
            console.log('pages' + this.pages)
          })
          .catch(e => {
              console.log('error in count')
          })

        axios.get(listUrl, {
          params: {
            pageSize: this.searchParams.size,
            pageNumber: this.searchParams.page - 1,
            nameFa: this.searchParams.props.nameFa,
            nameEn: this.searchParams.props.nameEn
          }
        })
          .then(response => {
            this.users = response.data
            this.loaded = true
            this.submitting = false
          })
          .catch(e => {
            console.log('error in list')
            this.loaded = true
            this.submitting = false
          })
      },
      nextPage: function () {
        this.searchParams.page += 1
        this.loadCorporateCustomers()
      },
      prevPage: function () {
        this.searchParams.page -= 1
        this.loadCorporateCustomers()
      },
      setPage: function (pageNumber) {
        this.searchParams.page = pageNumber
        this.loadCorporateCustomers()
      },
      goToDetails(corporateId) {
        this.$router.push({path: '/corporatecustomer/details/' + corporateId});
      }
    }
  }
</script>
