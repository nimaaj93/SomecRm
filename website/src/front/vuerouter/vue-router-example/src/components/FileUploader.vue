<template>
  <div class="example-simple">
      <input type="file" multiple @change="fileChanged"/>
      <button @click="upload">{{common.upload}}</button>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    data() {
      return {
        file: null,
      }
    },
    methods: {
      fileChanged(event) {
        let uploadedFile = event.target.files[0];
        if (uploadedFile) {
            this.file = uploadedFile;
        }
      },
      upload() {
          let url = 's/fileupload/upload';
          const formData = new FormData()
          formData.append('file', this.file)
          axios.post(url, formData)
            .then(response => {
            })
            .catch(e => {
            })
      }
    }
  }
</script>
