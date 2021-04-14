<template>
  <div id="channels-container">
    <div id="channel-info">
      <ChannelInfo :channel="channel" />
      <form @submit.prevent>
      <input v-model="goToTableau" @change="fetchPrograms($event)" name="tableauByDay" id="categories" type="text" placeholder="2020-01-01"/>
      </form>
    </div>
    <div id="tableau-list">
    <ul>
      <li class="selectable" v-for="(program, i) in programs" :key="i"> <b> {{ program.title }} </b>
        <br> {{ program.description }}
        <br> {{ program.starttimeutc }}
        </li>
    </ul>
    </div>
  </div>
</template>

<script>
import ChannelInfo from '../components/ChannelInfo.vue'

export default {
  data () {
    return {
      channel: '',
      programs: [],
      goToTableau: ''
    }
  },
  components: {
    ChannelInfo
  },

  async mounted () {
    const channelId = this.$route.params.channelId
    const response = await fetch('/rest/channels/' + channelId)
    this.channel = await response.json()

    try {
      const response = await fetch('/rest/programs/tableau/' + channelId)
      const programs = await response.json()
      this.programs = programs
      console.log(programs)
    } catch {
      alert('Kanalen finns inte. Prova med ett annat ID.')
    }
  },

  methods: {
    async fetchPrograms (event) {
      const channelId = this.$route.params.channelId
      if (channelId) {
        const response = await fetch('/rest/programs/tableau/' + channelId + '/' + this.goToTableau)
        const programs = await response.json()
        this.programs = programs
      }
      return null
    }
  }
}
</script>

<style scoped>
#channels-container {
  display: flex;
  width: 100%;
  flex-direction: column;
  align-items: center;
  background-color: tomato;
}

#channel-info {
  width: 100%;
  height: min-content;
  margin-bottom: var(--medium-padding);
}

#program-list {
  height: 100%;
  width: 100%;
}
</style>
