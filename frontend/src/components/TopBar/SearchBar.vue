<template>
  <div>
    <form>
      <input type="search" placeholder="Sök på något..." v-model="searchString"/>
      <button v-on:click="this.$router.push('/search/' + searchString)"> Sök </button>
      </form>
  </div>
</template>

<script>
export default {
  name: 'SearchBar',

  data () {
    return {
      searchString: ''
    }
  }

}
</script>

<style>

</style>
