<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
        <span class="col-md-2">
        </span>
        <span class="col-md-8">
          <h2>
            <i class="fa fa-industry" aria-hidden="true"></i>
            {{ 'sidebar.menu.corporate.user.registration' | message }}
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
        <div class="col-md-2">
        </div>
        <div class="col-md-8" :class="{'form-submit' : submitting}">
          <form v-on:submit.prevent="registerCorporateCustomer" role="form" novalidate autocomplete="no">
            <div class="box box-warning">
              <div class="box-header with-border">
                <h3 class="box-title">{{'corporate.customer.info' | message}}</h3>
              </div>
              <div class="box-body">
                <div class="row">
                  <div class="col-md-1"/>
                  <div class="col-md-10">
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group" :class="{'has-error': errors.has('nameFa')}">
                          <label class="control-label"
                                 v-show="errors.has('nameFa')">{{'common.required' | error}}</label>
                          <label for="nameFa"
                                 v-show="!errors.has('nameFa')">{{'corporate.customer.name.fa' | message}} *</label>
                          <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-user-o" aria-hidden="true"></i>
                            </span>
                            <input type="text" id="nameFa" class="form-control"
                                   maxlength="50"
                                   :disabled="submitting"
                                   data-vv-delay="1000"
                                   name="nameFa"
                                   v-validate="'required'"
                                   :placeholder="'corporate.customer.name.fa' | message" v-focus
                                   v-model="corporateCustomerModel.nameFa">
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group" :class="{'has-error': errors.has('nameEn')}">
                          <label class="control-label"
                                 v-show="errors.first('nameEn')">{{'common.required' | error}}</label>
                          <label for="nameEn"
                                 v-show="!errors.first('nameEn')">{{'corporate.customer.name.en' | message}} *</label>
                          <div class="input-group">
                            <span class="input-group-addon">
                              <i class="fa fa-user-o" aria-hidden="true"></i>
                            </span>
                            <input type="text" id="nameEn" class="form-control"
                                   maxlength="50"
                                   :disabled="submitting"
                                   data-vv-delay="1000"
                                   name="nameEn"
                                   v-validate="'required'"
                                   :placeholder="'corporate.customer.name.en' | message"
                                   v-model="corporateCustomerModel.nameEn">
                          </div>
                        </div>
                      </div>
                    </div>

                    <div class="form-group" :class="{'has-error': errors.has('inputPhoneNumber')}">
                      <label class="control-label"
                             v-show="errors.has('inputPhoneNumber')">{{'common.required' | error}}</label>
                      <label for="inputPhoneNumber"
                             v-show="!errors.has('inputPhoneNumber')">{{'corporate.customer.phonenumber' | message}} *</label>
                      <div class="input-group">
                        <span class="input-group-addon">
                          <i class="fa fa-phone-square" aria-hidden="true"></i>
                        </span>
                        <input type="text" id="inputPhoneNumber" class="form-control"
                               maxlength="11"
                               :disabled="submitting"
                               data-vv-delay="1000"
                               v-validate="'required'"
                               name="inputPhoneNumber"
                               :placeholder="'corporate.customer.phonenumber' | message"
                               v-model="corporateCustomerModel.phoneNumber">
                      </div>
                    </div>

                    <div class="form-group" :class="{'has-error': errors.has('inputCorporateId')}">
                      <label class="control-label"
                             v-show="errors.has('inputCorporateId')">{{'common.required' | error}}</label>
                      <label for="inputCorporateId"
                             v-show="!errors.has('inputCorporateId')">{{'corporate.customer.id' | message}}</label>
                      <div class="input-group">
                        <span class="input-group-addon">
                          <i class="fa fa-phone-square" aria-hidden="true"></i>
                        </span>
                        <input type="text" id="inputCorporateId" class="form-control"
                               maxlength="50"
                               :disabled="submitting"
                               name="inputCorporateId"
                               :placeholder="'corporate.customer.id' | message"
                               v-model="corporateCustomerModel.companyId">
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
                        <pdatepicker v-model="corporateCustomerModel.expireDate"
                                     open-transition-animation=""
                                     :placeholder="'common.expire.date' | message"
                                     id="inputExpireDate"
                                     :disabled="submitting"
                                     name="inputExpireDate"
                                     v-validate="'required'">
                        </pdatepicker>
                      </div>
                    </div>

                  </div>
                </div>
              </div>
            </div>

            <div class="box box-warning">
              <div class="box-header with-border">
                <h3 class="box-title">{{'corporate.customer.address' | message}}</h3>
              </div>
              <div class="box-body">
                <div class="row">
                  <div class="address-box-container col-md-10">
                      <div class="address-item-box">
                        <div class="row">
                          <div class="col-xs-1">
                          </div>
                          <div class="col-xs-10 form-group">
                            <div class="form-group" :class="{'has-error': errors.has('inputCityFa')}">
                              <label class="control-label"
                                     v-show="errors.has('inputCityFa')">{{'common.required' | error}}</label>
                              <label for="inputCityFa" v-show="!errors.has('inputCityFa')">{{'common.city' | message}} *</label>
                              <div class="input-group">
                                <input type="text" class="form-control"
                                       maxlength="20"
                                       id="inputCityFa"
                                       :placeholder="'common.city' | message"
                                       :disabled="submitting"
                                       data-vv-delay="1000"
                                       name="inputCityFa"
                                       v-validate="'required'"
                                       v-model="corporateCustomerModel.cityFa">
                              </div>
                            </div>

                            <div class="form-group" :class="{'has-error': errors.has('inputAddressFa')}">
                              <label class="control-label"
                                     v-show="errors.has('inputAddressFa')">{{'common.required' | error}}</label>
                              <label for="inputAddressFa" v-show="!errors.has('inputAddressFa')">{{'common.address' | message}} *</label>
                              <input type="text" class="form-control"
                                     maxlength="80"
                                     id="inputAddressFa"
                                     :placeholder="'common.address' | message"
                                     :disabled="submitting"
                                     data-vv-delay="1000"
                                     name="inputAddressFa"
                                     v-validate="'required'"
                                     v-model="corporateCustomerModel.addressFa">
                            </div>

                            <div class="form-group" :class="{'has-error': errors.has('inputCityEn')}">
                              <label class="control-label"
                                     v-show="errors.has('inputCityEn')">{{'common.required' | error}}</label>
                              <label for="inputCityEn" v-show="!errors.has('inputCityEn')">{{'common.city.en' | message}}</label>
                              <div class="input-group">
                                <input type="text" class="form-control"
                                       maxlength="20"
                                       id="inputCityEn"
                                       :placeholder="'common.city.en' | message"
                                       :disabled="submitting"
                                       data-vv-delay="1000"
                                       name="inputCityEn"
                                       v-model="corporateCustomerModel.cityEn">
                              </div>
                            </div>

                            <div class="form-group" :class="{'has-error': errors.has('inputAddressEn')}">
                              <label class="control-label"
                                     v-show="errors.has('inputAddressEn')">{{'common.required' | error}}</label>
                              <label for="inputAddressEn" v-show="!errors.has('inputAddressEn')">{{'common.address.en' | message}}</label>
                              <input type="text" class="form-control"
                                     maxlength="80"
                                     id="inputAddressEn"
                                     :placeholder="'common.address.en' | message"
                                     :disabled="submitting"
                                     data-vv-delay="1000"
                                     name="inputAddressEn"
                                     v-model="corporateCustomerModel.addressEn">
                            </div>
                          </div>
                        </div>
                      </div>
                  </div>
                </div>
              </div>
            </div>
            <button class="btn btn-lg btn-primary"
                    :disabled="submitting"
                    type="submit">
              {{'common.register' | message}}
            </button>
          </form>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: 'CorporateCustomerDataEntry',
    data () {
      return {
        submitting: false,
        corporateCustomerModel: {}
      }
    },
    created: function () {
    },
    methods: {
      registerCorporateCustomer() {
        let url = 's/corporateuser/signup';

        this.$validator.validateAll().then((result) => {
          if (result) {
            this.submitting = true
            axios.post(url, {
              nameFa: this.corporateCustomerModel.nameFa,
              nameEn: this.corporateCustomerModel.nameEn,
              companyId: this.corporateCustomerModel.companyId,
              expireDate: this.corporateCustomerModel.expireDate,
              cityFa: this.corporateCustomerModel.cityFa,
              cityEn: this.corporateCustomerModel.cityEn,
              addressFa: this.corporateCustomerModel.addressFa,
              addressEn: this.corporateCustomerModel.addressEn,
              phoneNumber: this.corporateCustomerModel.phoneNumber
            })
              .then(response => {
                this.submitting = false
                let corporateId = response.data.corporateUserId
                let msg = this.$options.filters.message('corporate.customer.registration.success')
                this.$toasted.success(msg)
                this.$router.push({path: '/corporatecustomer/details/' + corporateId});
              })
              .catch(e => {
                this.submitting = false
              })
            return;
          }
          this.submitting = false
          var msg = this.$options.filters.error('common.form.validation.failed')
          this.$toasted.error(msg)
        });
      }
    }
  }
</script>
<style scoped>

</style>
