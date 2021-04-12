<template>
  <div>
      <CategoryList />
      <ul>
          <li v-for="(program, i) in programs" :key="i"> {{ program.name }} </li>
      </ul>
  </div>
</template>

<script>
import CategoryList from '../components/CategoryList'

export default {

  data () {
    return {
      programs: []
    }
  },

  async mounted () {
    console.log(this.programs)
    this.categoryId = this.$route.params.categoryId
    try {
      const response = await fetch('/rest/programs/programcategory/' + this.categoryId)
      const programs = await response.json()
      this.programs = programs
      console.log(programs)
    } catch {
      alert('Denna Kategorin finns inte. Prova med ett annat ID.')
    }
  },

  components: {
    CategoryList
  }
}
</script>

<style>

</style>
