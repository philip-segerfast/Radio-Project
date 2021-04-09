<template>
  <div id="episode-list-container">Episode list
    <ol>
      <li v-for="(episode, i) in episodes" :key="i"> {{  }} </li>
    </ol>
  </div>
</template>

<script>
export default {
  props: ['program'],

  name: 'EpisodeList',

  data () {
    return {
      id: null,
      episode: '',
      episodes: []
    }
  },

  async mounted () {
    const programId = this.$route.params.programId
    try {
      const response = await fetch('/rest/episodes/' + programId)
      const episode = await response.json()
      console.log(episode)
    } catch {
      alert('Ogiltigt program. Prova med ett annat ID.')
    }
  }
}
</script>

<style scoped>
#episode-list-container {
  border: 1px solid black;
  height: 100%;
}
</style>
