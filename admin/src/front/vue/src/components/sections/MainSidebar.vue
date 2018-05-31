<template>
    <aside class="main-sidebar">

      <!-- sidebar: style can be found in sidebar.less -->
      <section class="sidebar">

        <div class="sidebar-title">
          <b>{{'mata.concierge.en.title' | message}}</b>
        </div>


        <!-- Sidebar Menu -->
        <ul class="sidebar-menu">
          <li class="header"><h5>{{'sidebar.menu.header' | message}}</h5></li>
          <!-- Optionally, you can add icons to the links -->
          <li>
            <router-link to="/home">
              <i class="fa fa-home"></i>
              <span>{{'sidebar.menu.home' | message}}</span>
            </router-link>
          </li>
          <li>
            <router-link to="/signuprequests">
              <i class="fa fa-paper-plane" aria-hidden="true"></i>
              <span>{{'sidebar.menu.signup.requests' | message}}</span>
            </router-link>
          </li>
          <li>
            <router-link to="/newemail">
              <i class="fa fa-envelope-o" aria-hidden="true"></i>
              <span>{{'sidebar.menu.transactions.newemail' | message}}</span>
            </router-link>
          </li>
          <li class="treeview">
            <a href="#"><i class="fa fa-link"></i> <span>{{ 'sidebar.menu.order.management' | message }}</span> <i class="fa fa-angle-left pull-right"></i></a>
            <ul class="treeview-menu">
              <router-link tag="li" to="/orders">

                <a><i class="fa fa-list" aria-hidden="true"></i>
                  {{ 'sidebar.menu.order.management.list' | message }}</a>
              </router-link>
              <router-link tag="li" to="/neworder">
                <a><i class="fa fa-indent" aria-hidden="true"></i>
                  {{ 'sidebar.menu.order.management.submit' | message }}</a>
              </router-link>
            </ul>
          </li>
          <li class="treeview">
            <a href="#"><i class="fa fa-link"></i> <span>{{ 'sidebar.menu.user.management' | message }}</span> <i class="fa fa-angle-left pull-right"></i></a>
            <ul class="treeview-menu">
              <router-link tag="li" to="/userregistration/dataentry">

                <a><i class="fa fa-user-plus" aria-hidden="true"></i>
                  {{ 'sidebar.menu.user.management.register' | message }}</a>
              </router-link>
              <router-link tag="li" to="/usermanagement/list">
                <a><i class="fa fa-users" aria-hidden="true"></i>
                  {{ 'sidebar.menu.user.management.list' | message }}</a>
              </router-link>
              <router-link tag="li" to="/usermanagement/nearexpire">
                <a><i class="fa fa-hourglass-end" aria-hidden="true"></i>
                  {{ 'sidebar.menu.near.expire.users' | message }}</a>
              </router-link>
              <router-link tag="li" to="/corporatecustomer/registration">
                <a><i class="fa fa-industry" aria-hidden="true"></i>
                  {{ 'sidebar.menu.corporate.user.registration' | message }}</a>
              </router-link>
              <router-link tag="li" to="/corporatecustomer/list">
                <a><i class="fa fa-list" aria-hidden="true"></i>
                  {{ 'sidebar.menu.corporate.user.list' | message }}</a>
              </router-link>
            </ul>
          </li>
          <li class="treeview" v-if="token.authorities.length > 1">
            <a href="#"><i class="fa fa-link"></i> <span>{{ 'sidebar.menu.admin.user.management' | message }}</span> <i class="fa fa-angle-left pull-right"></i></a>
            <ul class="treeview-menu">
              <router-link tag="li" to="/adminregistration/dataentry">
                <a><i class="fa fa-user-plus" aria-hidden="true"></i>
                  {{ 'sidebar.menu.admin.user.registration' | message }}</a>
              </router-link>
              <router-link tag="li" to="/adminmanagement/list">
                <a><i class="fa fa-users" aria-hidden="true"></i>
                  {{ 'sidebar.menu.admin.user.list' | message }}</a>
              </router-link>
            </ul>
          </li>
          <li class="treeview">
            <a href="#"><i class="fa fa-link"></i> <span>{{ 'sidebar.menu.promotions.management' | message }}</span> <i class="fa fa-angle-left pull-right"></i></a>
            <ul class="treeview-menu">
              <router-link tag="li" to="/promotions/newpromotion">
                <a><i class="fa fa-diamond" aria-hidden="true"></i>
                  {{ 'sidebar.menu.promotions.submit' | message }}</a>
              </router-link>
              <router-link tag="li" to="/promotions/list">
                <a><i class="fa fa-list" aria-hidden="true"></i>
                  {{ 'sidebar.menu.promotions.list' | message }}</a>
              </router-link>
            </ul>
          </li>
          <li class="treeview">
            <a href="#"><i class="fa fa-link"></i> <span>{{ 'sidebar.menu.transactions.management' | message }}</span> <i class="fa fa-angle-left pull-right"></i></a>
            <ul class="treeview-menu">
              <router-link tag="li" to="/transactions/new">
                <a><i class="fa fa-money" aria-hidden="true"></i>
                  {{ 'sidebar.menu.transactions.submit' | message }}</a>
              </router-link>
              <router-link tag="li" to="/transactions/list">
                <a><i class="fa fa-list" aria-hidden="true"></i>
                  {{ 'sidebar.menu.transactions.list' | message }}</a>
              </router-link>
            </ul>
          </li>
        </ul>
      </section>
    </aside>
</template>

<script>
  export default {
    name: 'MainSidebar',
    props: {
      token: {
        required: true
      }
    },
    data () {
      return {

      }
    }
  }
</script>
