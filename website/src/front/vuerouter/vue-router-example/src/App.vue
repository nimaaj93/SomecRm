<template>
  <div id="app" :class="{'ltr' : language === 'en'}">
    <div class="container">
      <div class="mainBox" :class="{'members' : authenticated}">
        <side-bar v-if="authenticated"></side-bar>
        <div class="leftSide">
          <div class="boxHeader">
            <div class="logo" v-if="authenticated"></div>
            <top-menu v-if="authenticated"></top-menu>
          </div>
          <router-view></router-view>
        </div>
      </div>
    </div>
    <app-footer></app-footer>
  </div>
</template>

<script>
  import SideBar from './components/sections/SideBar'
  import TopMenu from './components/sections/TopMenu'
  import AppFooter from './components/sections/AppFooter'
  import { mapState } from 'vuex'

export default {
  name: 'app',
  components: {
    SideBar,
    TopMenu,
    AppFooter
  },
  props: {
      authenticated: {
        type: Boolean,
        required: true
      }
  },
  methods: {
      logout: function () {
          window.eventBus.$emit('logout')
      }
  },
  computed: mapState([
    'language'
  ])
}
</script>
