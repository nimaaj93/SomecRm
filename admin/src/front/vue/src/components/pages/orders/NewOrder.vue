<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
        <span class="col-md-2">
        </span>
        <span class="col-md-8">
          <h2>
            <i class="fa fa-list" aria-hidden="true"></i>
            {{ 'sidebar.menu.order.management.submit' | message }}
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
                    {{'order.form.header' | message}}
                    <i class="fa fa-search" aria-hidden="true"></i>
                  </h3>
                </div>
                <div class="box-body row" :class="{'form-submit' : submitting}">
                  <div class="col-md-1">
                  </div>
                  <div class="col-md-10">
                    <form role="form">
                      <div class="row">
                        <div class="col-md-6">
                          <autocomplete
                            :url="getSearchUrl()"
                            anchor="searchAnchor"
                            label="searchTitle"
                            :customParams="{ access_token: getToken() }"
                            :min="3"
                            :debounce="500"
                            :placeholder="'user.search' | message"
                            :on-select="loadSelectedUser">
                          </autocomplete>
                        </div>

                      </div>
                    </form>
                  </div>
                  <div class="col-md-1">
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <lazy :loaded="loaded">
              <div class="col-xs-12">
                <div class="box">
                  <div class="box-body">
                    <div class="row">
                      <div class="table-wrapper" v-if="selectedUser.userId != null">
                        <table id="resultTable" class="table table-bordered table-striped">
                          <tbody>
                          <tr>
                            <td width="50%">{{'common.firstName' | message}}</td>
                            <td width="50%">{{ selectedUser.firstNameFa }}</td>
                          </tr>
                          <tr>
                            <td width="50%">{{'common.lastName' | message}}</td>
                            <td width="50%">{{ selectedUser.lastNameFa }}</td>
                          </tr>
                          <tr>
                            <td width="50%">{{'common.nationalCode' | message}}</td>
                            <td width="50%">{{ selectedUser.nationalCode }}</td>
                          </tr>
                          <tr>
                            <td width="50%">{{'common.phoneNumber' | message}}</td>
                            <td width="50%">{{ selectedUser.phoneNumber }}</td>
                          </tr>
                          </tbody>
                        </table>
                      </div>
                      <div v-else>
                        {{'please.choose.user' | message}}
                      </div>
                    </div>
                  </div>
                </div>

                <div class="box" v-if="selectedUser.userId != null">
                  <div class="box-header">
                    <h3 class="box-title">
                      {{'order.form.header' | message}}
                      <i class="fa fa-shopping-basket" aria-hidden="true"></i>
                    </h3>
                  </div>
                  <div class="box-body">
                    <form v-on:submit.prevent="submitOrder" role="form" novalidate>

                      <div class="row">
                        <!--<transition-group name="fade">-->
                          <div class="col-xs-12 col-md-3"
                               v-for="(categoryLevel,index) in categoryLevels">
                            <div class="form-group" :class="{'has-error': errors.has('categorySelect' + index)}"
                                 v-if="categoryLevel != null & categoryLevel.length > 0">
                              <label class="control-label"
                                     v-show="errors.has('categorySelect' + index)">{{'common.required' | error}}</label>
                              <label v-show="index == 0 & !errors.has('categorySelect' + index)">{{'common.category' | message}} *</label>
                              <label v-show="index != 0 & !errors.has('categorySelect' + index)">{{''}}</label>
                              <div class="input-group">
                                <select class="form-control"
                                        v-validate="'required'"
                                        :id="'categorySelect' + index"
                                        :name="'categorySelect' + index"
                                        @change="getCategories(categoryLevel.selectedCategoryId,index + 1)"
                                        v-model="categoryLevel.selectedCategoryId">
                                    <option v-for="(category,cindex) in categoryLevel" :value="category.orderCategoryId">
                                      <strong>{{category.titleFa}}</strong>
                                    </option>
                                </select>
                              </div>
                            </div>
                          </div>
                        <!--</transition-group>-->
                      </div>

                      <div class="row">
                        <div class="col-md-10">
                          <div class="form-group" :class="{'has-error': errors.has('inputDescription')}">
                            <label class="control-label"
                                   v-show="errors.has('inputDescription')">{{'common.required' | error}}</label>
                            <label for="inputDescription"
                                   v-show="!errors.has('inputDescription')">{{'common.description' | message}} *</label>
                            <div class="input-group">
                              <textarea type="text" id="inputDescription" class="form-control container"
                                        maxlength="1000"
                                        :disabled="submitting"
                                        name="inputDescription"
                                        v-validate="'required'"
                                        :placeholder="'common.description' | message"
                                        v-model="order.description">
                              </textarea>
                            </div>
                          </div>
                        </div>
                      </div>

                      <div class="row">
                        <div class="col-md-12">
                          <div class="form-group" :class="{'has-error': errors.has('addressSelect')}">
                            <label class="control-label"
                                   v-show="errors.has('addressSelect')">{{'common.required' | error}}</label>
                            <label v-show="!errors.has('addressSelect')">{{'common.address' | message}} *</label>
                            <div class="radio address-radio">
                              <div class="col-md-6 col-xs-12">
                                <div v-for="(address,index) in selectedUser.userAddresses" class="radio-item">
                                      <input type="radio" name="addressSelect"
                                             :disabled="submitting"
                                             v-validate="'required'"
                                             :id="'address' + address.addressId"
                                             :value="address.addressId"
                                             v-model="order.addressId">
                                      <span>{{address.cityFa}}</span><br>
                                      <span>{{address.addressFa}}</span>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>

                      <div class="row button-container">
                        <div class="col-md-6">
                          <button class="btn btn-primary"
                                  :disabled="submitting"
                                  type="submit">
                            {{'common.submit' | message}}
                          </button>
                        </div>
                      </div>
                    </form>
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
  import axios from 'axios';
  import Lazy from '../../Lazy'
  import Autocomplete from 'vue2-autocomplete-js'

  export default {
    name: 'NewOrder',
    components: {
      Lazy,Autocomplete
    },
    data () {
      return {
        loaded: true,
        submitting: false,
        searchParams: {
          props: {}
        },
        selectedUser: {},
        order: {},
        categoryLevels: []
      }
    },
    created: function () {

    },
    methods: {
      getSearchUrl() {
        return axios.defaults.baseURL + '/rest/s/usermangement/search';
      },
      getToken() {
        return window.localStorage.getItem('access_token');
      },
      loadSelectedUser(obj) {
        this.selectedUser = obj;
        this.searchUser();
      },
      searchUser: function () {
        var userInfoUrl = '/s/adminorder/userinfo'
            this.submitting = true
            this.loaded = false

            axios.get(userInfoUrl, {
              params: {
                nationalCode: this.selectedUser.nationalCode
              }
            })
              .then(response => {
                this.submitting = false
                this.loaded = true
                if (response.data) {
                  this.selectedUser = response.data
                  this.getUserAddress()
                  this.getCategories(null,0)
                } else {
                }
              })
              .catch(e => {
                this.submitting = false
                this.loaded = true
              })
      },
      submitOrder: function () {
          var submitOrderUrl = '/s/adminorder/sbmitrequest'

        if (!(this.selectedUser && this.selectedUser.nationalCode)) {
          var msg = this.$options.filters.error('please.choose.user');
          this.$toasted.error(msg);
          return;
        }

        this.$validator.validateAll().then((result) => {
          if (result) {
            var categoryId
            if (this.categoryLevels[this.categoryLevels.length - 1].selectedCategoryId) {
              categoryId = this.categoryLevels[this.categoryLevels.length - 1].selectedCategoryId
            } else {
              categoryId = this.categoryLevels[this.categoryLevels.length - 2].selectedCategoryId
            }
            this.submitting = true
            axios.post(submitOrderUrl, {
              nationalCode: this.selectedUser.nationalCode,
              description: this.order.description,
              addressId: this.order.addressId,
              categoryId: categoryId
            })
              .then(response => {
                this.submitting = false
                let msg = this.$options.filters.message('user.order.submit.success')
                this.$toasted.success(msg)
                this.$router.push({path: '/orders'});
              })
              .catch(e => {
                this.submitting = false
              })
            return
          }
          var msg = this.$options.filters.error('common.form.validation.failed')
          this.$toasted.error(msg)
        });
      },
      getUserAddress: function () {
        if (this.selectedUser && this.selectedUser.nationalCode) {
          var userAddressUrl = '/s/adminorder/useraddress'

          this.submitting = true
          this.loaded = false

          axios.get(userAddressUrl, {
            params: {
              nationalCode: this.selectedUser.nationalCode
            }
          })
            .then(response => {
              this.submitting = false
              this.loaded = true
              this.selectedUser.userAddresses = response.data
            })
            .catch(e => {
              this.submitting = false
              this.loaded = true
            })


        }
      },
      getCategories: function (categoryId,levelTrigger) {
        var categoriesUrl = '/s/adminorder/category'
        console.log(categoryId,levelTrigger)
        axios.get(categoriesUrl, {
          params: {
            categoryId: categoryId
          }
        })
          .then(response => {
            this.categoryLevels = this.categoryLevels.splice(0,levelTrigger)
            if (response.data) {
              this.categoryLevels.push(response.data)
            }
          })
          .catch(e => {
          })
      }
    }
  }
</script>

<style scoped>
  .input-group {
    width: 100%;
  }
  .button-container {
    margin-top: 20px;
  }

</style>
