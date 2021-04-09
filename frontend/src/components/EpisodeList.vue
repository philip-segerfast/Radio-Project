<template>
  <div id="episode-list-container">Episode list
    <ol>
      <li> {{ id }} </li>
    </ol>
  </div>
</template>

<script>
export default {
  name: 'EpisodeList',

  data () {
    return {
      id: ''
    }
  },

  async mounted () {
    console.log(this.$route.params.id)

    this.id = this.$route.params.id

    let program = await fetch('/rest/episodes/' + this.id)
    program = await program.json()

    console.log(program)
  }
}
</script>

<style scoped>
#episode-list-container {
  border: 1px solid black;
  height: 100%;
}
</style>
