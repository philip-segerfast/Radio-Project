<template>
  <div>
    <div class="info-header-container" v-for="(program, i) in programs" :key="i" :value="program">
      <div class="header"> {{ program.name }} </div>
        <div class="left-box">
          <div class="description selectable">
           {{ program.description }}
        </div>
              <div class="icon-bar">
        <div class="share tmp-icon" v-on:click="copyToClipboard">
          Share
        </div>
              </div>
      </div>
      <div class="right-box">
        <img :src='program.programImage'>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['program'],

  data () {
    return {
      programs: []
    }
  },

  async mounted () {
    const response = await fetch('/rest/program-favourites')
    const programs = await response.json()
    this.programs = programs
    console.log(programs)
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

<style>

</style>
