<template>
  <div>
      <ul>
          <li v-for="(program, i) in programs" :key="i">
          <b>  {{ program.name }} </b>
          <br> {{ program.description }}
          <br> {{ program.channel.channelName }}
          </li>
      </ul>
  </div>
</template>

<script>

export default {
  name: 'SearchView',

  data () {
    return {
      programs: []
    }
  },

  async mounted () {
    console.log(this.$route.params.searchString)

    this.searchString = this.$route.params.searchString

    let programs = await fetch('/rest/programs/search/' + this.searchString)
    programs = await programs.json()
    this.programs = programs

    console.log(programs)
  },

  components: {
  }
}
</script>

<style>

</style>
