<template>
  <div class="info-header-container">
    <div class="header"> {{ program.name }} </div>
    <div class="left-box">
      <div class="description selectable">
        {{ program.description }}
      </div>
      <div class="icon-bar">
        <div class="share tmp-icon" v-on:click="copyToClipboard">
          Share
        </div>
        <div class="favourite tmp-icon">
          Favourite
        </div>
      </div>
    </div>
    <div class="right-box">
      <img :src='program.programImage'>
    </div>
  </div>
</template>

<script>
export default {
  props: ['program'],

  methods: {
    copyToClipboard (text) {
      var inputc = document.body.appendChild(document.createElement('input'))
      inputc.value = window.location.href
      inputc.focus()
      inputc.select()
      document.execCommand('copy')
      inputc.parentNode.removeChild(inputc)
      alert('URL Copied.')
    }
  }
}
</script>
