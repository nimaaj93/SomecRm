<template>
  <div>
      <lazy :loaded="loaded">
        <div>
          <span v-for="category in orderCategories">
            {{ category.id }} - {{ category.title }}
          </span>
        </div>
      </lazy>
  </div>
</template>

<script>
  import Lazy from './Lazy';
  import axios from 'axios';

  export default {
    name: 'A',
    data () {
      return {
        loaded: false,
        orderCategories: []
      }
    },
    components: {
      Lazy
    },
    created() {
      this.loaded = false;
      axios.get(`/s/order/list`)
        .then(response => {
          this.orderCategories = response.data
          this.loaded = true;
        })
        .catch(e => {
          this.loaded = true;
        })
    }
  }
</script>
