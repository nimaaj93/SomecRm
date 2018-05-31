<template>
  <div class="rightSide">
    <div class="closeBtn" @click="closeMobileMenu"><span class="first"></span><span class="secound"></span></div>
    <ul class="mainMenu mobile-menu">
      <li>
        <router-link to="/orders" @click.native="closeMobileMenu">{{'menu.user.orders' | message}}</router-link>
      </li>
      <li>
        <router-link to="/neworder" @click.native="closeMobileMenu">{{'menu.user.new.order' | message}}</router-link>
      </li>
      <li>
        <router-link to="/messages" @click.native="closeMobileMenu">{{'menu.user.messages' | message}}</router-link>
        <span class="notification" v-show="notificationsCount > 0">{{notificationsCount}}</span>
      </li>
      <li>
        <router-link to="/profile" @click.native="closeMobileMenu">{{'menu.user.profile' | message}}</router-link>
      </li>
      <li>
        <router-link to="/transactions" @click.native="closeMobileMenu">{{'menu.user.transactions' | message}}</router-link>
      </li>
      <li>
        <a @click="logout" id="mobileExit">{{'common.logout' | message}}</a>
      </li>
    </ul>
    <ul class="mainMenu desktop-menu">
      <li>
        <router-link to="/orders">{{'menu.user.orders' | message}}</router-link>
      </li>
      <li>
        <router-link to="/neworder">{{'menu.user.new.order' | message}}</router-link>
      </li>
      <li>
        <router-link to="/messages">{{'menu.user.messages' | message}}</router-link>
        <span class="notification" v-show="notificationsCount > 0">{{notificationsCount}}</span>
      </li>
      <li>
        <router-link to="/profile">{{'menu.user.profile' | message}}</router-link>
      </li>
      <li>
        <router-link to="/transactions">{{'menu.user.transactions' | message}}</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
  export default {
    name: 'SideBar',
    props: {

    },
    methods: {
      logout: function () {
        window.eventBus.$emit('logout')
      },
      closeMobileMenu() {
        $(".rightSide").css('display', 'none');
      }
    },
    computed: {
      notificationsCount() {
          return this.$store.state.newMessagesCount + this.$store.state.newOffersCount
      }
    }
  }
</script>
<style>

</style>
