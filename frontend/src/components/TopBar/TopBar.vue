<template>
<nav id="topbar-container">
  <ul>
<li class="topbar-item-1">
  <router-link to="/" tag="button" class="homebtn">Sveriges Radio </router-link>
  </li>
  <li class="topbar-item-2">
    <ChannelCarousel />
  </li>
  </ul>
  <div class="topbar-item-3">
    <LoginRegister />
  </div>
</nav>
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
ul {
  list-style: none;
  display: flex;
}
ul li a {
  padding: 1.6 rem;
}
#topbar-container {
  background:brown;
  display: flex;
  height: 100px;
  }
.topbar-item-1 {
 justify-self: auto;
}
.container {
  display: grid;
  grid-template-columns: 30fr 20fr;
}
.homebtn {
  background-color: #4CAF50;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}
</style>
