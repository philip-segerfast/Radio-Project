<template>
  <div class="info-header-container">
    <div class="header grid-item"> {{ channel.name }} </div>
    <div class="left-box grid-item">
      <div class="description selectable">
        {{ channel.tagline }}
      </div>
      <div class="icon-bar">
        <div class="tmp-icon" v-on:click="goToTableau">
          Go to tableau
        </div>
        <div class="tmp-icon" v-on:click="copyToClipboard">
          Share
        </div>
        <div class="tmp-icon">
          Favourite
        </div>
      </div>
    </div>
    <div class="right-box">
      <img :src="channel.imageUrl">
    </div>
  </div>
</template>

<script>
export default {
  props: ['channel'],
  data () {
    return {
      program: ''
    }
  },
  methods: {
    goToTableau () {
      this.$router.push('/tableau/' + this.channel.id)
    },
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
