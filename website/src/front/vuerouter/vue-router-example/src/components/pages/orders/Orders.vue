<template>
  <div class="boxContent">
    <div class="boxContent">
      <div class="tabBox">
        <div class="tabTitle">
          <div class="tabBtn tab1" :class="{'active' : activeTabIndex == 1}">
            <span @click="setActiveTabIndex(1)">{{'orders.in.progress' | message}}</span>
          </div>
          <div class="tabBtn tab2" :class="{'active' : activeTabIndex == 2}">
            <span @click="setActiveTabIndex(2)">{{'orders.done' | message}}</span>
          </div>
          <div class="tabBtn tab3" :class="{'active' : activeTabIndex == 3}">
            <span @click="setActiveTabIndex(3)">{{'orders.canceled' | message}}</span>
          </div>
        </div>
        <div class="tabContent" style="padding: 10px 10px 10px 5px;">
          <div id="tab1" class="tab">
            <div class="boxContentMain">

              <lazy :loaded="loaded">
                <!-- Item Row -->
                <div v-if="orders.length > 0">
                <div class="itemRow" v-for="(order,index) in orders">
                  <div class="itemRowTitle">
                    <span>
                      {{((searchParams.page - 1) * searchParams.size) + index + 1}}
                    </span>
                    <br>
                    <span v-for="(category, index) in order.categoryDtoList.slice().reverse()">
                                       {{category.titleFa}} <i v-if="index != (order.categoryDtoList.length - 1)" class="fa fa-chevron-left" aria-hidden="true"></i>
                    </span>
                  </div>
                  <div class="itemRowDescription">
                    {{'order.submit.date' | message}}:
                    {{order.orderDate}}<br>
                    {{'order.number' | message}}:
                    {{order.orderNumber}} <br/>
                    {{'order.category' | message}}:
                    <span v-for="(category, index) in order.categoryDtoList.slice().reverse()">
                                       {{category.titleFa}} <i v-if="index != (order.categoryDtoList.length - 1)" class="fa fa-chevron-left" aria-hidden="true"></i>
                    </span>
                    <br>
                    {{'common.description' | message}}:
                    {{order.description}}
                    <br/>
                    <a v-if="order.orderStatus === 400 || order.orderStatus === 401 || order.orderStatus === 402" class="left" @click="cancelOrder(order.orderId)">
                      {{'order.cancel' | message}}
                    </a>
                  </div>
                </div>
                </div>
                <div class="itemRow" v-else>
                  <div class="itemRowDescription" style="text-align: center">
                    {{'no.items.found' | message}}
                  </div>
                </div>
                <!-- Item Row End -->
              </lazy>
            </div>
              <div class="paginator" v-if="pages > 0" :class="{'disabled' : submitting}">
                <a @click="firstPage" class="last" :class="{'disabled' : searchParams.page < 2}">&nbsp</a>
                <a @click="prevPage" class="next" :class="{'disabled' : searchParams.page < 2}">&nbsp</a>
                <a @click="setPage(n)" v-for="n in pages" class="pages" :class="{'active' : searchParams.page === n}">{{n}}</a>
                <a @click="nextPage" class="back" :class="{'disabled' : searchParams.page >= pages}">&nbsp</a>
                <a @click="lastPage" class="first" :class="{'disabled' : searchParams.page >= pages}">&nbsp</a>
              </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import Lazy from '../../Lazy'
  import axios from 'axios'

  export default {
    name: 'Orders',
    components: {
      Lazy
    },
    data () {
      return {
        loaded: true,
        submitting: false,
        activeTabIndex: 1,
        searchParams: {
          props: {
            status: 400
          },
          size: 10,
          page: 1
        },
        pages: 1,
        orders: []
      }
    },
    created: function () {
//      $(function () {
//        $('.boxContentMain').jScrollPane();
//      });
      this.searchOrders()
    },
    methods: {
        searchOrders() {
            this.searchParams.page = 1;
            this.loadOrders();
        },
      loadOrders: function () {
        var countUrl = '/s/orderreport/count'
        var listUrl = '/s/orderreport/list'

        this.loaded = false
        this.submitting = true

        axios.get(countUrl, {
          params: {
            tabindex: this.activeTabIndex
          }
        })
          .then(response => {
            this.pages = parseInt(Math.ceil(response.data / this.searchParams.size))
          })
          .catch(e => {
          })

        axios.get(listUrl, {
          params: {
            tabindex: this.activeTabIndex,
            pageSize: this.searchParams.size,
            pageNumber: this.searchParams.page - 1
          }
        })
          .then(response => {
            this.orders = response.data
            this.loaded = true
            this.submitting = false
          })
          .catch(e => {
            this.loaded = true
            this.submitting = false
          })

      },
      setActiveTabIndex(index) {
          this.activeTabIndex = index;
        this.searchOrders()
      },
      setPage(n) {
        this.searchParams.page = n;
        this.loadOrders();
      },
      lastPage() {
        this.searchParams.page = this.pages;
        this.loadOrders();
      },
      nextPage() {
        this.searchParams.page++;
        this.loadOrders();
      },
      prevPage() {
        this.searchParams.page--;
        this.loadOrders();
      },
      firstPage() {
        this.searchParams.page = 1;
        this.loadOrders();
      },
      cancelOrder(orderId) {
        let url = '/s/orderreport/cancel';

        let querystring = require('querystring');
        this.submitting = true;
        axios.post(url, querystring.stringify({
          orderId: orderId
        }))
          .then((response) => {
            this.submitting = false;
            let msg = this.$options.filters.message('order.cancel.success')
            this.$toasted.success(msg);
            this.loadOrders();
          })
          .catch((error) => {
            this.submitting = false;
          });
      }
    }
  }
</script>
