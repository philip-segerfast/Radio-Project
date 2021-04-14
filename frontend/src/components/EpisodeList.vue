<template>
  <div id="episode-list-container" class="selectable">Episode list
    <ul>
      <li v-for="(episode, i) in episodes" :key="i"> <b> {{ episode.name }} </b>
        <br> {{ episode.description }}
        <br> {{ episode.publishdateutc }}
        <br> {{ episode.url }} </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: ['program'],
  name: 'EpisodeList',

  data () {
    return {
      episodes: []
    }
  },

  async mounted () {
    const programId = this.$route.params.programId
    try {
      const response = await fetch('/rest/episodes/' + programId)
      const episodes = await response.json()
      this.episodes = episodes
      console.log(episodes)
    } catch {
      alert('Ogiltigt program. Prova med ett annat ID.')
    }
  }
}
</script>

<style scoped>
#episode-list-container {
  border-radius: inherit;
  height: 100%;
  background-color: var(--background-darker);
  border: 1px solid black;
  padding: var(--medium-padding);
}
</style>
