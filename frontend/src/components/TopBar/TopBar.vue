<template>
  <div>
    <SearchBar />
  </div>
  <nav>
    <router-link to="/login" tag="button">Sign in</router-link>
  </nav>
</template>

<script>
import SearchBar from '../TopBar/SearchBar.vue'
export default {
  name: 'TopBar',

  components: {
    SearchBar
  }
}
</script>

<style scoped>

</style>
