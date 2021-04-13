<template>
  <div id="channels-container">
    <div id="channel-info">
      <ChannelInfo :channel="channel" />
    </div>
    <div id="tableau-list">
        <TableauList />
    </div>
  </div>
</template>

<script>
import ChannelInfo from '../components/ChannelInfo.vue'
import TableauList from '../components/TableauList.vue'

export default {
  data () {
    return {
      channel: ''
    }
  },
  components: {
    ChannelInfo,
    TableauList
  },
  async mounted () {
    const channelId = this.$route.params.channelId
    const response = await fetch('/rest/channels/' + channelId)
    this.channel = await response.json()
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
