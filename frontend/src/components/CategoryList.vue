<template>
  <div>
      <select v-model="selectedCategory" name="categories" id="categories">
          <option v-for="(category, i) in categories" :key="i">
              {{ category.name }}
          </option>
      </select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      categories: []
    }
  },

  async mounted () {
    const response = await fetch('/rest/categories')
    const categories = await response.json()
    this.categories = categories
    console.log(categories)
  }
}
</script>

<style>

</style>
