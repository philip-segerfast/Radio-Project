<template>
  <div class="radio-page-container">
    <div class="radio-topper-container">
      <ChannelInfo :channel="channel" />
    </div>
    <div class="radio-items-container">
      <ProgramList :programs="programs" />
    </div>
  </div>
</template>

<script>
import ChannelInfo from '../components/ChannelInfo.vue'
import ProgramList from '../components/ProgramList/ProgramList.vue'

export default {
  data () {
    return {
      channel: '',
      programs: ''
    }
  },
  watch: {
    $route () {
      this.fetchChannel()
      this.fetchPrograms()
    }
  },
  components: {
    ChannelInfo,
    ProgramList
  },
  async mounted () {
    await this.fetchAll()
  },
  methods: {
    async fetchChannel () {
      const channelId = this.$route.params.channelId
      const channelResponse = await fetch('/rest/channels/' + channelId)
      this.channel = await channelResponse.json()
    },
    async fetchPrograms () {
      const channelId = this.$route.params.channelId
      const programsResponse = await fetch(`/rest/programs/channel/${channelId}`)
      this.programs = await programsResponse.json()
    },
    async fetchAll () {
      await this.fetchChannel()
      await this.fetchPrograms()
    }
  }
}
</script>

<style scoped>
</style>
