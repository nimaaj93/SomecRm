<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
        <span class="col-md-2">
        </span>
        <span class="col-md-8">
          <h2>
            <i class="fa fa-paper-plane" aria-hidden="true"></i>
            {{ 'sidebar.menu.signup.requests' | message }}
          </h2>
        </span>
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
                  <form v-on:submit.prevent="searchRequests" role="form" novalidate>
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
                    <div class="table-wrapper" v-if="requests.length > 0">
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
                          <th>{{'common.phoneNumber' | message}}</th>
                          <th>{{'common.email' | message}}</th>
                          <th>{{'common.learn.method' | message}}</th>
                          <th>{{'common.description' | message}}</th>
                          <th>{{'common.request.date' | message}}</th>
                          <th>{{'common.status' | message}}</th>
                          <th>{{'common.actions' | message}}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(request,index) in requests">
                          <td>{{((searchParams.page - 1) * 10) + index + 1}}</td>
                          <td>{{request.firstNameFa}} {{request.lastNameFa}}</td>
                          <td>{{request.phoneNumber}}</td>
                          <td>{{request.email}}</td>
                          <td>{{request.learnMethod}}</td>
                          <td>{{request.description}}</td>
                          <td>{{request.requestDate}}</td>
                          <td>
                            <div v-if="request.status === 201">
                              <span class="label label-success">
                                {{'common.status.new' | message}}
                              </span>
                            </div>
                            <div v-if="request.status === 202">
                              <span class="label label-warning">
                                {{'common.status.inprogress' | message}}
                              </span>
                            </div>
                            <div v-if="request.status === 200">
                              {{'common.status.investigated' | message}}
                            </div>
                          </td>
                          <td>
                            <div class="input-group">
                              <div class="input-group-btn">
                                <a class="btn dropdown-toggle" data-toggle="dropdown">
                                  <i class="fa fa-cog" aria-hidden="true"></i>
                                </a>
                                <ul class="dropdown-menu">
                                  <li><a @click="deleteRequest(request)"><i class="fa fa-times" aria-hidden="true"></i>
                                    {{'common.delete' | message}}</a></li>
                                  <li><a @click="setAsSeen(request)"><i class="fa fa-ticket" aria-hidden="true"></i>
                                    {{'signup.request.set.as.seen' | message}}</a></li>
                                  <li><a @click="setAsDone(request)"><i class="fa fa-ticket" aria-hidden="true"></i>
                                    {{'signup.request.set.as.done' | message}}</a></li>
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
  </div>
</template>

<script>
  import axios from 'axios';
  import Lazy from '@/components/Lazy'
  export default {
    name: 'SignUpRequests',
    components: {
      Lazy
    },
    data () {
      return {
        submitting: false,
        loaded: true,
        pages: 1,
        searchParams: {
          props: {
            phoneNumber: ''
          },
          size: 10,
          page: 1
        },
        requests: []
      }
    },
    created: function () {
      this.loadRequests()
    },
    methods: {
      searchRequests: function() {
        this.searchParams.page = 1
        this.loadRequests()
      },
      loadRequests: function () {
        var countUrl = '/s/signuprequest/count'
        var listUrl = '/s/signuprequest/list'

        this.loaded = false
        this.submitting = true

        axios.get(countUrl, {
          params: {
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
            phoneNumber: this.searchParams.props.phoneNumber
          }
        })
          .then(response => {
            this.requests = response.data
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
        this.loadRequests()
      },
      prevPage: function () {
        this.searchParams.page -= 1
        this.loadRequests()
      },
      setPage: function (pageNumber) {
        this.searchParams.page = pageNumber
        this.loadRequests()
      },
      deleteRequest: function (request) {
        var deleteUrl = '/s/signuprequest/delete'

        var querystring = require('querystring');

        this.loaded = false
        this.submitting = true

        axios.post(deleteUrl, querystring.stringify({
          phoneNumber: request.phoneNumber,
          signUpRequestId: request.signUpRequestId
        }))
          .then(response => {
            this.loaded = true
            this.submitting = false
            this.loadRequests()
          })
          .catch(e => {
            this.loaded = true
            this.submitting = false
            this.loadRequests()
          })
      },
      setAsSeen: function (request) {
        var seenUrl = '/s/signuprequest/seen'

        var querystring = require('querystring');

        this.loaded = false
        this.submitting = true

        axios.post(seenUrl, querystring.stringify({
          phoneNumber: request.phoneNumber,
          signUpRequestId: request.signUpRequestId
        }))
          .then(response => {
            this.loaded = true
            this.submitting = false
            this.loadRequests()
          })
          .catch(e => {
            this.loaded = true
            this.submitting = false
            this.loadRequests()
          })
      },
      setAsDone: function (request) {
        var doneUrl = '/s/signuprequest/done'

        var querystring = require('querystring');

        this.loaded = false
        this.submitting = true

        axios.post(doneUrl, querystring.stringify({
          phoneNumber: request.phoneNumber,
          signUpRequestId: request.signUpRequestId
        }))
          .then(response => {
            this.loaded = true
            this.submitting = false
            this.loadRequests()
          })
          .catch(e => {
            this.loaded = true
            this.submitting = false
            this.loadRequests()
          })
      }
    }
  }
</script>
