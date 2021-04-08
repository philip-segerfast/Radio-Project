import { createStore } from 'vuex'
import axios from 'axios'

export default createStore({
  state: {
    episodes: String,
    clickedProgram: null
  },
  mutations: {
    setEpisodes (state, payload) {
      state.episodes = payload
    },
    setClickedProgram (state, payload) {
      state.clickedProgram = payload
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
