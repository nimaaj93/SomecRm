<template>
  <div class="boxContent">
    <div class="boxContentMain" :class="{'disabled' : submitting}">
      <div class="boxContentMiddleTitle">{{'orders.quick.access' | message}}</div>
      <div class="desktop-shortcut">
        <div class="shortcutBox">
          <div class="col3Row">
            <a class="shortcut" @click="loadShortCut([26,27])">
                                      <span>{{'orders.short.cut.1' | message}}
                                      </span>
            </a>
            <a class="shortcut" @click="loadShortCut([1,2])">
                                      <span>
                                        {{'orders.short.cut.2' | message}}
                                      </span>
            </a>
            <a class="shortcut" @click="loadShortCut([37,40])">
                                      <span>
                                        {{'orders.short.cut.3' | message}}
                                      </span>
            </a>
          </div>
          <div class="col3Row">
            <a class="shortcut" @click="loadShortCut([42,47])">
                                      <span>
                                        {{'orders.short.cut.4' | message}}
                                      </span>
            </a>
            <a class="shortcut" @click="loadShortCut([55,57])">
                                      <span>
                                        {{'orders.short.cut.5' | message}}
                                      </span>
            </a>
            <a class="shortcut" @click="loadShortCut([73,74])">
                                      <span>
                                          {{'orders.short.cut.6' | message}}
                                      </span>
            </a>
          </div>
          <div class="col1Row">
            <a class="shortcut" @click="loadShortCut([15,19])">
                                      <span>
                                          {{'orders.short.cut.7' | message}}
                                      </span>
            </a>
          </div>
          <div class="col2Row">
            <a class="shortcut" @click="loadShortCut([9,11])">
                                      <span>
                                         {{'orders.short.cut.8' | message}}
                                      </span>
            </a>
            <a class="shortcut" @click="loadShortCut([91,92])">
                                      <span>
                                          {{'orders.short.cut.9' | message}}
                                      </span>
            </a>
          </div>
        </div>
      </div>
      <div class="mobile-shortcut">
        <div class="shortcutBox">
          <a class="shortcut" @click="loadShortCut([26,27])">
                                      <span>{{'orders.short.cut.1' | message}}
                                      </span>
          </a>
          <a class="shortcut" @click="loadShortCut([1,2])">
                                      <span>
                                        {{'orders.short.cut.2' | message}}
                                      </span>
          </a>
          <a class="shortcut" @click="loadShortCut([37,40])">
                                      <span>
                                        {{'orders.short.cut.3' | message}}
                                      </span>
          </a>
          <a class="shortcut" @click="loadShortCut([42,47])">
                                      <span>
                                        {{'orders.short.cut.4' | message}}
                                      </span>
          </a>
          <a class="shortcut" @click="loadShortCut([55,57])">
                                      <span>
                                        {{'orders.short.cut.5' | message}}
                                      </span>
          </a>
          <a class="shortcut" @click="loadShortCut([73,74])">
                                      <span>
                                          {{'orders.short.cut.6' | message}}
                                      </span>
          </a>
          <a class="shortcut" @click="loadShortCut([15,19])">
                                      <span>
                                          {{'orders.short.cut.7' | message}}
                                      </span>
          </a>
          <a class="shortcut" @click="loadShortCut([9,11])">
                                      <span>
                                         {{'orders.short.cut.8' | message}}
                                      </span>
          </a>
          <a class="shortcut" @click="loadShortCut([91,92])">
                                      <span>
                                          {{'orders.short.cut.9' | message}}
                                      </span>
          </a>
        </div>
      </div>
      <div class="boxContentMiddleTitle">{{'orders.category.select.header' | message}}</div>
      <h5>{{'orders.category.select.desc' | message}}</h5>
      <div class="btnBox">
        <select class="select"
                :disabled="submitting"
                v-validate="'required'"
                v-for="(categoryLevel,index) in categoryLevels"
                @change="getCategories(categoryLevel.selectedCategoryId,index + 1)"
                v-model="categoryLevel.selectedCategoryId">
          <!--<option selected="selected"><strong>{{'common.choose' | message}}</strong></option>-->
          <option v-for="(category,cindex) in categoryLevel" :value="category.orderCategoryId">
            <strong v-if="language === 'fa'">{{category.titleFa}}</strong>
            <strong v-if="language === 'en'">{{category.titleEn}}</strong>
          </option>
        </select>
      </div>
      <textarea class="description"
                :disabled="submitting"
                :class="{'has-error': errors.has('addressSelect')}"
                id="orderDescription"
                name="orderDescription"
                :placeholder="'common.description' | message"
                v-validate="'required'"
                v-model="order.description">

      </textarea>
      <div class="address-radio-container" :class="{'has-error': errors.has('addressSelect')}">
        <span>{{'order.address' | message}}</span>
        <div v-for="(address,index) in userAddresses" class="address-radio-box">
          <input type="radio" name="addressSelect"
                 :value="address.addressId"
                 v-validate="'required'"
                 v-model="order.addressId">
          <label class="address-content">
            <span>{{address.cityFa}}</span>,
            <span>{{address.addressFa}}</span>
          </label>
        </div>
      </div>

      <span class="mid-title">{{'common.attachments' | message}}</span>
      <div class="row">
        <div class="col-xs-12 col-md-5 col-lg-5">
          <input type="file" multiple @change="fileChanged"/>
        </div>
        <div class="col-xs-12 col-md-5 col-lg-5">
          <ul class="attachments" v-if="file">
            <li>
              {{file.name}}
              <span class="delete-icon" @click="deleteFile"><i class="fa fa-times"></i></span>
            </li>
          </ul>
        </div>
      </div>

      <div class="btnBox noMargin">
        <a class="btn highlight" @click="submitOrder">{{'submit.order' | message}}</a>
      </div>
    </div>
  </div>
</template>

<script>
  import Lazy from '../../Lazy'
  import axios from 'axios'
  import { mapState } from 'vuex'

  export default {
    name: 'NewOrder',
    components: {
      Lazy
    },
    data () {
      return {
        loaded: true,
        submitting: false,
        order: {},
        categoryLevels: [],
        userAddresses: [],
        setLevelTrigger: 0,
        shortcut: [],
        file: null,
      }
    },
    created: function () {
//      $(function () {
//        $('.boxContentMain').jScrollPane();
//      });
      this.getCategories(null,0)
      this.loadAddresses()
    },
  computed: mapState([
    'language'
  ]),
    methods: {
      submitOrder: function () {
        var submitOrderUrl = '/s/order/submitrequest'

        this.$validator.validateAll().then((result) => {
          if (result) {
            var categoryId
            if (this.categoryLevels[this.categoryLevels.length - 1].selectedCategoryId) {
              categoryId = this.categoryLevels[this.categoryLevels.length - 1].selectedCategoryId
            } else {
              categoryId = this.categoryLevels[this.categoryLevels.length - 2].selectedCategoryId
            }
            this.submitting = true

            let fileName = null;
            const formData = new FormData();

            if (this.file) {
              fileName = this.file.name;
              formData.append('file', this.file)
            }

            formData.append('description', this.order.description)
            formData.append('categoryId', categoryId)
            formData.append('addressId', this.order.addressId)
            formData.append('uploadedFileName', fileName)

            axios.post(submitOrderUrl, formData)
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
          var msg = this.$options.filters.error('common.form.required.error')
          this.$toasted.error(msg)
        });
      },
      getCategories: function (categoryId,levelTrigger) {
        var categoriesUrl = '/s/order/category'
        this.submitting = true;
        axios.get(categoriesUrl, {
          params: {
            categoryId: categoryId
          }
        })
          .then(response => {
            this.categoryLevels = this.categoryLevels.splice(0,levelTrigger)
            if (response.data && response.data.length > 0) {
              this.categoryLevels.push(response.data)
            }
            this.submitting = false;
          })
          .catch(e => {
            this.submitting = false;
          })
      },
      loadAddresses: function () {
        var addressUrl = '/s/order/useraddresses'

        this.submitting = true
        this.loaded = false

        axios.get(addressUrl)
          .then(response => {
            this.submitting = false
            this.loaded = true
            this.userAddresses = response.data
          })
          .catch(e => {
            this.submitting = false
            this.loaded = true
          })
      },loadShortCut(shortcut) {
          this.shortcut = shortcut;
        this.setLevelTrigger = 0;
        this.categoryLevels = this.categoryLevels.splice(0,1);
        this.setCategory(this.setLevelTrigger)
      },
      setCategory(level) {
        var categoriesUrl = '/s/order/category'

        this.categoryLevels[level].selectedCategoryId = this.shortcut[level];

        axios.get(categoriesUrl, {
          params: {
            categoryId: this.shortcut[level]
          }
        })
          .then(response => {
            if (response.data && response.data.length > 0) {
              this.categoryLevels.push(response.data);
              this.setLevelTrigger++;
              this.setCategory(this.setLevelTrigger);
            }
          })
          .catch(e => {
          })
      },
      fileChanged(event) {
        if (event.target.files && event.target.files.length > 0) {
          this.file = event.target.files[0];
        }
      },
      deleteFile() {
          this.file = null;
      }
    }
  }
</script>
<style scoped>
  .select {
    float: right;
    margin: 5px;
  }

  .ltr .select {
    float: left;
    margin: 5px;
  }

  .address-radio-box {
    border: 1px solid black;
    border-radius: 5px 0 5px 0;
    margin: 10px;
    padding: 5px;
  }

  .address-content {
    padding: 5px 10px;
  }

  @media screen and (max-width: 900px) {
    .select {
      width: 95%;
      margin: 10px auto;
    }
  }
</style>
