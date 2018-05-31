<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
        <span class="col-md-2">
        </span>
        <span class="col-md-8">
          <h2>
            <i class="fa fa-user-plus" aria-hidden="true"></i>
            {{ 'sidebar.menu.admin.user.registration' | message }}
          </h2>
        </span>
      </div>
    </section>
    <section class="content">
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

                    <div class="form-group">
                      <div class="checkbox checkbox-success">
                        <label>
                          <input type="checkbox" v-model="signUpModel.userPersonalModel.superAdmin"/>
                          {{'common.super.admin' | message}}
                        </label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="box box-warning">
              <div class="box-header with-border">
                <h3 class="box-title">{{'registration.password.header' | message}}</h3>
              </div>
              <div class="box-body">
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-6">
                    <div class="form-group" :class="{'has-error': errors.has('inputPassword')}">
                      <label class="control-label"
                             v-show="errors.has('inputPassword')">{{'common.required' | error}}</label>
                      <label for="inputLastnameEn"
                             v-show="!errors.has('inputPassword')">{{'registration.password.input' | message}} *</label>
                      <div class="input-group">
                            <span class="input-group-addon">
                              <i class="fa fa-user-o" aria-hidden="true"></i>
                            </span>
                        <input type="password" id="inputPassword" class="form-control"
                               maxlength="50"
                               :disabled="submitting"
                               data-vv-delay="1000"
                               name="inputPassword"
                               v-validate="'required'"
                               :placeholder="'registration.password.input' | message"
                               v-model="signUpModel.userPersonalModel.password">
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-6">
                    <div class="form-group" :class="{'has-error': errors.has('inputPasswordConfirm')}">
                      <label class="control-label"
                             v-show="errors.has('inputPasswordConfirm')">{{'common.required' | error}}</label>
                      <label for="inputLastnameEn"
                             v-show="!errors.has('inputPasswordConfirm')">{{'registration.password.confirm' | message}} *</label>
                      <div class="input-group">
                            <span class="input-group-addon">
                              <i class="fa fa-user-o" aria-hidden="true"></i>
                            </span>
                        <input type="password" id="inputPasswordConfirm" class="form-control"
                               maxlength="50"
                               :disabled="submitting"
                               data-vv-delay="1000"
                               name="inputPasswordConfirm"
                               v-validate="'required'"
                               :placeholder="'registration.password.confirm' | message"
                               v-model="signUpModel.userPersonalModel.confirmPassword">
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
  import DatePicker from '../../input/DatePicker'
  export default {
    name: 'AdminRegistration',
    components: {
      DatePicker
    },
    data () {
      return {
        submitting: false,
        signUpModel: {
          userPersonalModel: {
            firstNameFa: '',
            lastNameFa: '',
            firstNameEn: '',
            lastNameEn: '',
            nationalCode: '',
            phoneNumber: '',
            email: '',
            birthDate: '',
            superAdmin: false,
            password: '',
            confirmPassword: ''
          }
        }
      }
    },
    created: function () {

      this.signUpModel = {
        userPersonalModel: {
          firstNameFa: '',
          lastNameFa: '',
          firstNameEn: '',
          lastNameEn: '',
          nationalCode: '',
          phoneNumber: '',
          email: '',
          birthDate: '',
          superAdmin: false,
          password: '',
          confirmPassword: ''
        }
      }
    },
    methods: {
      doRegisterUser: function () {

        if (this.signUpModel.userPersonalModel.password === this.signUpModel.userPersonalModel.confirmPassword) {

          this.$validator.validateAll().then((result) => {
            if (result) {
              this.submitting = true
              axios.post(`/s/adminuser/adminsignup`,
                this.signUpModel.userPersonalModel
              )
                .then(response => {
                  this.submitting = false
                  let msg = this.$options.filters.message('user.registration.success')
                  this.$toasted.success(msg)
                  this.$router.push({path: '/adminregistration/success/' + this.signUpModel.userPersonalModel.nationalCode});
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
        } else {
          let errorMsg = this.$options.filters.error('password.confirm.not.matched')
          this.$toasted.error(errorMsg)
        }
      }
    }
  }
</script>
<style scoped>

</style>
