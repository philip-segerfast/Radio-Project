import { createStore } from 'vuex'
import axios from 'axios'

export default createStore({
  state: {
    episodes: String,
    clickedProgram: null,
    programs: null
  },
  mutations: {
    setEpisodes (state, payload) {
      state.episodes = payload
    },
    setClickedProgram (state, payload) {
      state.clickedProgram = payload
    },
    setPrograms (state, payload) {
      state.programs = payload
    }
  },
  actions: {
    async fetchEpisodes () {
      await axios.get('http://localhost:5000/rest/episodes/3718')
        .then(response => {
          this.commit('setEpisodes', response.data)
        })
    }
  },
  modules: {
  }
})
