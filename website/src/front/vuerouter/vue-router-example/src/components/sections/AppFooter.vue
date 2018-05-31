<template>
  <div class="boxFooter">
    <footer>
      <div class="desktop-footer">
        <ul class="footer-links right">
          <li><a href="http://mataconcierge.com/%d8%b4%d8%b1%d8%a7%db%8c%d8%b7-%d8%ae%d8%af%d9%85%d8%a7%d8%aa/">
            {{'footer.terms.of.service' | message}}</a>
          </li>
          <li><a href="http://mataconcierge.com/%d8%ad%d8%b1%db%8c%d9%85-%d8%b4%d8%ae%d8%b5%db%8c/">
            {{'footer.privacy' | message}}</a>
          </li>
          <li><a href="http://mataconcierge.com/%d8%b3%d9%88%d8%a7%d9%84%d8%a7%d8%aa-%d9%85%d8%aa%d8%af%d8%a7%d9%88%d9%84/">
            {{'footer.faq' | message}}</a>
          </li>
          <li><a href="http://mataconcierge.com/%d8%aa%d9%85%d8%a7%d8%b3-%d8%a8%d8%a7-%d9%85%d8%a7/">
            {{'footer.contact.us' | message}}</a>
          </li>
          <li>
            <a @click="changeLang('en')" v-if="language === 'fa'">En</a>
            <a @click="changeLang('fa')" v-if="language === 'en'">فا</a>
          </li>
        </ul>
        <div class="copyright left">{{'footer.copyright' | message}}</div>
      </div>
      <div class="mobile-footer">

        <ul>
          <li style="font-size: 18px; padding-bottom: 20px">
            <a @click="changeLang('en')" v-if="language === 'fa'">En</a>
            <a @click="changeLang('fa')" v-if="language === 'en'">فا</a>
          </li>
          <li><a href="http://mataconcierge.com/%d8%b4%d8%b1%d8%a7%db%8c%d8%b7-%d8%ae%d8%af%d9%85%d8%a7%d8%aa/">
            {{'footer.terms.of.service' | message}}</a>
          </li>
          <li><a href="http://mataconcierge.com/%d8%ad%d8%b1%db%8c%d9%85-%d8%b4%d8%ae%d8%b5%db%8c/">
            {{'footer.privacy' | message}}</a>
          </li>
          <li><a href="http://mataconcierge.com/%d8%b3%d9%88%d8%a7%d9%84%d8%a7%d8%aa-%d9%85%d8%aa%d8%af%d8%a7%d9%88%d9%84/">
            {{'footer.faq' | message}}</a>
          </li>
          <li><a href="http://mataconcierge.com/%d8%aa%d9%85%d8%a7%d8%b3-%d8%a8%d8%a7-%d9%85%d8%a7/">
            {{'footer.contact.us' | message}}</a>
          </li>
        </ul>
        <div class="copyright" style="text-align: center">{{'footer.copyright' | message}}</div>
      </div>
    </footer>
  </div>
</template>
<script>
  import { mapState } from 'vuex'

  export default {
    name: 'AppFooter',
    methods: {
      changeLang(lang) {
        this.$store.commit('changeLanguage',lang)
      }
    },
    computed: mapState([
      'language'
    ])
  }
</script>
