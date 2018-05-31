<template>
    <body class="skin-mata" v-cloak>
      <div class="wrapper">
          <app-header v-if="authenticated" :token="token"></app-header>
          <main-sidebar v-if="authenticated" :token="token"></main-sidebar>
          <router-view class="router-view"></router-view>
          <app-footer v-if="authenticated"></app-footer>
          <second-sidebar v-if="authenticated"></second-sidebar>
        <div v-if="authenticated" class="control-sidebar-bg"></div>
      </div>
  </body>
</template>

<script>

  import AppHeader from './components/sections/AppHeader'
  import AppFooter from './components/sections/AppFooter'
  import MainSidebar from './components/sections/MainSidebar'
  import SecondSidebar from './components/sections/SecondSidebar'

export default {
  name: 'app',
  props: {
      authenticated: {
        type: Boolean,
        required: true
      },
      token: {
        required: true
      }
  },
  methods: {
      logout: function () {
          window.eventBus.$emit('logout')
      }
  },
  components: {
    AppHeader,
    AppFooter,
    MainSidebar,
    SecondSidebar
  }
}
</script>
