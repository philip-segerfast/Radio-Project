<template>
  <div class="radio-page-container">
    <div class="radio-topper-container">
      <ProgramInfo :program="program" @favourite="favourite" @unFavourite="unFavourite" />
    </div>
    <div class="radio-items-container">
          <NextEpisode />
      <EpisodeList :program="program"/>
    </div>
  </div>
</template>

<script>
import ProgramInfo from '../components/ProgramInfo'
import NextEpisode from '../components/NextEpisode.vue'
import EpisodeList from '../components/EpisodeList.vue'

export default {
  data () {
    return {
      program: ''
    }
  },

  async mounted () {
    this.$store.subscribe((setLoggedInUser, state) => {
      if (setLoggedInUser.type === 'CHANGEAPP') {
        this.fetchUsers()
      }
    })

    const programId = this.$route.params.programId
    try {
      const response = await fetch('/rest/programs/' + programId)
      this.program = await response.json()

      if (this.$store.loggedInUser != null) {
        const favouriteInfoResponse = await fetch('/rest/is-program-favourite/' + programId)
        const favouriteInfo = await favouriteInfoResponse.json()
        if (!favouriteInfo.error) {
          const isFavourite = favouriteInfo.isFavourite
          this.program.isFavourite = isFavourite
          console.log(this.program)
        } else {
          alert('Error: ' + favouriteInfo.error)
        }
      }
    } catch (error) {
      console.log('Error: ' + error)
      alert('Ogiltigt program. Prova med ett annat ID.')
      this.program = {
        name: 'Otillgängligt',
        description: 'Otillgängligt'
      }
    }
  },

  methods: {
    async favourite () {
      const addProgramFavouriteUrl = '/rest/user/add-program-favourite/' + this.program.id
      const response = await fetch(addProgramFavouriteUrl, {
        method: 'PUT'
      })
      const successJson = await response.json()
      if (successJson.success === true) {
        this.program.isFavourite = true
      } else {
        alert('Error: ' + successJson.error)
      }
    },
    async unFavourite () {
      const response = await fetch('/rest/user/remove-program-favourite/' + this.program.id, {
        method: 'PUT'
      })
      const success = await response.json()
      this.program.isFavourite = false
    }
  },

  components: {
    ProgramInfo,
    NextEpisode,
    EpisodeList
  }
}
</script>
