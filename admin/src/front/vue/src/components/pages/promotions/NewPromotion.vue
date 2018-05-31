<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
        <span class="col-md-2">
        </span>
        <span class="col-md-8">
          <h2>
            <i class="fa fa-diamond" aria-hidden="true"></i>
            {{ 'sidebar.menu.promotions.submit' | message }}
          </h2>
        </span>
      </div>
    </section>
    <section class="content">
      <lazy :loaded="true">
        <div class="row">
          <div class="col-md-1">
          </div>
          <div class="col-md-10">
            <div class="row" :class="{'form-submit' : submitting}">
              <form v-on:submit.prevent="submitPromotion" role="form" novalidate>
                <div class="col-xs-12">

                  <div class="box">
                    <div class="box-body row">
                      <div class="col-md-1">
                      </div>
                      <div class="col-md-10">

                        <div class="row">
                          <div class="form-group" :class="{'has-error': errors.has('promotionHeader')}">
                            <label class="control-label"
                                   v-show="errors.has('promotionHeader')">{{'common.required' | error}}</label>
                            <label for="promotionHeader"
                                   v-show="!errors.has('promotionHeader')">{{'common.header' | message}} *</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                  <i class="fa fa-phone-square" aria-hidden="true"></i>
                                </span>
                              <input type="text" id="promotionHeader" class="form-control"
                                     autocomplete="off"
                                     maxlength="100"
                                     :disabled="submitting"
                                     name="promotionHeader"
                                     v-validate="'required'"
                                     :placeholder="'common.header' | message"
                                     v-model="promotion.header">
                            </div>
                          </div>
                        </div>

                        <div class="row">
                          <div class="col-md-6">
                            <div class="form-group" :class="{'has-error': errors.has('promotionExpireDate')}">
                              <label class="control-label"
                                     v-show="errors.has('promotionExpireDate')">{{'common.required' | error}}</label>
                              <label for="promotionExpireDate"
                                     v-show="!errors.has('promotionExpireDate')">{{'common.expire.date' | message}} *</label>
                              <div class="input-group">
                                  <span class="input-group-addon">
                                    <i class="fa fa-phone-square" aria-hidden="true"></i>
                                  </span>
                                <input type="text" id="promotionExpireDate" class="form-control"
                                       autocomplete="off"
                                       maxlength="100"
                                       :disabled="submitting"
                                       name="promotionExpireDate"
                                       v-mask="'####/##/##'"
                                       v-validate="'required'"
                                       :placeholder="'common.expire.date' | message"
                                       v-model="promotion.offerExpireDate">
                              </div>
                            </div>
                          </div>
                        </div>

                        <div class="row">
                          <div class="form-group" :class="{'has-error': errors.has('inputDescription')}">
                            <label class="control-label"
                                   v-show="errors.has('inputDescription')">{{'common.required' | error}}</label>
                            <label for="inputDescription"
                                   v-show="!errors.has('inputDescription')">{{'common.description' | message}} *</label>
                            <div class="input-group fill">
                              <textarea type="text" id="inputDescription" class="form-control container"
                                        maxlength="1000"
                                        :disabled="submitting"
                                        name="inputDescription"
                                        v-validate="'required'"
                                        :placeholder="'common.description' | message"
                                        v-model="promotion.description">
                              </textarea>
                            </div>
                          </div>
                        </div>

                        <div class="row">
                          <div class="col-md-12 button-container">
                            <button class="btn btn-primary pull-right"
                                    :disabled="submitting"
                                    type="submit">
                              {{'common.send' | message}}
                            </button>
                          </div>
                        </div>

                      </div>
                      <div class="col-md-1">
                      </div>
                    </div>
                  </div>
                </div>
              </form>
            </div>



          </div>
          <div class="col-md-1">
          </div>
        </div>
      </lazy>
    </section>

  </div>
</template>

<script>
  import axios from 'axios';
  import Lazy from '../../Lazy';

  export default {
    name: 'NewPromotion',
    components: {
      Lazy
    },
    data () {
      return {
        submitting: false,
        promotion: {}
      }
    },
    created: function () {

    },
    methods: {
      submitPromotion: function () {
        var submitUrl = '/s/useroffer/submit'

        this.$validator.validateAll().then((result) => {
          if (result) {
            this.submitting = true
            axios.post(submitUrl, {
              header: this.promotion.header,
              offerExpireDate: this.promotion.offerExpireDate,
              description: this.promotion.description
            })
              .then(response => {
                this.submitting = false
                let msg = this.$options.filters.message('promotion.submit.success')
                this.$toasted.success(msg)
                this.$router.push({path: '/promotions/list'});
              })
              .catch(e => {
                this.submitting = false
              })
            return;
          }
          var msg = this.$options.filters.error('common.form.validation.failed')
          this.$toasted.error(msg)
        });
      },
    }
  }
</script>

<style scoped>
  .button-container {
    margin: 20px 0;
  }
  .fill {
    width: 100%;
  }
  textarea {
    min-height: 250px;
  }
</style>
