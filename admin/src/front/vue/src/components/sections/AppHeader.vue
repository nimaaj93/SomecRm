<template>
    <header class="main-header">

      <!-- Logo -->
      <router-link to="/home" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>M</b>ata</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg">
          <img src="../../assets/logo.png"/>
        </span>
      </router-link>

      <!-- Header Navbar -->
      <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <!--<a class="sidebar-toggle" data-toggle="offcanvas" role="button">-->
          <!--<span class="sr-only">Toggle navigation</span>-->
        <!--</a>-->

        <a class="mobile-menu-toggle pull-right" @click="toggleMenu">
          <i class="fa fa-list" aria-hidden="true"></i>
        </a>

        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu">
          <ul class="nav navbar-nav">
            <!-- Messages: style can be found in dropdown.less-->
            <!--<li class="dropdown messages-menu">-->
              <!--&lt;!&ndash; Menu toggle button &ndash;&gt;-->
              <!--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                <!--<i class="fa fa-envelope-o"></i>-->
                <!--<span class="label label-success">4</span>-->
              <!--</a>-->
              <!--<ul class="dropdown-menu">-->
                <!--<li class="header">You have 4 messages</li>-->
                <!--<li>-->
                  <!--&lt;!&ndash; inner menu: contains the messages &ndash;&gt;-->
                  <!--<ul class="menu">-->
                    <!--<li>&lt;!&ndash; start message &ndash;&gt;-->
                      <!--<a href="#">-->
                        <!--<div class="pull-left">-->
                          <!--&lt;!&ndash; User Image &ndash;&gt;-->
                          <!--<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">-->
                        <!--</div>-->
                        <!--&lt;!&ndash; Message title and timestamp &ndash;&gt;-->
                        <!--<h4>-->
                          <!--Support Team-->
                          <!--<small><i class="fa fa-clock-o"></i> 5 mins</small>-->
                        <!--</h4>-->
                        <!--&lt;!&ndash; The message &ndash;&gt;-->
                        <!--<p>Why not buy a new awesome theme?</p>-->
                      <!--</a>-->
                    <!--</li>&lt;!&ndash; end message &ndash;&gt;-->
                  <!--</ul>&lt;!&ndash; /.menu &ndash;&gt;-->
                <!--</li>-->
                <!--<li class="footer"><a href="#">See All Messages</a></li>-->
              <!--</ul>-->
            <!--</li>&lt;!&ndash; /.messages-menu &ndash;&gt;-->

            <!--&lt;!&ndash; Notifications Menu &ndash;&gt;-->
            <!--<li class="dropdown notifications-menu">-->
              <!--&lt;!&ndash; Menu toggle button &ndash;&gt;-->
              <!--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                <!--<i class="fa fa-bell-o"></i>-->
                <!--<span class="label label-warning">10</span>-->
              <!--</a>-->
              <!--<ul class="dropdown-menu">-->
                <!--<li class="header">You have 10 notifications</li>-->
                <!--<li>-->
                  <!--&lt;!&ndash; Inner Menu: contains the notifications &ndash;&gt;-->
                  <!--<ul class="menu">-->
                    <!--<li>&lt;!&ndash; start notification &ndash;&gt;-->
                      <!--<a href="#">-->
                        <!--<i class="fa fa-users text-aqua"></i> 5 new members joined today-->
                      <!--</a>-->
                    <!--</li>&lt;!&ndash; end notification &ndash;&gt;-->
                  <!--</ul>-->
                <!--</li>-->
                <!--<li class="footer"><a href="#">View all</a></li>-->
              <!--</ul>-->
            <!--</li>-->
            <!--&lt;!&ndash; Tasks Menu &ndash;&gt;-->
            <!--<li class="dropdown tasks-menu">-->
              <!--&lt;!&ndash; Menu Toggle Button &ndash;&gt;-->
              <!--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                <!--<i class="fa fa-flag-o"></i>-->
                <!--<span class="label label-danger">9</span>-->
              <!--</a>-->
              <!--<ul class="dropdown-menu">-->
                <!--<li class="header">You have 9 tasks</li>-->
                <!--<li>-->
                  <!--&lt;!&ndash; Inner menu: contains the tasks &ndash;&gt;-->
                  <!--<ul class="menu">-->
                    <!--<li>&lt;!&ndash; Task item &ndash;&gt;-->
                      <!--<a href="#">-->
                        <!--&lt;!&ndash; Task title and progress text &ndash;&gt;-->
                        <!--<h3>-->
                          <!--Design some buttons-->
                          <!--<small class="pull-right">20%</small>-->
                        <!--</h3>-->
                        <!--&lt;!&ndash; The progress bar &ndash;&gt;-->
                        <!--<div class="progress xs">-->
                          <!--&lt;!&ndash; Change the css width attribute to simulate progress &ndash;&gt;-->
                          <!--<div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar"-->
                               <!--aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">-->
                            <!--<span class="sr-only">20% Complete</span>-->
                          <!--</div>-->
                        <!--</div>-->
                      <!--</a>-->
                    <!--</li>&lt;!&ndash; end task item &ndash;&gt;-->
                  <!--</ul>-->
                <!--</li>-->
                <!--<li class="footer">-->
                  <!--<a href="#">View all tasks</a>-->
                <!--</li>-->
              <!--</ul>-->
            <!--</li>-->

            <li>
              <router-link to="/mailbox">
                <i v-if="this.$route.path != '/mailbox'" class="fa fa-envelope-o" aria-hidden="true"></i>
                <i v-else="" class="fa fa-envelope-open-o" aria-hidden="true"></i>
              </router-link>
            </li>

            <!-- User Account Menu -->
            <li class="dropdown user user-menu">
              <!-- Menu Toggle Button -->
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <!--<img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">-->
                <span><b>{{token.userinfo.firstNameFa}} {{token.userinfo.lastNameFa}}</b></span>
              </a>
              <ul class="dropdown-menu">
                <!-- The user image in the menu -->
                <li class="user-header">
                  <!--<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">-->
                  <p>
                    <b>{{token.userinfo.firstNameFa}} {{token.userinfo.lastNameFa}}</b>
                    <small v-if="token.authorities.length > 1">{{'role.super.admin' | message}}</small>
                    <small v-else>{{'role.admin' | message}}</small>
                  </p>
                </li>
                <!-- Menu Body -->
                <!--<li class="user-body">-->
                  <!--<div class="col-xs-4 text-center">-->
                    <!--<a href="#">Followers</a>-->
                  <!--</div>-->
                  <!--<div class="col-xs-4 text-center">-->
                    <!--<a href="#">Sales</a>-->
                  <!--</div>-->
                  <!--<div class="col-xs-4 text-center">-->
                    <!--<a href="#">Friends</a>-->
                  <!--</div>-->
                <!--</li>-->
                <!-- Menu Footer-->
                <li class="user-footer">
                  <!--<div class="pull-left">-->
                    <!--<a class="btn btn-default btn-flat">-->
                      <!--{{'common.profile' | message}}-->
                      <!--<i class="fa fa-id-card" aria-hidden="true"></i>-->
                    <!--</a>-->
                  <!--</div>-->
                  <div>
                    <a @click="logout" class="btn btn-default btn-flat">
                      {{'login.signout' | message}}
                      <i class="fa fa-sign-out" aria-hidden="true"></i>
                    </a>
                  </div>
                </li>
              </ul>
            </li>
            <!-- Control Sidebar Toggle Button -->
            <li style="min-width: 50px">
              <!--<a data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>-->
            </li>
          </ul>
        </div>
      </nav>
    </header>
</template>

<script>
  export default {
    name: 'AppHeader',
    props: {
      token: {
        required: true
      }
    },
    data () {
      return {

      }
    },
    methods: {
      logout: function () {
        window.eventBus.$emit('logout')
      },
      toggleMenu() {
          let currentStyle = document.getElementsByClassName("main-sidebar")[0].style.display;

          if (currentStyle === "block") {
            document.getElementsByClassName("main-sidebar")[0].style.display = "none";
          } else {
            document.getElementsByClassName("main-sidebar")[0].style.display = "block";
          }
      }
    }
  }
</script>
<style scoped>
  .logo-lg img {
    display: block;
    margin: auto;
    max-width: 40px;
    height: auto;
  }
</style>
