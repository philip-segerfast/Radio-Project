import { createStore } from 'vuex'
import axios from 'axios'

export default createStore({
  state: {
    episodes: String
  },
  mutations: {
    setEpisodes (state, payload) {
      state.episodes = payload
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
