<template>
  <div id="tableaubyday-list-container" class="selectable">Tableau list
    <ul>
      <li v-for="(program, i) in programs" :key="i"> <b> {{ program.title }} </b>
        <br> {{ program.description }}
        <br> {{ program.starttimeutc }}
        </li>
    </ul>
  </div>
</template>

<script>
export default {

  name: 'TableauByDayList',

  data () {
    return {
      programs: []
    }
  },

  async mounted () {
    const channelId = this.$route.params.channelId

    try {
      const response = await fetch('/rest/programs/tableau/' + channelId)
      const programs = await response.json()
      this.programs = programs
      console.log(programs)
    } catch {
      alert('Kanalen eller datumet finns inte. Prova med något annat.')
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
