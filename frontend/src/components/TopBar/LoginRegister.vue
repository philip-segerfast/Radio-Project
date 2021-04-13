<template>
  <div>
    <router-link to="/login" v-if="loggedInUser==null" tag="button">Sign in</router-link>
    <router-link to="/" @click="logout" v-if="loggedInUser==user" tag="button">Sign out</router-link>
  </div>
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
    }
  }
}
</script>

<style>

</style>
