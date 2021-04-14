<template>
  <div>
    <nav id="topbar-container">
      <ul>
        <li class="topbar-item-1">
          <router-link to="/" tag="button" class="homebtn">Sveriges Radio </router-link>
          <router-link to="/programs/category" tag="button" class="categories">Kategorier </router-link>
          <ChannelCarousel />
        </li>
        <LoginRegister />
      </ul>
    </nav>
    <div>
      <SearchBar />
    </div>
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
ul {
  list-style: none;
  display: flex;
}
ul li a {
  padding: 1.6 rem;
}
#topbar-container {
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
.categories {
  background-color: #4CAF50;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}
</style>
