<template>
  <div>
      <ul>
          <li v-for="(program, i) in programs" :key="i">
          <b id="search-results" v-on:click="this.$router.push('/program/' + program.id)">  {{ program.name }} </b>
          <br> {{ program.description }}
          <br> {{ program.channel.name }}
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
#search-results {
  cursor: pointer;
  text-decoration: underline;
}
</style>
