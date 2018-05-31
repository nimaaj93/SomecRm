<template>
  <div class="boxContent">
    <div class="boxContent">
      <div class="tabBox">
        <div class="tabContent" style="padding: 10px 10px 10px 5px;">
          <div id="tab1" class="tab">
            <div class="boxContentMain">

              <lazy :loaded="loaded">
                <div v-if="transactions.length > 0">
                <!-- Item Row -->
                <div class="itemRow" v-for="(transaction,index) in transactions">
                  <div class="itemRowTitle">
                    <span>
                      {{((searchParams.page - 1) * searchParams.size) + index + 1}}
                    </span>
                    <br>
                    <span>

                    </span>
                  </div>
                  <div class="itemRowDescription">
                    {{'common.amount' | message}} : {{transaction.amount | amount}}
                    <br/>
                    {{'common.status' | message}} :
                    <span v-if="transaction.transactionStatus === 900">{{'common.successful' |  message}}</span>
                    <span v-else-if="transaction.transactionStatus === 901">{{'common.failed' |  message}}</span>
                    <br/>
                    <span v-if="transaction.debit === true">
                              <i class="fa fa-level-up" aria-hidden="true"></i>
                              {{'transaction.debit' | message}}
                    </span>
                    <span v-else>
                              <i class="fa fa-level-down" aria-hidden="true"></i>
                              {{'transaction.credit' | message}}
                    </span>
                    <br/>
                    {{'common.balance' | message}} : {{transaction.lastBalance | amount}}
                    <br/>
                    {{'transaction.due.date' | message}} : {{transaction.transactionDate}}
                    <br/>
                    <span v-if="transaction.transactionNumber">
                      {{'transaction.number' | message}} : {{transaction.transactionNumber}}
                      <br/>
                    </span>
                    <span v-if="transaction.transactionFollowupNumber">
                    {{'transaction.followup.number' | message}} : {{transaction.transactionFollowupNumber}}
                      <br/>
                    </span>
                  </div>
                </div>
                <!-- Item Row End -->
                </div>
                <div v-else>
                  <div class="itemRowDescription" style="text-align: center">
                    {{'no.items.found' | message}}
                  </div>
                </div>
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
    name: 'Transactions',
    components: {
      Lazy
    },
    data () {
      return {
        loaded: true,
        submitting: false,
        searchParams: {
          props: {
            status: 400
          },
          size: 10,
          page: 1
        },
        pages: 1,
        transactions: []
      }
    },
    created: function () {
      this.searchTransactions();
    },
    methods: {
      searchTransactions() {
        this.searchParams.page = 1;
        this.loadTransactions();
      },
      loadTransactions: function () {
        var countUrl = '/s/transactions/count'
        var listUrl = '/s/transactions/list'

        this.loaded = false
        this.submitting = true

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
            this.transactions = response.data
            this.loaded = true
            this.submitting = false
          })
          .catch(e => {
            this.loaded = true
            this.submitting = false
          })

      },
      setPage(n) {
        this.searchParams.page = n;
        this.loadTransactions();
      },
      lastPage() {
        this.searchParams.page = this.pages;
        this.loadTransactions();
      },
      nextPage() {
        this.searchParams.page++;
        this.loadTransactions();
      },
      prevPage() {
        this.searchParams.page--;
        this.loadTransactions();
      },
      firstPage() {
        this.searchParams.page = 1;
        this.loadTransactions();
      }
    }
  }
</script>
