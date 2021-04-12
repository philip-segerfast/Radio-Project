<template>
  <div id="programs-container">
    <div id="program-info">
      <ProgramInfo :program="program" />
    </div>
    <div id="program-list">
      <EpisodeList :episode="program"/>
    </div>
  </div>
</template>

<script>
import EpisodeList from '../components/EpisodeList.vue'
import ProgramInfo from '../components/ProgramInfo'

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
    EpisodeList,
    ProgramInfo
  }
}
</script>

<style scoped>
#programs-container {
  display: flex;
  width: 100%;
  flex-direction: column;
  align-items: center;
  background-color: tomato;
}

#program-info {
  width: 100%;
  height: min-content;
  margin-bottom: var(--medium-padding);
}

#program-list {
  height: 100%;
  width: 100%;
}
</style>
