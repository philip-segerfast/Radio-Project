<template>
  <div>
    Here you can see all programs for a certain channel.
  </div>
</template>

<script>
export default {
  data () {
    return {
      id: ''
    }
  },
  mounted () {
    this.id = this.$route.params.channelID
  }
}
</script>

<style scoped>
div {
  width: 100%;
  height: 100%;
  background-color: black;
}
</style>
