<template>
  <div id="episode-list-container" class="selectable">Episode list
    <ul>
      <li class="episode-item" v-for="episode in episodes" :key="episode.episodeId">
        <div class="info">
          <b>  {{ episode.name }} </b>
          <br> {{ episode.description }}
          <br> {{ episode.publishdateutc }}
          <br> {{ episode.url }}
        </div>
        <div class="favourite tmp-icon" v-if="episode.isFavourite" v-on:click='unFavouriteEpisode(episode.episodeId)'>
          Unfavourite
        </div>
        <div class="tmp-icon" v-else v-on:click='favouriteEpisode(episode.episodeId)'>
          Favourite
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: ['program'],
  name: 'EpisodeList',

  data () {
    return {
      episodes: []
    }
  },

  async mounted () {
    const programId = this.$route.params.programId
    try {
      const response = await fetch('/rest/episodes/' + programId)
      const episodes = await response.json()
      this.episodes = episodes
    } catch {
      alert('Ogiltigt program. Prova med ett annat ID.')
    }
    for (let i = 0; i < this.episodes.length; i++) {
      let favouriteInfo
      try {
        const favouriteInfoResponse = await fetch('/rest/is-episode-favourite/' + this.episodes[i].episodeId)
        favouriteInfo = await favouriteInfoResponse.json()
      } catch (e) {
        console.log(e.message)
      }
      console.log('favourite info:')
      console.log(favouriteInfo)
      if (!favouriteInfo.error) {
        const isFavourite = favouriteInfo.isFavourite
        this.episodes[i].isFavourite = isFavourite
        console.log(this.episodes[i])
      } else {
        alert('Error: ' + favouriteInfo.error)
      }
    }
  },

  methods: {
    async favouriteEpisode (episodeId) {
      const addEpisodeFavouriteUrl = '/rest/user/add-episode-favourite/' + episodeId
      const response = await fetch(addEpisodeFavouriteUrl, {
        method: 'PUT'
      })
      const successJson = await response.json()
      if (successJson.success === true) {
        for (let i = 0; i < this.episodes.length; i++) {
          if (this.episodes[i].episodeId === episodeId) {
            this.episodes[i].isFavourite = true
          }
        }
      } else {
        alert('Error: ' + successJson.error)
      }
    },
    async unFavouriteEpisode (episodeId) {
      const removeEpisodeFavouriteUrl = '/rest/user/remove-episode-favourite/' + episodeId
      const response = await fetch(removeEpisodeFavouriteUrl, {
        method: 'PUT'
      })
      const successJson = await response.json()
      if (successJson.success === true) {
        for (let i = 0; i < this.episodes.length; i++) {
          if (this.episodes[i].episodeId === episodeId) {
            this.episodes[i].isFavourite = false
          }
        }
      } else {
        alert('Error: ' + successJson.error)
      }
    },
    getEpisodeById (id) {
      const episodes = this.episodes
      for (let i = 0; i < episodes.length; i++) {
        const episode = episodes[i]
        if (episode.episodeId === id) {
          return episode
        }
      }
    }
  }
}
</script>

<style scoped>
#episode-list-container {
  border-radius: inherit;
  height: 100%;
  background-color: var(--background-darker);
  border: 1px solid black;
  padding: var(--medium-padding);
}
</style>
