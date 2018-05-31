<template>
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        {{'home.header' | message}}
      </h1>
      <!--<ol class="breadcrumb">-->
        <!--<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>-->
        <!--<li class="active">Here</li>-->
      <!--</ol>-->
    </section>

    <section class="content">
      <div class="row">
        <div class="col-xs-1">
        </div>
        <div class="col-xs-10">
      <lazy :loaded="loaded">
        <div class="row">
          <div class="col-lg-3 col-xs-6">
            <!-- small box -->
            <div class="small-box bg-aqua">
              <div class="inner">
                <h3>{{summary.newOrdersCount}}</h3>
                <p>{{'home.summary.new.orders' | message}}</p>
              </div>
              <div class="icon">
                <i class="ion ion-bag"></i>
              </div>
              <router-link to="/orders" class="small-box-footer">
                {{'common.more.info' | message}}
                <i class="fa fa-arrow-circle-left"></i>
              </router-link>
            </div>
          </div><!-- ./col -->
          <div class="col-lg-3 col-xs-6">
            <!-- small box -->
            <div class="small-box bg-green">
              <div class="inner">
                <h3>{{summary.newRegistrationRequestsCount}}</h3>
                <p>{{'home.summary.new.submit.request' | message}}</p>
              </div>
              <div class="icon">
                <i class="ion ion-stats-bars"></i>
              </div>
              <router-link to="/signuprequests" class="small-box-footer">
                {{'common.more.info' | message}}
                <i class="fa fa-arrow-circle-left"></i>
              </router-link>
            </div>
          </div><!-- ./col -->
          <div class="col-lg-3 col-xs-6">
            <!-- small box -->
            <div class="small-box bg-yellow">
              <div class="inner">
                <h3>{{summary.activeUsersCount}}</h3>
                <p>{{'home.summary.active.user' | message}}</p>
              </div>
              <div class="icon">
                <i class="ion ion-person-add"></i>
              </div>
              <router-link to="/usermanagement/list" class="small-box-footer">
                {{'common.more.info' | message}}
                <i class="fa fa-arrow-circle-left"></i>
              </router-link>
            </div>
          </div><!-- ./col -->
          <div class="col-lg-3 col-xs-6">
            <!-- small box -->
            <div class="small-box bg-red">
              <div class="inner">
                <h3>{{summary.activeOffersCount}}</h3>
                <p>{{'home.summary.active.offer' | message}}</p>
              </div>
              <div class="icon">
                <i class="ion ion-pie-graph"></i>
              </div>
              <router-link to="/promotions/list" class="small-box-footer">
                {{'common.more.info' | message}}
                <i class="fa fa-arrow-circle-left"></i>
              </router-link>
            </div>
          </div><!-- ./col -->
        </div>
        <div class="row">
          <!-- to do list -->
          <div class="box box-primary shadowed">
            <div class="box-header">
              <i class="ion ion-clipboard"></i>
              <h3 class="box-title">{{'home.notifications' | message}}</h3>
              <div class="box-tools pull-left">
                <ul class="pagination pagination-sm inline">
                  <li v-for="n in pages" :class="{active : searchParams.page == n}">
                    <a @click="setPage(n)">{{n}}</a>
                  </li>
                </ul>
              </div>
            </div><!-- /.box-header -->
            <div class="box-body" style="min-height: 500px;">
              <ul class="todo-list">
                <li v-for="(notification,index) in notifications">
                  <!-- drag handle -->
                  <span class="handle row-num">
                        {{((searchParams.page - 1) * 10) + index + 1}}
                  </span>
                  <span class="text">{{ notification.notificationText }}</span>

                  <small class="label label-danger">
                    <i class="fa fa-clock-o"> {{ notification.notificationDateStr }} </i>
                  </small>

                  <!--<div class="tools">-->
                    <!--<i class="fa fa-edit"></i>-->
                    <!--<i class="fa fa-trash-o"></i>-->
                  <!--</div>-->
                </li>

                <li>
                  <span class="text" v-if="notifications.length < 1">{{'table.no.items.found' | message}}</span>
                </li>

              </ul>
              <div class="overlay" v-if="!notificationsLoaded">
                <i class="fa fa-refresh fa-spin"></i>
              </div>
            </div><!-- /.box-body -->
            <div class="box-footer clearfix">
              <!--<button class="btn btn-default pull-left"><i class="fa fa-plus"></i> Add item</button>-->
            </div>
          </div><!-- to do list end -->

        </div>

      </lazy>
      </div>
        <div class="col-xs-1">

        </div>
      </div>
    </section>
  </div>
</template>

<script>
  import Lazy from "../Lazy";
  import axios from 'axios';

  export default {
    components: {Lazy},
    name: 'Home',
    data () {
      return {
        loaded: true,
        notificationsLoaded: true,
        summary: {},
        notifications: [],
        pages: 1,
        searchParams: {
          size: 15,
          page: 1
        },
      }
    },
    created: function () {
      this.getHomePageData()
    },
    methods: {
        getHomePageData: function () {
          this.getHomePageSummary();
          this.getNotifications();
        },
        getHomePageSummary: function () {
          var summaryUrl = '/s/home/summary';
          this.loaded = false;
          axios.get(summaryUrl)
            .then(response => {
              this.summary = response.data;
              this.loaded = true;
            })
            .catch(e => {
              this.loaded = true;
            })
        },
      getNotifications() {
        let countUrl = '/s/home/notification/count';
        let listUrl = '/s/home/notification/list';

        this.notificationsLoaded = false;

        axios.get(countUrl)
          .then(response => {
            this.pages = parseInt(Math.ceil(response.data / this.searchParams.size))
          })
          .catch(e => {
          })

        axios.get(listUrl, {
          params: {
            pageSize: this.searchParams.size,
            pageNumber: this.searchParams.page - 1
          }
        })
          .then(response => {
            this.notifications = response.data;
            this.notificationsLoaded = true;
          })
          .catch(e => {
            this.notificationsLoaded = true;
          })
      },
      setPage(n) {
        this.this.searchParams.page = n;
        this.getNotifications();
      }
    }
  }
</script>
<style scoped>
  .row-num {
    padding: 3px 4px;
    width: 20px;
    text-align: center;
    color: white;
    background-color: black;
    margin-left: 7px;
    border-radius: 3px;
  }
</style>
