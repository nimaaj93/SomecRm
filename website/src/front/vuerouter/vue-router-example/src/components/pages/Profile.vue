<template>
  <div class="boxContent">
    <div class="tabBox">
      <!--<div class="tabTitle">-->
        <!--<div class="tabBtn tab1 active">-->
          <!--<span>اطلاعات هویتی شما</span>-->
        <!--</div>-->
        <!--<div class="tabBtn tab2">-->
          <!--<span>اطلاعات شما</span>-->
        <!--</div>-->
        <!--<div class="tabBtn tab3">-->
          <!--<span>تقویم شما</span>-->
        <!--</div>-->
      <!--</div>-->
      <div class="tabContent" style="padding: 10px 10px 10px 5px;">
        <lazy :loaded="loaded">
        <div id="tab1" class="tab">
          <div class="boxContentNoScroll">
            <div class="right"  style="width: 60%;">
              <div class="innerRow">
                <span>{{'common.name' | message}}:</span>
                <span>{{userinfo.firstNameFa}}</span>
              </div>
              <div class="innerRow">
                <span>{{'common.lastname' | message}}:</span>
                <span>{{userinfo.lastNameFa}}</span>
              </div>
              <div class="innerRow">
                <span>{{'common.date.of.birth' | message}}:</span>
                <span>{{userinfo.birthDate}}</span>
              </div>
              <div class="innerRow">
                <span>{{'common.national.code' | message}}:</span>
                <span>{{userinfo.nationalCode}}</span>
              </div>
              <div class="innerRow">
                <span>{{'common.login.id' | message}}:</span>
                <span>{{userinfo.nationalCode}}</span>
              </div>
              <div class="innerRow" v-if="userinfo.corporateUserNameFa">
                <span>{{'common.corporate.user' | message}}:</span>
                <span>{{userinfo.corporateUserNameFa}}</span>
              </div>
            </div>
            <div class="left"  style="width: 40%;">
              <p>
                {{'profile.change.info.request.desc' | message}}
              </p>
              <div class="btnBox">
                <a class="btn" :class="{'disabled' : submitting || submitted}"
                   @click="sendInfoChangeRequest">{{'profile.change.info.submit' | message}}</a>
              </div>
              <div class="btnBox">
                <router-link class="btn" :class="{'disabled' : submitting}" to="/resetpassword">{{'reset.password.link' | message}}</router-link>
              </div>
            </div>
            <div>
              <div class="right"  style="width: 60%;">
                <div class="innerRow">
                  <span>{{'common.mobile.number' | message}}:</span>
                  <span>{{contactinfo.phoneNumber}}</span>
                </div>
                <div class="innerRow">
                  <span>{{'common.email' | message}}:</span>
                  <span>{{contactinfo.email}}</span>
                </div>
              </div>
              <div class="spliterLine"></div>
              <div class="right">
                <div class="innerRow new-address-bar">
                  <span class="">{{'profile.new.address' | message}}:</span>
                  <br class="mobile-breaks"/>
                  <span>*</span><input :placeholder="'common.city' | message"
                                       v-model="newAddress.cityFa"
                                       name="cityFa"
                                       maxlength="20"
                                       :disabled="submitting"
                                        v-validate="'required'"/>
                  <br class="mobile-breaks"/>
                  <span>*</span><input :placeholder="'common.address' | message"
                                       v-model="newAddress.addressFa"
                                       name="addressFa"
                                       v-validate="'required'"
                                       :disabled="submitting"
                                       maxlength="80"/>
                  <br class="mobile-breaks"/>
                  <a class="btn inlineBtn" :class="{'disabled' : submitting}" @click="addNewAddress">{{'profile.add.new.address' | message}}</a>
                </div>
                <br>
                <div class="innerRow" v-for="addressItem,index in contactinfo.userAddressEntity">
                  <span><strong>{{index + 1}}.</strong></span> <span>{{addressItem.cityFa}}</span>, <span>{{addressItem.addressFa}}</span>
                  <span v-if="index !== 0" @click="deleteAddress(addressItem.addressId)"><i class="fa fa-times" aria-hidden="true"></i></span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div id="tab2" class="tab">

        </div>
        <div id="tab3" class="tab">
          <div class="boxContentMain">
            <!-- Item Row -->
            <div class="itemRow">
              <div class="itemRowTitle">Item Title</div>
              <div class="itemRowDescription">
                Item Description
              </div>
            </div>
            <!-- Item Row End -->
          </div>
        </div>
        </lazy>
      </div>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'
  import Lazy from '../Lazy'

  export default {
    name: 'Profile',
    components: {
      Lazy
    },
    data () {
      return {
        loaded: true,
        submitting: false,
        submitted: false,
        userinfo: {},
        contactinfo: {},
        newAddress: {}
      }
    },
    mounted: function () {
      var tabs = $(".tabTitle .tabBtn").length;
      $('.tabBtn.tab1').on('click',function(){
        $('#tab1').css('left','0');
        $('#tab2').css('left','-655px');
        $('#tab3').css('left','-1310px');
        $('.tabBtn.tab1').addClass("active");
        $('.tabBtn.tab2').removeClass("active");
        $('.tabBtn.tab3').removeClass("active");
      });
      $('.tabBtn.tab2').on('click',function(){
        $('#tab1').css('left','650px');
        $('#tab2').css('left','-0px');
        $('#tab3').css('left','-6550px');
        $('.tabBtn.tab1').removeClass("active");
        $('.tabBtn.tab2').addClass("active");
        $('.tabBtn.tab3').removeClass("active");
      });
      $('.tabBtn.tab3').on('click',function(){
        $('#tab1').css('left','1310px');
        $('#tab2').css('left','655px');
        $('#tab3').css('left','0px');
        $('.tabBtn.tab1').removeClass("active");
        $('.tabBtn.tab2').removeClass("active");
        $('.tabBtn.tab3').addClass("active");
      });
    },
    created: function () {
      this.loadUserProfile()
      this.loadUserContactInfo()
    },
    methods: {
      loadUserProfile: function () {
        var userInfoUrl =  's/userinformation/userinformation'
        this.loaded = false
        axios.get(userInfoUrl)
          .then(response => {
            this.loaded = true
            this.userinfo = response.data
          })
          .catch(e => {
            this.loaded = true
          })
      },
      loadUserContactInfo: function () {
        var contactUrl =  's/userinformation/contactinformation'
        this.loaded = false
        axios.get(contactUrl)
          .then(response => {
            this.loaded = true
            this.contactinfo = response.data
          })
          .catch(e => {
            this.loaded = true
          })
      },
      addNewAddress: function () {
        var addAddressUrl = '/s/userinformation/submitaddress'

        this.$validator.validateAll().then((result) => {
          if (result) {

            this.submitting = true;

            axios.post(addAddressUrl, {
              addressFa: this.newAddress.addressFa,
              cityFa: this.newAddress.cityFa,
              addressEn: this.newAddress.addressFa,
              cityEn: this.newAddress.cityFa,
            })
              .then(response => {
                this.submitting = false;
                this.newAddress = {}
                this.loadUserContactInfo()
              })
              .catch(e => {
                this.submitting = false;
              })
            return;
          }
          var msg = this.$options.filters.error('common.form.required.error');
          this.$toasted.error(msg);
        });
      },
      sendInfoChangeRequest() {
        let url = '/s/userinformation/changeinfo';

        this.submitting = true;

        axios.post(url)
          .then(response => {
            this.submitting = false;
            this.submitted = true;
            let msg = this.$options.filters.message('change.info.request.submit.success')
            this.$toasted.success(msg)
          })
          .catch(e => {
            this.submitting = false;
          })

      },
      deleteAddress(addressId) {
        let url = '/s/userinformation/deleteaddress';

        let querystring = require('querystring');
        this.submitting = true;
        axios.post(url, querystring.stringify({
          addressId: addressId
        }))
          .then((response) => {
            this.submitting = false;
            let msg = this.$options.filters.message('address.delete.success')
            this.$toasted.success(msg);
            this.loadUserContactInfo();
          })
          .catch((error) => {
            this.submitting = false;
          });
      }
    }
  }
</script>
