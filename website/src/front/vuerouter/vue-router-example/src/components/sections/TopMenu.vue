<template>
  <div class="topMenu">
    <a @click="logout" class="right exitBtn">{{'common.logout' | message}}</a>
    <div class="mobileMenu" @click="showMobileMenu"><span></span><span></span><span></span></div>
    <router-link class="right btn newOrderBtn" to="/neworder">
      {{'top.menu.new.order' | message}}
    </router-link>
    <div class="right user-info-panel">
      <span v-if="language === 'fa'">{{token.userinfo.firstNameFa}} {{token.userinfo.lastNameFa}}</span>
      <span v-if="language === 'en'">{{token.userinfo.firstNameEn}} {{token.userinfo.lastNameEn}}</span>
      <br/>
      <span>{{token.userinfo.nationalCode}}</span>
    </div>
    <div class="left lang-link">
      <a @click="changeLang('en')" v-if="language === 'fa'">En</a>
      <a @click="changeLang('fa')" v-if="language === 'en'">فا</a>
    </div>
  </div>
</template>
<script>
  import { mapState } from 'vuex'

  export default {
    name: 'TopMenu',
    props: {

    },
    methods: {
      logout: function () {
        window.eventBus.$emit('logout')
      },
      showMobileMenu() {
        $(".rightSide").css('display','block');
      },
      changeLang(lang) {
          this.$store.commit('changeLanguage',lang)
      }
    },
    computed: mapState([
      'token',
      'language'
    ])
  }
</script>
