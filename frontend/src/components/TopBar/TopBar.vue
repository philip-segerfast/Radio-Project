<template>
  <div id="topbar-container">
    <router-link to="/" class="top-button">Sveriges Radio </router-link>
    <router-link to="/programs/category" class="top-button">Kategorier </router-link>
    <ChannelCarousel />
    <LoginRegister />
    <SearchBar />
  </div>
</template>

<script>
import ChannelCarousel from './ChannelCarousel.vue'
import LoginRegister from './LoginRegister.vue'
import SearchBar from '../TopBar/SearchBar.vue'
export default {
  data () {
    return {
      isUserLoggedIn: false
    }
  },
  name: 'TopBar',
  components: {
    ChannelCarousel,
    LoginRegister,
    SearchBar
  },
  methods: {
    logout () {
      // tell backend to forget us
      console.log('logout work')
      fetch('/logout', { mode: 'no-cors' })

      // remove logged in user from store
      this.$store.commit('setLoggedInUser', null)
    }
  }
}
</script>

<style scoped>

#topbar-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
  padding: 10px;
  border: 1px solid black;
  background-color: var(--background-darker);
  border-radius: var(--medium-border-radius);
  margin-bottom: var(--medium-padding);
}

</style>
