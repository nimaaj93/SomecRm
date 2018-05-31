<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
          <span class="col-md-2">
          </span>
        <span class="col-md-8">
            <h2>
              <i class="fa fa-hourglass-end" aria-hidden="true"></i>
              {{ 'sidebar.menu.near.expire.users' | message }}
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
        <div class="col-md-1">
        </div>
        <div class="col-md-10">

          <div class="row">
            <lazy :loaded="loaded">
              <div class="col-xs-12">
                <div class="box">
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
                          <th>{{'common.expire.date' | message}}</th>
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
                            {{user.expireDate}}
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
  import Lazy from '../../Lazy';
  import axios from 'axios';

  export default {
    components: {Lazy},
    name: 'NearExpireUsers',
    data () {
      return {
        submitting: false,
        loaded: true,
        pages: 1,
        searchParams: {
          size: 10,
          page: 1
        },
        users: []
      }
    },
    created: function () {
      this.loadUsers()
    },
    methods: {
      searchUsers: function () {
        this.searchParams.page = 1
        this.loadUsers()
      },
      loadUsers: function () {
        var countUrl = '/s/userexpire/count'
        var listUrl = '/s/userexpire/list'

        this.loaded = false
        this.submitting = true

        axios.get(countUrl)
          .then(response => {
            this.pages = parseInt(Math.ceil(response.data / this.searchParams.size))
          })
          .catch(e => {
          })

        axios.get(listUrl, {
          params: {
            size: this.searchParams.size,
            page: this.searchParams.page - 1
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
      }
    }
  }
</script>
