<template>
  <div class="lazy-container">
      <slot v-if="loaded" class="lazy-content"></slot>
      <div v-else class="loading-gif-container">
        <img src="../assets/loading.gif"/>
      </div>
  </div>
</template>

<script>
  export default {
    name: 'Lazy',
    props: {
      loaded: {
        type: Boolean,
        required: true
      }
    }
  }
</script>

<style scoped>
  .loading-gif-container img {
    top: 100px;
    position: absolute;
    margin-left: auto;
    margin-right: auto;
    left: 0;
    right: 0;
  }

  .lazy-container {
      width: 100%;
  }
</style>

