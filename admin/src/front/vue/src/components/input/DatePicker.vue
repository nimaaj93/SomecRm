<template>
  <input type="text"
         class="form-control"
         :v-mask="vMask"
         :maxlength="maxlength"
         :v-model="v-model"/>
</template>

<script>
  export default {
    name: 'DatePicker',
    props: {
      vMask: {
        type: String,
        default: '####/##/##'
      },
      maxlength: {
        type: Number,
        default: 10
      },
      'v-model': {
        required: true
      }
    }
  }
</script>

<style scoped>

</style>
