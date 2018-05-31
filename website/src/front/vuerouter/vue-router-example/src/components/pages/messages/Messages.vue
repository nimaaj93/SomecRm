<template>
  <div class="boxContent">
    <lazy :loaded="loaded">
    <div class="tabBox">
      <div class="tabTitle">
        <div class="tabBtn tab1 active">
          <span class="notification" v-if="newMessagesCount > 0">{{newMessagesCount}}</span>
          <span @click="loadMessages">{{'messages.tab.header' | message}}</span>
        </div>
        <div class="tabBtn tab2">
          <span @click="loadOffers">{{'offers.tab.header' | message}}</span>
          <span class="notification" v-if="newOffersCount > 0">{{newOffersCount}}</span>
        </div>
      </div>
      <div class="tabContent" style="padding: 10px 10px 10px 5px;">
        <div id="tab1" class="tab">

          <div class="boxContentMain">
            <div v-if="userMessages.length > 0">
              <scroll-wrapper>
              <!-- Item Row -->
              <div v-for="(umessage,index) in userMessages" class="itemRow">
                <div class="itemRowTitle">
                  <span>
                    {{index + 1}}
                  </span>
                  <br/>
                  {{umessage.messageHeader}}
                </div>
                <div class="itemRowDescription">
                  {{umessage.description}}
                </div>
              </div>
              <!-- Item Row End -->
              </scroll-wrapper>
            </div>
            <div v-else>
              <div class="itemRowDescription" style="text-align: center">
                {{'no.items.found' | message}}
              </div>
            </div>
          </div>
        </div>
        <div id="tab2" class="tab">
          <div class="boxContentMain">
            <div v-if="offers.length > 0">
              <!-- Item Row -->
              <div class="itemRow" v-for="(offer,index) in offers">
                <div class="itemRowTitle">
                  <span>
                    {{index + 1}}
                  </span>
                  <br/>
                  {{offer.promotionHeader}}
                </div>
                <div class="itemRowDescription">
                  {{'common.expire.date' | message}} : {{offer.expireDate}}<br>
                  {{'common.description' | message}} : {{offer.description}}
                </div>
              </div>
              <!-- Item Row End -->
            </div>
            <div v-else>
              <div class="itemRowDescription" style="text-align: center">
                {{'no.items.found' | message}}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    </lazy>
  </div>
</template>

<script>
  import axios from 'axios'
  import Lazy from "../../Lazy";
  import { mapState } from 'vuex'
  import ScrollWrapper from '@/components/ScrollWrapper'

  export default {
    components: {Lazy,ScrollWrapper},
    name: 'Messages',
    data () {
      return {
        loaded: true,
        submitting: false,
        userMessages: [],
        offers: []
      }
    },
    created: function () {
      this.loadMessages()
    },
    mounted: function () {
      $('.tabBtn.tab1').on('click',function(){
        $('#tab1').css('left','0');
        $('#tab2').css('left','-655px');
        $('.tabBtn.tab1').addClass("active");
        $('.tabBtn.tab2').removeClass("active");
      });
      $('.tabBtn.tab2').on('click',function(){
        $('#tab1').css('left','655px');
        $('#tab2').css('left','0');
        $('.tabBtn.tab1').removeClass("active");
        $('.tabBtn.tab2').addClass("active");
      });
    },
    methods: {
      loadMessages: function () {
        var getUrl =  '/s/promotion/messages'
        axios.get(getUrl)
          .then(response => {
            this.userMessages = response.data;
            this.seenMessages();
          })
          .catch(e => {
          })
      },
      seenMessages() {
          let url = '/s/promotion/updatemessage'
          axios.post(url)
            .then(response => {
              window.eventBus.$emit('new-msg-load')
            })
            .catch(e => {
            })
      },
      loadOffers: function () {
        var getUrl =  '/s/promotion/newofferlist';
        axios.get(getUrl)
          .then(response => {
            this.offers = response.data;
            this.seenOffers();
          })
          .catch(e => {
          })
      },
      seenOffers() {
          let url = '/s/promotion/seenoffers';
        axios.post(url)
          .then(response => {
            window.eventBus.$emit('new-offer-load')
          })
          .catch(e => {
          })
      }
    },
    computed: mapState([
      'newMessagesCount',
      'newOffersCount'
    ])
  }
</script>
