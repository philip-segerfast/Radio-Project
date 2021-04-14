<template>
  <div>
    <router-link to="/login" class="button-login">Sign in</router-link>
    <router-link to="/" class="button-logout">Sign out</router-link>
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

<style scoped>
.button-login {
  background-color: #4CAF50;
  color: white;
  padding: 16px;
  display: flex;
}
.button-logout {
  background-color: #4CAF50;
  color: white;
  padding: 16px;
  display: flex;
}

</style>
