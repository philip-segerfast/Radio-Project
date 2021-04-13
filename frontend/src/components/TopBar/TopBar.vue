<template>
<div>
  <router-link to="/" tag="button" class="homebtn">Sveriges Radio </router-link>
  </div>
  <ul>
    <ChannelCarousel />
  </ul>
  <div>
    <LoginRegister />
  </div>
</template>

<script>
import ChannelCarousel from './ChannelCarousel.vue'
import LoginRegister from './LoginRegister.vue'
export default {
  data () {
    return {
      isUserLoggedIn: false
    }
  },
  name: 'TopBar',
  components: {
    ChannelCarousel,
    LoginRegister
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
.homebtn {
  background-color: #4CAF50;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}
</style>
