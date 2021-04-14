<template>
  <router-link to="/login" v-if="!isLoggedIn" class="top-button">Sign in</router-link>
  <router-link to="/" @click="logout" v-else class="top-button">Sign out (logged in as {{ loggedInUser.username }})</router-link>
</template>

<script>
export default {
  data () {
    return {
      isUserLoggedIn: false
    }
  },
  name: 'TopBar',
  methods: {
    logout () {
      // tell backend to forget us
      console.log('logout work')
      fetch('/logout', { mode: 'no-cors' })

      // remove logged in user from store
      this.$store.commit('setLoggedInUser', null)

      alert('You signed out')
    }
  },
  computed: {
    isLoggedIn () {
      return this.$store.state.loggedInUser != null
    },
    loggedInUser () {
      return this.$store.state.loggedInUser
    }
  }
}
</script>
