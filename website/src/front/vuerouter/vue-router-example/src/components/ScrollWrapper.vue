<template>
  <div>
    <slot class="scroll-wrapper">

    </slot>
  </div>
</template>
<script>
  export default {
    name: 'ScrollWrapper',
    created() {
      $(function () {
        $('.scroll-wrapper').jScrollPane();
      });
    }
  }
</script>
