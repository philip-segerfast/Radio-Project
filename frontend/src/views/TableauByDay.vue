<template>
  <div id="channels-container">
    <div id="channel-info">
      <ChannelInfo :channel="channel" />
    </div>
    <div id="tableau-list">
        <TableauBydayList />
    </div>
  </div>
</template>

<script>
import ChannelInfo from '../components/ChannelInfo.vue'
import TableauBydayList from '../components/TableauByDayList.vue'

export default {
  data () {
    return {
      channel: ''
    }
  },
  components: {
    ChannelInfo,
    TableauBydayList
  },
  async mounted () {
    const channelId = this.$route.params.channelId
    const date = this.$route.params.date

    const response = await fetch('/rest/channels/' + channelId + '/' + date)
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
