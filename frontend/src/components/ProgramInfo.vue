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
        <div v-if="isLoggedIn">
          <div v-if="!program.isFavourite" class="tmp-icon" v-on:click="this.$emit('favourite')">
            Favourite
          </div>
          <div v-else class="tmp-icon" v-on:click="this.$emit('unFavourite')">
            Unfavourite
          </div>
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

  mounted () {
    console.log(this.program)
  },

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
  },

  computed: {
    isLoggedIn () {
      return this.$store.state.loggedInUser != null
    }
  }
}
</script>
