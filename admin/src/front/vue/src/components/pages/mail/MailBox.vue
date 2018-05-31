<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
        <span class="col-md-2">
        </span>
        <span class="col-md-8">
          <h2>
            <i class="fa fa-envelope-open-o" aria-hidden="true"></i>
            {{ 'mailbox.header' | message }}
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
                <div class="box-body row" :class="{'form-submit' : submitting}">
                  <div class="col-md-1">
                  </div>
                  <div class="col-md-10">
                    <form v-on:submit.prevent="searchMailBox" role="form" novalidate>
                      <div class="row">
                        <div class="col-md-6">
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

                        <div class="col-md-6">
                          <div class="form-group">
                            <label for="searchNationalCode" class="sr-only">{{'common.nationalCode' | message}}</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                  <i class="fa fa-phone-square" aria-hidden="true"></i>
                                </span>
                              <input type="text" id="searchNationalCode" class="form-control"
                                     autocomplete="off"
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
                        <div class="col-md-6">
                          <button class="btn btn-primary"
                                  :disabled="submitting"
                                  type="submit">
                            {{'common.search' | message}}
                          </button>
                        </div>
                      </div>
                    </form>
                  </div>
                  <div class="col-md-1">
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row button-container">
            <div class="col-md-6">
              <router-link tag="button" to="/newmail"
                           class="btn btn-primary"
                            :disabled="submitting">
                {{'new.mail' | message}}
              </router-link>
            </div>
          </div>

          <div class="row">
            <lazy :loaded="loaded">
              <div class="col-xs-12">
                <div class="box">
                  <div class="box-body">

                    <!--<ul class="nav nav-tabs nav-justified">-->
                      <!--<li :class="{active : searchParams.props.orderStatus === 400}"><a href="#" @click="setActiveTab(400)">{{'order.status.new' | message}}</a></li>-->
                      <!--<li :class="{active : searchParams.props.orderStatus === 401}"><a href="#" @click="setActiveTab(401)">{{'order.status.inprogress' | message}}</a></li>-->
                      <!--<li :class="{active : searchParams.props.orderStatus === 402}"><a href="#" @click="setActiveTab(402)">{{'order.status.waiting.for.payment' | message}}</a></li>-->
                      <!--<li :class="{active : searchParams.props.orderStatus === 403}"><a href="#" @click="setActiveTab(403)">{{'order.status.paid' | message}}</a></li>-->
                      <!--<li :class="{active : searchParams.props.orderStatus === 404}"><a href="#" @click="setActiveTab(404)">{{'order.status.cancel' | message}}</a></li>-->
                    <!--</ul>-->

                    <div class="table-wrapper" v-if="mails.length > 0">
                      <ul class="pagination">
                        <li v-for="n in pages" :class="{active : searchParams.page == n}">
                          <a @click="setPage(n)">{{n}}</a>
                        </li>
                      </ul>
                      <table id="mailboxDataTable" class="table table-bordered table-hover table-striped">
                        <thead>
                          <tr>
                            <th>{{'common.row' | message}}</th>
                            <th>{{'mail.sender.fullName' | message}}</th>
                            <th>{{'mail.receiver.fullName' | message}}</th>
                            <th>{{'mail.sendDate' | message}}</th>
                            <th>{{'mail.header' | message}}</th>
                            <th>{{'mail.content' | message}}</th>
                            <th>{{'common.actions' | message}}</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr v-for="(mail,index) in mails">
                            <td>{{((searchParams.page - 1) * 10) + index + 1}}</td>
                            <td>{{mail.senderFirstNameFa}} {{mail.senderLastNameFa}}</td>
                            <td>{{mail.receiverFirstNameFa}} {{mail.receiverLastNameFa}}</td>
                            <td>{{mail.sendDate}}</td>
                            <td>{{mail.messageHeader}}</td>
                            <td>{{mail.description | summarize(40)}}</td>
                            <td>
                              <div class="input-group">
                                <div class="input-group-btn">
                                  <a class="btn dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-cog" aria-hidden="true"></i>
                                  </a>
                                  <ul class="dropdown-menu">
                                    <li>
                                      <a @click="setSelectedMail(mail)" data-toggle="modal" data-target="#mailViewPopup">
                                        <i class="fa fa-eye" aria-hidden="true"></i>
                                        {{'common.view.detail' | message}}
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

    <!-- letter view popup -->
    <div id="mailViewPopup" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <lazy :loaded="true">
          <div class="modal-content" v-if="selectedMail != null">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">{{'common.view.detail' | message}}
              </h4>
            </div>
            <div class="modal-body">
              <div class="row">

                <div class="col-md-6 col-xs-12">
                  <strong>
                    {{'mail.sender.fullName' | message}}
                  </strong>
                  <div class="text-muted">
                    <p>
                      {{selectedMail.senderFirstNameFa}} {{selectedMail.senderLastNameFa}}
                    </p>
                  </div>
                </div>

                <div class="col-md-6 col-xs-12">
                  <strong>
                    {{'mail.receiver.fullName' | message}}
                  </strong>
                  <div class="text-muted">
                    <p>
                      {{selectedMail.receiverFirstNameFa}} {{selectedMail.receiverLastNameFa}}
                    </p>
                  </div>
                </div>

              </div>

              <div class="row">

                <div class="col-md-6 col-xs-12">
                  <strong>
                    {{'mail.sendDate' | message}}
                  </strong>
                  <div class="text-muted">
                    <p>
                      {{selectedMail.sendDate}}
                    </p>
                  </div>
                </div>

              </div>

              <div class="row">

                <div class="col-md-6 col-xs-12">
                  <strong>
                    {{'mail.header' | message}}
                  </strong>
                  <div class="text-muted">
                    <p>
                      {{selectedMail.messageHeader}}
                    </p>
                  </div>
                </div>

              </div>

              <div class="row">

                <div class="col-md-12 col-xs-12">
                  <strong>
                    {{'mail.content' | message}}
                  </strong>
                  <div class="text-muted">
                    <div class="preserve-breaks">
                      {{selectedMail.description}}
                    </div>
                  </div>
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
  import axios from 'axios';
  import Lazy from '../../Lazy';

  export default {
    name: 'MailBox',
    components: {
      Lazy
    },
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
        mails: [],
        selectedMail: null
      }
    },
    created: function () {
      this.loadMails()
    },
    methods: {
      searchMailBox: function() {
        this.searchParams.page = 1
        this.loadMails()
      },
      loadMails: function () {
        var countUrl = '/s/usermessage/count'
        var listUrl = '/s/usermessage/list'

        this.loaded = false
        this.submitting = true

        axios.get(countUrl, {
          params: {
            phoneNumber: this.searchParams.props.phoneNumber,
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
            phoneNumber: this.searchParams.props.phoneNumber,
            nationalCode: this.searchParams.props.nationalCode,
            size: this.searchParams.size,
            page: this.searchParams.page - 1
          }
        })
          .then(response => {
            this.mails = response.data
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
        this.loadMails()
      },
      prevPage: function () {
        this.searchParams.page -= 1
        this.loadMails()
      },
      setPage: function (pageNumber) {
        this.searchParams.page = pageNumber
        this.loadMails()
      },
      setSelectedMail: function (mail) {
        this.selectedMail = JSON.parse(JSON.stringify(mail))
      }
    }
  }
</script>

<style scoped>
  .button-container {
    margin: 20px 0;
  }
</style>
