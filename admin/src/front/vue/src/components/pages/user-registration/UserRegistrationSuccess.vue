<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
        <span class="col-md-2">
        </span>
        <span class="col-md-8">
          <h2>
            <i class="fa fa-user-plus" aria-hidden="true"></i>
            {{ 'sidebar.menu.user.management.register' | message }}
            <!--<small>Preview</small>-->
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
        <div class="col-md-8">
          <div class="box box-success">
            <div class="box-header with-border">
              <h3 class="box-title">{{'common.customerInfo' | message}}</h3>
            </div>
            <div class="box-body table-wrapper">
              <lazy :loaded="loaded">
                <div class="table-wrapper">
                <table id="resultTable" class="table table-bordered table-striped">
                  <tbody>
                    <tr>
                      <td>{{'common.firstName' | message}}</td>
                      <td>{{ registeredUser.firstNameFa }}</td>
                    </tr>
                    <tr>
                      <td>{{'common.lastName' | message}}</td>
                      <td>{{ registeredUser.lastNameFa }}</td>
                    </tr>
                    <tr>
                      <td>{{'common.firstName.en' | message}}</td>
                      <td>{{ registeredUser.firstNameEn }}</td>
                    </tr>
                    <tr>
                      <td>{{'common.lastName.en' | message}}</td>
                      <td>{{ registeredUser.lastNameEn }}</td>
                    </tr>
                    <tr>
                      <td>{{'common.nationalCode' | message}}</td>
                      <td>{{ registeredUser.nationalCode }}</td>
                    </tr>
                    <tr>
                      <td>{{'common.phoneNumber' | message}}</td>
                      <td>{{ registeredUser.phoneNumber }}</td>
                    </tr>
                    <tr>
                      <td>{{'common.email' | message}}</td>
                      <td>{{ registeredUser.email }}</td>
                    </tr>
                    <tr>
                      <td>{{'common.dateOfBirth' | message}}</td>
                      <td>{{ registeredUser.birthDate }}</td>
                    </tr>
                  </tbody>
                </table>
                </div>
              </lazy>

              <div class="row button-container">
                <div class="col-xs-4"></div>
                <div class="col-xs-4">
                  <router-link tag="button" to="/home" class="btn btn-primary pull-right">
                    <i class="fa fa-home"></i>
                    {{'sidebar.menu.home' | message}}
                  </router-link>
                  <router-link tag="button" to="/userregistration/dataentry" class="btn btn-success pull-right">
                    <i class="fa fa-angle-double-left" aria-hidden="true"></i>
                    {{'common.back' | message}}
                  </router-link>
                </div>
                <div class="col-xs-4"></div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-2">
        </div>
      </div>
    </section>
  </div>
</template>

<script>
  import Lazy from '../../Lazy';
  import axios from 'axios';

  export default {
    name: 'UserRegistrationSuccess',
    components: {
      Lazy
    },
    data () {
      return {
        loaded: false,
        registeredUser: {

        }
      }
    },
    created: function () {
      this.loadRegisteredUserData()
    },
    methods: {
      loadRegisteredUserData: function () {
        console.log('nationalCode: ' + this.$route.params.nationalCode)
        var url = '/s/user/userinfo';
        axios.get(url, {
          params: {
            nationalCode: this.$route.params.nationalCode
          }
        })
          .then(response => {
            this.registeredUser = response.data
            this.loaded = true
          })
          .catch(e => {
            this.loaded = true
          })
      }
    }
  }
</script>

<style scoped>
  .button-container button {
    margin: 15px;
  }
</style>
