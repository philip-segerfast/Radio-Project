<template>
  <div>
        <p>Selected category: {{selectedCategory.name}}</p>
  <div>
      <select v-model="selectedCategory" @change="fetchPrograms($event)" name="categories" id="categories">
          <option v-for="(category, i) in categories" :key="i" :value="category">
              {{ category.name }}
          </option>
      </select>
  </div>
      <ul>
          <li v-for="(program, i) in programs" :key="i">
            <b id="program-on-categories" v-on:click="this.$router.push('/program/' + program.id)">  {{ program.name }} </b>
              </li>
      </ul>
  </div>
</template>

<script>
export default {

  data () {
    return {
      programs: [],
      categories: [],
      selectedCategory: ''
    }
  },

  async mounted () {
    const response = await fetch('/rest/categories')
    const categories = await response.json()
    this.categories = categories
  },

  methods: {
    async fetchPrograms (event) {
      const id = this.selectedCategory.id
      if (id) {
        const response = await fetch('/rest/programs/programcategory/' + id)
        const programs = await response.json()
        this.programs = programs
      }
      return null
    }
  }
}
</script>

<style>
#program-on-categories {
  cursor: pointer;
  text-decoration: underline;
}
</style>
