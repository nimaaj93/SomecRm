<template>
  <div :class="{'submit-container' : submitting}">
    <div v-if="submitting" class="cp-spinner cp-bubble spinner"></div>
    <slot></slot>
  </div>
</template>

<script>
  export default {
    name: 'SubmitWrapper',
    props: {
      submitting: {
        type: Boolean,
        required: true
      }
    }
  }
</script>

<style scoped>

  .spinner {
    position: absolute;
    margin: auto;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    z-index: 1001;
  }
</style>

