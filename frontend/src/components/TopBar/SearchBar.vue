<template>
  <form id="search-container" @submit.prevent="search">
    <input type="search" id="search-box" name="q" placeholder="Sök efter program..." v-model="searchString"/>
    <div id="search-button" v-on:click="search"><i class="fa fa-search"></i></div>
  </form>
</template>

<script>
export default {
  name: 'SearchBar',

  data () {
    return {
      searchString: ''
    }
  },
  methods: {
    search () {
      this.$router.push('/search/' + this.searchString)
    }
  }

}
</script>

<style scoped>

#search-container {
  margin-left: auto;
  height: 30px;
  width: fit-content;
  display: flex;
}

#search-box {
  display: inline-block;
  height: 100%;
  border: 1px solid black;
  padding-left: 5px;
  background-color: var(--background-brightest);
}

#search-button {
  display: flex;
  align-items: center;
  padding: 0 5px;
  height: 100%;
  background-color: var(--background-brightest);
  border: 1px solid black;
  transition: background-color 0.2s;
}

#search-button:hover {
  cursor: pointer;
  background-color: var(--background-brightest-hover);
}

#search-button:active {
  cursor: pointer;
  background-color: var(--background-brightest-active);
}

</style>
