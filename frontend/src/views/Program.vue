<template>
  <div class="radio-page-container">
    <div class="radio-topper-container">
      <ProgramInfo :program="program" />
    </div>
    <div class="radio-items-container">
          <NextEpisode />
      <EpisodeList :program="program"/>
    </div>
  </div>
</template>

<script>
import ProgramInfo from '../components/ProgramInfo'
import NextEpisode from '../components/NextEpisode.vue'
import EpisodeList from '../components/EpisodeList.vue'

export default {
  data () {
    return {
      program: ''
    }
  },

  async mounted () {
    this.programId = this.$route.params.programId
    try {
      const response = await fetch('/rest/programs/' + this.programId)
      this.program = await response.json()
    } catch {
      alert('Ogiltigt program. Prova med ett annat ID.')
      this.program = {
        name: 'Otillgängligt',
        description: 'Otillgängligt'
      }
    }
  },

  components: {
    ProgramInfo,
    NextEpisode,
    EpisodeList
  }
}
</script>
