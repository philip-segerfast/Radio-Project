<template>
  <div id="app-container">
    <TopBar />
    <router-view />
  </div>
</template>

<script>
import TopBar from './components/TopBar/TopBar.vue'

export default {
  components: {
    TopBar
  }
}

</script>

<!-- GLOBAL STYLE APPLIES TO EVERYTHING -->
<style src="./style.css"></style>

<style scoped>
#app-container {
  display: flex;
  justify-content: center;
  padding: 1% 20%;
  background-color: orangered;
  min-height: 100vh;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin: 0;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
