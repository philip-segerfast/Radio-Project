<template>
  <div id="programs-container">
    <div id="program-info">
      <ProgramInfo :program="program" />
    </div>
    <div id="program-list">
      <EpisodeList />
    </div>
  </div>
</template>

<script>
import EpisodeList from '../components/EpisodeList.vue'
import ProgramInfo from '../components/ProgramInfo'

export default {
  data () {
    return {
      program: null
    }
  },
  mounted () {
    // 1. Get program with ID of param.
    // 2. Set data variable to the program.
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
  flex-direction: column;
  align-items: center;
  background-color: tomato;
}

#program-info {
  width: 70%;
  height: min-content;
}

#program-list {
  height: 100%;
  width: 100%;
}
</style>
