<template>
    <footer class="main-footer">
      <!-- To the right -->
      <div class="pull-right hidden-xs">
      </div>
      <!-- Default to the left -->
      <div class="pull-left">
          {{'copyright.text' | message}}
      </div>
    </footer>
</template>

<script>
  export default {
    name: 'AppFooter',
    data () {
      return {

      }
    }
  }
</script>
