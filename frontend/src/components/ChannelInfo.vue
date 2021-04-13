<template>
  <div class="info-header-container">
    <div class="header grid-item"> {{ channel.name }} </div>
    <div class="left-box grid-item">
      <div class="description selectable">
        {{ channel.tagline }}
      </div>
      <div class="icon-bar">
        <div class="tmp-icon" v-on:click="goToTableau">
          Go to tableau
        </div>
        <div class="tmp-icon">
          Share
        </div>
        <div class="tmp-icon">
          Favourite
        </div>
      </div>
    </div>
    <div class="right-box">
      <img :src="channel.imageUrl">
    </div>
  </div>
</template>

<script>
export default {
  props: ['channel'],
  data () {
    return {
      program: ''
    }
  },
  methods: {
    goToTableau () {
      this.$router.push('/tableau/' + this.channel.id)
    }
  }
}
</script>
