<template>
  <div class="dropdown">
    <div class="top-button">Kanaler</div>
    <div class="dropdown-content">
      <div class="channel-item" v-for="channel in channels" :key="channel.id" v-on:click="goToChannel(channel)">{{ channel.name }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Channel',

  data () {
    return {
      channels: ''
    }
  },
  async mounted () {
    const channelId = this.$route.params.channelId
    const channelsResponse = await fetch('/rest/channels')
    this.channels = await channelsResponse.json()
  },
  methods: {
    goToChannel (channel) {
      this.$router.push('/channels/' + channel.id)
      this.$forceUpdate()
    }
  }
}
</script>

<style>
.channel-item:hover {
  background-color: gainsboro;
  cursor: pointer;
}

/* Style The Dropdown Button */
.dropbtn {
  background-color: #4CAF50;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
  position: relative;
  display: inline-block;
  cursor: pointer;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  max-height: 50vh;
  overflow: auto;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #f1f1f1}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
  display: block;
}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {
  background-color: #3e8e41;
}
</style>
