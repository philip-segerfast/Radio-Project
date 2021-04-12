<template>
  <div>
      <select v-model="selectedCategory" name="categories" id="categories">
          <option v-for="(category, categoryIndex) in getCategories" :key="categoryIndex" :value="category.id">
              {{  }}
          </option>
      </select>
  </div>
</template>

<script>
export default {
  name: 'CategoryList',

  computed: {
    getCategories () {
      return this.$store.getters.getUsers
    }
  },

  mounted () {
    this.$store.dispatch('fetchCategories')
  }
}
</script>

<style>

</style>
