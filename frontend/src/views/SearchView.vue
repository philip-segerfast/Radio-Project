<template>
  <div>
      <input type="text" placeholder="Sök..." v-model="searchString"/>
      <button> Search </button>
      <p> {{ searchString }} </p>
      <ol v-for="program in getPrograms" :key="program">
          <li> {{ program.name }} </li>
      </ol>
  </div>
</template>

<script>
export default {
  name: 'SearchView',

  data () {
    return {
      searchString: ''
    }
  },

  computed: {
    getPrograms () {
      return this.$store.getters.getPrograms
    }
  },

  mounted () {
    this.$store.dispatch('fetchPrograms')
    console.log()
  }
}
</script>

<style>

</style>
