<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
          <span class="col-md-2">
          </span>
        <span class="col-md-8">
            <h2>
              <i class="fa fa-list" aria-hidden="true"></i>
              {{ 'sidebar.menu.promotions.list' | message }}
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
                  <form v-on:submit.prevent="searchPromotions" role="form" novalidate>
                    <div class="row">
                      <div class="col-md-1">

                      </div>

                      <div class="col-md-5">
                        <div class="form-group">
                          <label for="searchFromDate" class="sr-only">{{'common.fromDate' | message}}</label>
                          <div class="input-group">
                              <span class="input-group-addon">
                                <i class="fa fa-phone-square" aria-hidden="true"></i>
                              </span>
                            <input type="text" id="searchFromDate" class="form-control"
                                   v-focus
                                   autocomplete="off"
                                   maxlength="10"
                                   :disabled="submitting"
                                   name="searchFromDate"
                                   v-mask="'####/##/##'"
                                   :placeholder="'common.fromDate' | message"
                                   v-model="searchParams.props.fromDate">
                          </div>
                        </div>
                      </div>

                      <div class="col-md-5">
                        <div class="form-group">
                          <label for="searchToDate" class="sr-only">{{'common.toDate' | message}}</label>
                          <div class="input-group">
                              <span class="input-group-addon">
                                <i class="fa fa-shield" aria-hidden="true"></i>
                              </span>
                            <input type="text" id="searchToDate" class="form-control"
                                   maxlength="10"
                                   :disabled="submitting"
                                   name="searchToDate"
                                   v-mask="'####/##/##'"
                                   :placeholder="'common.toDate' | message"
                                   v-model="searchParams.props.toDate">
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
                    <div class="table-wrapper" v-if="promotions.length > 0">
                      <ul class="pagination">
                        <li v-for="n in pages" :class="{active : searchParams.page == n}">
                          <a @click="setPage(n)">{{n}}</a>
                        </li>
                      </ul>
                      <table id="promotionsDataTable" class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                          <th>{{'common.row' | message}}</th>
                          <th>{{'promotion.sender' | message}}</th>
                          <th>{{'common.expire.date' | message}}</th>
                          <th>{{'common.create.date' | message}}</th>
                          <th>{{'common.header' | message}}</th>
                          <th>{{'common.description' | message}}</th>
                          <th>{{'common.actions' | message}}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(promotion,index) in promotions">
                          <td>{{((searchParams.page - 1) * 10) + index + 1}}</td>
                          <td>{{promotion.senderFirstNameFa}} {{promotion.senderLastNameFa}}</td>
                          <td>{{promotion.expireDate}}</td>
                          <td>{{promotion.createDate}}</td>
                          <td>{{promotion.promotionHeader}}</td>
                          <td>{{promotion.description | summarize(50)}}</td>
                          <td>
                            <div class="input-group">
                              <div class="input-group-btn">
                                <a class="btn dropdown-toggle" data-toggle="dropdown">
                                  <i class="fa fa-cog" aria-hidden="true"></i>
                                </a>
                                <ul class="dropdown-menu">
                                  <!--<li><a @click="setSelectedUser(user.nationalCode)" data-toggle="modal" data-target="#editUserPopup">-->
                                    <!--<i class="fa fa-pencil-square-o" aria-hidden="true"></i>-->
                                    <!--{{'common.edit' | message}}</a>-->
                                  <!--</li>-->
                                  <li><a @click="deleteOffer(promotion.offerId)">
                                    <i class="fa fa-ticket" aria-hidden="true"></i>
                                    {{'common.delete' | message}}
                                  </a></li>
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
          props: {},
          size: 10,
          page: 1
        },
        promotions: [],
      }
    },
    created: function () {
      this.loadPromotions()
    },
    methods: {
      searchPromotions: function () {
        this.searchParams.page = 1
        this.loadPromotions()
      },
      loadPromotions: function () {
        var countUrl = '/s/useroffer/count'
        var listUrl = '/s/useroffer/list'

        this.loaded = false
        this.submitting = true

        axios.get(countUrl, {
          params: {
            fromDate: this.searchParams.props.fromDate,
            toDate: this.searchParams.props.toDate
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
            fromDate: this.searchParams.props.fromDate,
            toDate: this.searchParams.props.toDate
          }
        })
          .then(response => {
            this.promotions = response.data
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
        this.loadPromotions()
      },
      prevPage: function () {
        this.searchParams.page -= 1
        this.loadPromotions()
      },
      setPage: function (pageNumber) {
        this.searchParams.page = pageNumber
        this.loadPromotions()
      },
      deleteOffer(offerId) {
          let url = 's/useroffer/update'

        axios.post(url, {
            offerId: offerId,
            offerStatus: 601
        }).then((response) => {
          let msg = this.$options.filters.message('offer.update.success');
          this.$toasted.success(msg);
          this.loadPromotions();
        })
          .catch((error)  => {
          });
      }
    }
  }
</script>
