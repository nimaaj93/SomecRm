<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
        <span class="col-md-2">
        </span>
        <span class="col-md-8">
          <h2>
            <i class="fa fa-user-plus" aria-hidden="true"></i>
            {{ 'corporate.customer.new.user.header' | message }}
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
          <div class="box box-success">
            <div class="box-header with-border">
              <h3 class="box-title">{{'corporate.customer.info' | message}}</h3>
            </div>
            <div class="box-body table-wrapper">
              <div class="table-wrapper">
                <table id="resultTable" class="table table-bordered table-striped">
                  <tbody>
                  <tr>
                    <td>{{'corporate.customer.name.fa' | message}}</td>
                    <td>{{ corporateCustomer.nameFa }}</td>
                  </tr>
                  <tr>
                    <td>{{'corporate.customer.name.en' | message}}</td>
                    <td>{{ corporateCustomer.nameEn }}</td>
                  </tr>
                  <tr>
                    <td>{{'corporate.customer.phonenumber' | message}}</td>
                    <td>{{ corporateCustomer.phoneNumber }}</td>
                  </tr>
                  <tr>
                    <td>{{'corporate.customer.id' | message}}</td>
                    <td>{{ corporateCustomer.companyId }}</td>
                  </tr>
                  <tr>
                    <td>{{'common.expire.date' | message}}</td>
                    <td>{{ corporateCustomer.expireDate }}</td>
                  </tr>
                  <tr>
                    <td>{{'common.status' | message}}</td>
                    <td>
                          <span class="label label-success" v-if="corporateCustomer.status === 100">
                                  {{'common.active' | message}}
                          </span>
                      <span class="label label-warning" v-if="corporateCustomer.status === 101">
                                  {{'common.inactive' | message}}
                          </span>
                    </td>
                  </tr>
                  <tr>
                    <td>{{'common.address' | message}}</td>
                    <td>{{ corporateCustomer.cityFa }}, {{ corporateCustomer.addressFa }} </td>
                  </tr>
                  <tr>
                    <td>{{'common.address.en' | message}}</td>
                    <td>{{ corporateCustomer.cityEn }}, {{ corporateCustomer.addressEn }}</td>
                  </tr>
                  </tbody>
                </table>
              </div>

            </div>
            <div class="overlay" v-if="!coporateInfoLoaded">
              <i class="fa fa-refresh fa-spin"></i>
            </div>
          </div>

        </div>
        <div class="col-md-1">
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
        </div>
        <div class="col-md-8" :class="{'form-submit' : submitting}">
          <form v-on:submit.prevent="doRegisterUser" role="form" novalidate autocomplete="no">
            <div class="box box-warning">
              <div class="box-header with-border">
                <h3 class="box-title">{{'common.customerInfo' | message}}</h3>
              </div>
              <div class="box-body">
                <div class="row">
                  <div class="col-md-1"/>
                  <div class="col-md-10">
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group" :class="{'has-error': errors.has('inputFirstnameFa')}">
                          <label class="control-label"
                                 v-show="errors.has('inputFirstnameFa')">{{'common.required' | error}}</label>
                          <label for="inputFirstnameFa"
                                 v-show="!errors.has('inputFirstnameFa')">{{'common.firstName' | message}} *</label>
                          <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-user-o" aria-hidden="true"></i>
                            </span>
                            <input type="text" id="inputFirstnameFa" class="form-control"
                                   maxlength="50"
                                   :disabled="submitting"
                                   data-vv-delay="1000"
                                   name="inputFirstnameFa"
                                   v-validate="'required'"
                                   :placeholder="'common.firstName' | message" v-focus
                                   v-model="signUpModel.userPersonalModel.firstNameFa">
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group" :class="{'has-error': errors.has('inputLastnameFa')}">
                          <label class="control-label"
                                 v-show="errors.first('inputLastnameFa')">{{'common.required' | error}}</label>
                          <label for="inputLastnameFa"
                                 v-show="!errors.first('inputLastnameFa')">{{'common.lastName' | message}} *</label>
                          <div class="input-group">
                            <span class="input-group-addon">
                              <i class="fa fa-user-o" aria-hidden="true"></i>
                            </span>
                            <input type="text" id="inputLastnameFa" class="form-control"
                                   maxlength="50"
                                   :disabled="submitting"
                                   data-vv-delay="1000"
                                   name="inputLastnameFa"
                                   v-validate="'required'"
                                   :placeholder="'common.lastName' | message"
                                   v-model="signUpModel.userPersonalModel.lastNameFa">
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group" :class="{'has-error': errors.has('inputFirstnameEn')}">
                          <label class="control-label"
                                 v-show="errors.has('inputFirstnameEn')">{{'common.required' | error}}</label>
                          <label for="inputFirstnameEn"
                                 v-show="!errors.has('inputFirstnameEn')">{{'common.firstName.en' | message}} *</label>
                          <div class="input-group">
                            <span class="input-group-addon">
                              <i class="fa fa-user-o" aria-hidden="true"></i>
                            </span>
                            <input type="text" id="inputFirstnameEn" class="form-control"
                                   maxlength="50"
                                   :disabled="submitting"
                                   data-vv-delay="1000"
                                   name="inputFirstnameEn"
                                   v-validate="'required'"
                                   :placeholder="'common.firstName.en' | message"
                                   v-model="signUpModel.userPersonalModel.firstNameEn">
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group" :class="{'has-error': errors.has('inputLastnameEn')}">
                          <label class="control-label"
                                 v-show="errors.has('inputLastnameEn')">{{'common.required' | error}}</label>
                          <label for="inputLastnameEn"
                                 v-show="!errors.has('inputLastnameEn')">{{'common.lastName.en' | message}} *</label>
                          <div class="input-group">
                            <span class="input-group-addon">
                              <i class="fa fa-user-o" aria-hidden="true"></i>
                            </span>
                            <input type="text" id="inputLastnameEn" class="form-control"
                                   maxlength="50"
                                   :disabled="submitting"
                                   data-vv-delay="1000"
                                   name="inputLastnameEn"
                                   v-validate="'required'"
                                   :placeholder="'common.lastName.en' | message"
                                   v-model="signUpModel.userPersonalModel.lastNameEn">
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="form-group" :class="{'has-error': errors.has('inputNationalCode')}">
                      <label class="control-label"
                             v-show="errors.has('inputNationalCode')">{{'common.required' | error}}</label>
                      <label for="inputNationalCode"
                             v-show="!errors.has('inputNationalCode')">{{'common.nationalCode' | message}} *</label>
                      <div class="input-group">
                        <span class="input-group-addon">
                          <i class="fa fa-shield" aria-hidden="true"></i>
                        </span>
                        <input type="text" id="inputNationalCode" class="form-control"
                               maxlength="10"
                               :disabled="submitting"
                               data-vv-delay="1000"
                               name="inputNationalCode"
                               v-validate="'required'"
                               :placeholder="'common.nationalCode' | message"
                               v-model="signUpModel.userPersonalModel.nationalCode">
                      </div>
                    </div>
                    <div class="form-group" :class="{'has-error': errors.has('inputPhoneNumber')}">
                      <label class="control-label"
                             v-show="errors.has('inputPhoneNumber')">{{'common.required' | error}}</label>
                      <label for="inputPhoneNumber"
                             v-show="!errors.has('inputPhoneNumber')">{{'common.phoneNumber' | message}} *</label>
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
                               :placeholder="'common.phoneNumber' | message"
                               v-model="signUpModel.userPersonalModel.phoneNumber">
                      </div>
                    </div>
                    <div class="form-group" :class="{'has-error': errors.has('inputEmail')}">
                      <label class="control-label" v-show="errors.has('inputEmail')">{{'email.invalid' | error}}</label>
                      <label for="inputEmail" v-show="!errors.has('inputEmail')">{{'common.email' | message}}</label>
                      <div class="input-group">
                        <span class="input-group-addon">
                          <i class="fa fa-envelope"></i>
                        </span>
                        <input type="text" id="inputEmail" class="form-control"
                               maxlength="100"
                               :disabled="submitting"
                               name="inputEmail"
                               data-vv-delay="1000"
                               v-validate="'email'"
                               :placeholder="'common.email' | message"
                               v-model="signUpModel.userPersonalModel.email">
                      </div>
                    </div>

                    <div class="form-group" :class="{'has-error': errors.has('inputDateOfBirth')}">
                      <label class="control-label"
                             v-show="errors.has('inputDateOfBirth')">{{'common.required' | error}}</label>
                      <label for="inputDateOfBirth"
                             v-show="!errors.has('inputDateOfBirth')">{{'common.dateOfBirth' | message}} *</label>
                      <div class="input-group">
                        <span class="input-group-addon">
                          <i class="fa fa-calendar" aria-hidden="true"></i>
                        </span>
                        <pdatepicker v-model="signUpModel.userPersonalModel.birthDate"
                                     open-transition-animation=""
                                     :placeholder="'common.dateOfBirth' | message"
                                     id="inputDateOfBirth"
                                     :disabled="submitting"
                                     name="inputDateOfBirth"
                                     v-validate="'required'">
                        </pdatepicker>
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
                        <pdatepicker v-model="signUpModel.userPersonalModel.expireDateStr"
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
                <h3 class="box-title">{{'common.customerAddresses' | message}}</h3>
              </div>
              <div class="box-body">
                <div class="row">
                  <div class="address-box-container col-md-10">
                    <transition-group name="fade">
                      <div class="address-item-box"
                           v-for="addressInfo, key in signUpModel.userAddressInfoList"
                           v-bind:key="addressInfo">
                        <div class="row">
                          <div class="col-xs-1">
                            <span>{{key + 1}}</span>
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
                                       v-model="addressInfo.cityFa">
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
                                     v-model="addressInfo.addressFa">
                            </div>

                            <div class="form-group" :class="{'has-error': errors.has('inputCityEn')}">
                              <label class="control-label"
                                     v-show="errors.has('inputCityEn')">{{'common.required' | error}}</label>
                              <label for="inputCityEn" v-show="!errors.has('inputCityEn')">{{'common.city.en' | message}} *</label>
                              <div class="input-group">
                                <input type="text" class="form-control"
                                       maxlength="20"
                                       id="inputCityEn"
                                       :placeholder="'common.city.en' | message"
                                       :disabled="submitting"
                                       data-vv-delay="1000"
                                       name="inputCityEn"
                                       v-validate="'required'"
                                       v-model="addressInfo.cityEn">
                              </div>
                            </div>

                            <div class="form-group" :class="{'has-error': errors.has('inputAddressEn')}">
                              <label class="control-label"
                                     v-show="errors.has('inputAddressEn')">{{'common.required' | error}}</label>
                              <label for="inputAddressEn" v-show="!errors.has('inputAddressEn')">{{'common.address.en' | message}} *</label>
                              <input type="text" class="form-control"
                                     maxlength="80"
                                     id="inputAddressEn"
                                     :placeholder="'common.address.en' | message"
                                     :disabled="submitting"
                                     data-vv-delay="1000"
                                     name="inputAddressEn"
                                     v-validate="'required'"
                                     v-model="addressInfo.addressEn">
                            </div>
                            <div class="form-group">
                              <button @click.prevent="removeAddress(key)"
                                      :disabled="submitting"
                                      v-if="signUpModel.userAddressInfoList.length > 1"
                                      class="btn btn-danger">
                                {{'common.delete' | message}}
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </transition-group>
                  </div>
                  <div class="add-address col-md-1">
                    <button @click.prevent="addAddress"
                            :disabled="submitting"
                            class="btn btn-lg btn-primary">
                      <i class="fa fa-plus" aria-hidden="true"></i>
                    </button>
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
  import DatePicker from '../../input/DatePicker'
  export default {
    name: 'CorporateCustomerNewUserRegistration',
    components: {
      DatePicker
    },
    data () {
      return {
        submitting: false,
        coporateInfoLoaded: true,
        signUpModel: {
          userPersonalModel: {},
          userAddressInfoList: []
        },
        corporateCustomer: {}
      }
    },
    created: function () {

      this.signUpModel = {
        userPersonalModel: {
          corporateUserId: this.$route.params.corporateCustomerId
        },
        userAddressInfoList: []
      }
      this.addAddress();
      this.loadCorporateCustomerInfo()
    },
    methods: {
      loadCorporateCustomerInfo() {
        let corporateId = this.$route.params.corporateCustomerId
        let url = 's/corporateuser/details';
        this.coporateInfoLoaded = false;
        this.submitting = true;
        axios.get(url, {
          params: {
            corporateUserId: corporateId
          }
        })
          .then(response => {
            this.coporateInfoLoaded = true;
            this.submitting = false;
            this.corporateCustomer = response.data
          })
          .catch(e => {
            this.coporateInfoLoaded = true;
            this.submitting = false;
          })
      },
      doRegisterUser: function () {
        this.$validator.validateAll().then((result) => {
          if (result) {
            this.submitting = true
            axios.post(`/s/user/signup`, {
              userPersonalModel: this.signUpModel.userPersonalModel,
              userAddressInfoList: this.signUpModel.userAddressInfoList
            })
              .then(response => {
                this.submitting = false
                let msg = this.$options.filters.message('user.registration.success')
                this.$toasted.success(msg)
                let corporateId = this.$route.params.corporateCustomerId
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
      },
      addAddress: function () {
        this.signUpModel.userAddressInfoList.push({
          addressFa: '',
          addressEn: '',
          cityFa: '',
          cityEn: ''
        })
      },
      removeAddress: function (index) {
        this.signUpModel.userAddressInfoList.splice(index, 1)
      }
    }
  }
</script>
<style scoped>

</style>
