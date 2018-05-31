<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
        <span class="col-md-2">
        </span>
        <span class="col-md-8">
          <h2>
            <i class="fa fa-industry" aria-hidden="true"></i>
            {{ 'corporate.customer.info' | message }}
            <!--<small>Preview</small>-->
          </h2>
        </span>
      </div>
    </section>
    <section class="content">
      <lazy :loaded="loaded">
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
                        <td>{{'corporate.customer.id' | message}}</td>
                        <td>{{ corporateCustomer.companyId }}</td>
                      </tr>
                      <tr>
                        <td>{{'corporate.customer.phonenumber' | message}}</td>
                        <td>{{ corporateCustomer.phoneNumber }}</td>
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
          </div>

        </div>
        <div class="col-md-1">
        </div>
      </div>

        <!-- action bar start -->
        <div class="row button-container">
          <div class="col-xs-1"></div>
          <div class="col-xs-10">
            <button class="btn btn-primary pull-right"
                    :class="{'disabled' : corporateCustomer.status === 100}"
                    @click="enableCustomer">
              {{'common.enable' | message}}
            </button>

            <button class="btn btn-primary pull-right"
                    :class="{'disabled' : corporateCustomer.status === 101}"
                    @click="disableCustomer">
              {{'common.disable' | message}}
            </button>

            <button class="btn btn-primary pull-right"
                    @click="setSelectedCorporateUser()" data-toggle="modal" data-target="#editCorporateUserPopup">
              {{'common.edit' | message}}
            </button>

            <button class="btn btn-primary pull-right"
                  @click="goToAddUser">
              {{'corporate.customer.add.user' | message}}
            </button>
          </div>
          <div class="col-xs-1"></div>
        </div>
        <!-- action bar end -->

      <div class="row">
        <div class="col-xs-1"></div>
        <div class="col-xs-10 tableBox">
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">{{'corporate.customer.users' | message}}</h3>
            </div>
            <div class="box-body">
              <div class="table-wrapper" v-if="users.length > 0">
                <ul class="pagination">
                  <li v-for="n in pages" :class="{active : searchParams.page == n}">
                    <a @click="setPage(n)">{{n}}</a>
                  </li>
                </ul>
                <table id="usersDataTable" class="table table-bordered table-hover table-striped">
                  <thead>
                  <tr>
                    <th>{{'common.row' | message}}</th>
                    <th>{{'common.fullName' | message}}</th>
                    <th>{{'common.fullName.en' | message}}</th>
                    <th>{{'common.nationalCode' | message}}</th>
                    <th>{{'common.phoneNumber' | message}}</th>
                    <th>{{'common.email' | message}}</th>
                    <th>{{'common.dateOfBirth' | message}}</th>
                    <th>{{'common.status' | message}}</th>
                    <th>{{'common.expire.date' | message}}</th>
                    <th>{{'common.actions' | message}}</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="(user,index) in users">
                    <td>{{((searchParams.page - 1) * 10) + index + 1}}</td>
                    <td>{{user.firstNameFa}} {{user.lastNameFa}}</td>
                    <td>{{user.firstNameEn}} {{user.lastNameEn}}</td>
                    <td>{{user.nationalCode}}</td>
                    <td>{{user.phoneNumber}}</td>
                    <td>{{user.email}}</td>
                    <td>{{user.birthDate}}</td>
                    <td>
                                <span class="label label-success" v-if="user.status === 100">
                                  {{'common.active' | message}}
                                </span>
                      <span class="label label-warning" v-if="user.status === 101">
                                  {{'common.inactive' | message}}
                                </span>
                    </td>
                    <td>
                      {{user.expireDate}}
                    </td>
                    <td>
                      <div class="input-group">
                        <div class="input-group-btn">
                          <a class="btn dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-cog" aria-hidden="true"></i>
                          </a>
                          <ul class="dropdown-menu">
                          <li><a @click="setSelectedUser(user.nationalCode)" data-toggle="modal" data-target="#editUserPopup">
                          <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                          {{'common.edit' | message}}</a>
                          </li>
                          <li v-if="user.status === 101"><a @click="enableUser(user.nationalCode)">
                          <i class="fa fa-ticket" aria-hidden="true"></i>
                          {{'common.enable' | message}}
                          </a></li>
                          <li v-if="user.status === 100"><a @click="disableUser(user.nationalCode)">
                          <i class="fa fa-ticket" aria-hidden="true"></i>
                          {{'common.disable' | message}}
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

        <div class="row button-container">
          <div class="col-xs-4"></div>
          <div class="col-xs-4">
            <router-link tag="button" to="/home" class="btn btn-primary pull-right">
              <i class="fa fa-home"></i>
              {{'sidebar.menu.home' | message}}
            </router-link>
            <router-link tag="button" to="/corporatecustomer/list" class="btn btn-success pull-right">
              <i class="fa fa-angle-double-left" aria-hidden="true"></i>
              {{'common.back' | message}}
            </router-link>
          </div>
          <div class="col-xs-4"></div>
        </div>

      </div>
      </lazy>
    </section>

    <div id="editUserPopup" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <lazy :loaded="userEditLoaded" v-if="userEditLoaded">
          <div class="modal-content" v-if="selectedUser != null">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">{{'common.edit' | message}}</h4>
            </div>
            <div class="modal-body">
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
                             :disabled="userEditSubmitting"
                             data-vv-delay="1000"
                             name="inputFirstnameFa"
                             v-validate="'required'"
                             :placeholder="'common.firstName' | message" v-focus
                             v-model="selectedUser.firstNameFa">
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
                             :disabled="userEditSubmitting"
                             data-vv-delay="1000"
                             name="inputLastnameFa"
                             v-validate="'required'"
                             :placeholder="'common.lastName' | message"
                             v-model="selectedUser.lastNameFa">
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
                             :disabled="userEditSubmitting"
                             data-vv-delay="1000"
                             name="inputFirstnameEn"
                             v-validate="'required'"
                             :placeholder="'common.firstName.en' | message"
                             v-model="selectedUser.firstNameEn">
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
                             :disabled="userEditSubmitting"
                             data-vv-delay="1000"
                             name="inputLastnameEn"
                             v-validate="'required'"
                             :placeholder="'common.lastName.en' | message"
                             v-model="selectedUser.lastNameEn">
                    </div>
                  </div>
                </div>
              </div>
              <div class="form-group" :class="{'has-error': errors.has('inputNationalCode')}">
                <label class="control-label"
                       v-show="errors.has('inputNationalCode')">{{'common.required' | error}}</label>
                <label for="inputNationalCode"
                       v-show="!errors.has('inputNationalCode')">{{'common.nationalCode' | message}}</label>
                <div class="input-group">
                        <span class="input-group-addon">
                          <i class="fa fa-shield" aria-hidden="true"></i>
                        </span>
                  <input type="text" id="inputNationalCode" class="form-control"
                         maxlength="10"
                         disabled="true"
                         data-vv-delay="1000"
                         name="inputNationalCode"
                         v-validate="'required'"
                         :placeholder="'common.nationalCode' | message"
                         v-model="selectedUser.nationalCode">
                </div>
              </div>
              <div class="form-group" :class="{'has-error': errors.has('inputPhoneNumber')}">
                <label class="control-label"
                       v-show="errors.has('inputPhoneNumber')">{{'common.required' | error}}</label>
                <label for="inputPhoneNumber"
                       v-show="!errors.has('inputPhoneNumber')">{{'common.phoneNumber' | message}}</label>
                <div class="input-group">
                        <span class="input-group-addon">
                          <i class="fa fa-phone-square" aria-hidden="true"></i>
                        </span>
                  <input type="text" id="inputPhoneNumber" class="form-control"
                         maxlength="11"
                         disabled="true"
                         data-vv-delay="1000"
                         v-validate="'required'"
                         name="inputPhoneNumber"
                         :placeholder="'common.phoneNumber' | message"
                         v-model="selectedUser.phoneNumber">
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
                         :disabled="userEditSubmitting"
                         name="inputEmail"
                         data-vv-delay="1000"
                         v-validate="'email'"
                         :placeholder="'common.email' | message"
                         v-model="selectedUser.email">
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
                  <pdatepicker v-model="selectedUser.birthDate"
                               open-transition-animation=""
                               :placeholder="'common.dateOfBirth' | message"
                               id="inputDateOfBirth"
                               :disabled="userEditSubmitting"
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
                  <pdatepicker v-model="selectedUser.expireDateStr"
                               open-transition-animation=""
                               :placeholder="'common.expire.date' | message"
                               id="inputExpireDate"
                               :disabled="userEditSubmitting"
                               name="inputExpireDate"
                               v-validate="'required'">
                  </pdatepicker>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <button :disabled="userEditSubmitting" type="button" class="btn btn-primary float" @click="updateUser">{{'common.update' | message}}</button>
              <button :disabled="userEditSubmitting" type="button" class="btn btn-danger float" data-dismiss="modal">{{'common.cancel' | message}}</button>
            </div>
          </div>
        </lazy>
      </div>
    </div>

    <!-- corporate customer edit dialog -->
    <div id="editCorporateUserPopup" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <lazy :loaded="corporateUserEditLoaded">
          <div class="modal-content" v-if="selectedCorporateUser != null">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">{{'common.edit' | message}}</h4>
            </div>
            <div class="modal-body">
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
                             v-model="selectedCorporateUser.nameFa">
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
                             v-model="selectedCorporateUser.nameEn">
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
                         v-model="selectedCorporateUser.phoneNumber">
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
                         v-model="selectedCorporateUser.companyId">
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
                  <pdatepicker v-model="selectedCorporateUser.expireDate"
                               open-transition-animation=""
                               :placeholder="'common.expire.date' | message"
                               id="inputExpireDate"
                               :disabled="submitting"
                               name="inputExpireDate"
                               v-validate="'required'">
                  </pdatepicker>
                </div>
              </div>

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
                                   v-model="selectedCorporateUser.cityFa">
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
                                 v-model="selectedCorporateUser.addressFa">
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
                                   v-model="selectedCorporateUser.cityEn">
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
                                 v-model="selectedCorporateUser.addressEn">
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

            <div class="modal-footer">
              <button :disabled="corporateUserEditSubmitting" type="button" class="btn btn-primary float" @click="updateCorporateUser">{{'common.update' | message}}</button>
              <button :disabled="corporateUserEditSubmitting" type="button" class="btn btn-danger float" data-dismiss="modal">{{'common.cancel' | message}}</button>
            </div>
          </div>
          </div>
        </lazy>
      </div>
    </div>
    <!-- corporate customer edit dialog end -->

  </div>
</template>

<script>
  import Lazy from '../../Lazy';
  import axios from 'axios';

  export default {
    name: 'CorporateCustomerDetails',
    components: {
      Lazy
    },
    data () {
      return {
        submitting: false,
        loaded: true,
        pages: 1,
        searchParams: {
          props: {
            nationalCode: '',
            phoneNumber: ''
          },
          size: 10,
          page: 1
        },
        users: [],
        corporateCustomer: {},
        selectedUser: {},
        selectedCorporateUser: {},
        userEditSubmitting: false,
        corporateUserEditSubmitting: false,
        userEditLoaded: false,
        corporateUserEditLoaded: true,

      }
    },
    created: function () {
      this.loadData();
    },
    methods: {
        loadData() {
          this.loadCorporateCustomerInfo();
          this.searchUsers();
        },
      loadCorporateCustomerInfo: function () {
        let corporateId = this.$route.params.corporateCustomerId
        var url = 's/corporateuser/details';
        this.loaded = false
        axios.get(url, {
          params: {
            corporateUserId: corporateId
          }
        })
          .then(response => {
            this.corporateCustomer = response.data
            this.loaded = true
          })
          .catch(e => {
            this.loaded = true
          })
      },
      searchUsers: function () {
        this.searchParams.page = 1
        this.loadUsers()
      },
      loadUsers: function () {
        var countUrl = '/s/usermangement/count'
        var listUrl = '/s/usermangement/list'

        this.loaded = false
        this.submitting = true

        axios.get(countUrl, {
          params: {
            nationalCode: this.searchParams.props.nationalCode,
            phoneNumber: this.searchParams.props.phoneNumber,
            corporateUserId: this.$route.params.corporateCustomerId
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
            nationalCode: this.searchParams.props.nationalCode,
            phoneNumber: this.searchParams.props.phoneNumber,
            corporateUserId: this.$route.params.corporateCustomerId
          }
        })
          .then(response => {
            this.users = response.data
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
        this.loadUsers()
      },
      prevPage: function () {
        this.searchParams.page -= 1
        this.loadUsers()
      },
      setPage: function (pageNumber) {
        this.searchParams.page = pageNumber
        this.loadUsers()
      },
      enableCustomer() {
        let url = '/s/corporateuser/activate';
        let querystring = require('querystring');

        axios.post(url, querystring.stringify({
          corporateUserId: this.$route.params.corporateCustomerId
        }))
          .then((response) => {
            let msg = this.$options.filters.message('corporate.customer.enable.success')
            this.$toasted.success(msg);
            this.loadData();
          })
          .catch((error) => {

          });

      },
      disableCustomer() {
        let url = '/s/corporateuser/inactivate';
        let querystring = require('querystring');

        axios.post(url, querystring.stringify({
          corporateUserId: this.$route.params.corporateCustomerId
        }))
          .then((response) => {
            let msg = this.$options.filters.message('corporate.customer.disable.success')
            this.$toasted.success(msg);
            this.loadData();
          })
          .catch((error) => {

          });
      },
      setSelectedCorporateUser() {
        this.corporateUserEditLoaded = false;
        this.selectedCorporateUser = JSON.parse(JSON.stringify(this.corporateCustomer));
        this.corporateUserEditLoaded = true;
      },
      updateCorporateUser() {
        var updateUrl = '/s/corporateuser/update';
        var querystring = require('querystring');

        this.$validator.validateAll().then((result) => {
          if (result) {
            this.corporateUserEditSubmitting = true
            axios.post(updateUrl, {
              nameFa: this.selectedCorporateUser.nameFa,
              nameEn: this.selectedCorporateUser.nameEn,
              companyId: this.selectedCorporateUser.companyId,
              expireDate: this.selectedCorporateUser.expireDate,
              cityFa: this.selectedCorporateUser.cityFa,
              cityEn: this.selectedCorporateUser.cityEn,
              addressFa: this.selectedCorporateUser.addressFa,
              addressEn: this.selectedCorporateUser.addressEn,
              phoneNumber: this.selectedCorporateUser.phoneNumber,
              corporateUserId: this.$route.params.corporateCustomerId
            })
              .then(response => {
                this.corporateUserEditSubmitting = false;
                let msg = this.$options.filters.message('corporate.customer.update.success');
                this.$toasted.success(msg);
                $('#editCorporateUserPopup').modal('hide');
                this.loadData();
              })
              .catch(e => {
                this.corporateUserEditSubmitting = false
              })
            return;
          }
          this.corporateUserEditSubmitting = false
          var msg = this.$options.filters.error('common.form.validation.failed')
          this.$toasted.error(msg)
        });
      },
      goToAddUser() {
        this.$router.push({path: '/corporatecustomer/newuser/' + this.$route.params.corporateCustomerId});
      },
      setSelectedUser: function (nationalCode) {

        var url = '/s/user/userinfo'

        this.userEditLoaded = false

        axios.get(url, {
          params: {
            nationalCode: nationalCode
          }
        })
          .then(response => {
            this.selectedUser = response.data
            this.userEditLoaded = true
          })
          .catch(e => {
            this.selectedUser = null
            this.userEditLoaded = true
          })
      },
      updateUser: function () {

        var updateUrl = '/s/useredit/update'

        this.$validator.validateAll().then((result) => {
          if (result) {
            this.userEditSubmitting = true
            axios.post(updateUrl, {
              firstNameFa: this.selectedUser.firstNameFa,
              lastNameFa: this.selectedUser.lastNameFa,
              firstNameEn: this.selectedUser.firstNameEn,
              lastNameEn: this.selectedUser.lastNameEn,
              nationalCode: this.selectedUser.nationalCode,
              phoneNumber: this.selectedUser.phoneNumber,
              email: this.selectedUser.email,
              birthDate: this.selectedUser.birthDate,
              expireDateStr: this.selectedUser.expireDateStr
            })
              .then(response => {
                this.userEditSubmitting = false
                let msg = this.$options.filters.message('user.update.success')
                this.$toasted.success(msg)
                $('#editUserPopup').modal('hide')
                this.loadUsers()
              })
              .catch(e => {
                this.userEditSubmitting = false
              })
            return;
          }
          this.userEditSubmitting = false
          var msg = this.$options.filters.error('common.form.validation.failed')
          this.$toasted.error(msg)
        });
      },
      enableUser: function (nationalCode) {
        var enableUrl = '/s/usermangement/enable'
        var querystring = require('querystring');
        this.submitting = true
        axios.post(enableUrl, querystring.stringify({
          nationalCode: nationalCode
        }))
          .then(response => {
            this.submitting = false
            let msg = this.$options.filters.message('user.enable.success')
            this.$toasted.success(msg)
            this.loadUsers()
          })
          .catch(e => {
            this.submitting = false
            this.loadUsers()
          })
      },
      disableUser: function (nationalCode) {
        var disableUrl = '/s/usermangement/disable'
        var querystring = require('querystring');
        this.submitting = true
        axios.post(disableUrl, querystring.stringify({
          nationalCode: nationalCode
        }))
          .then(response => {
            this.submitting = false
            let msg = this.$options.filters.message('user.disable.success')
            this.$toasted.success(msg)
            this.loadUsers()
          })
          .catch(e => {
            this.submitting = false
            this.loadUsers()
          })
      }
    }
  }
</script>

<style scoped>
  .button-container button {
    margin: 15px;
  }

  .tableBox {
    margin-top: 50px;
  }
</style>
