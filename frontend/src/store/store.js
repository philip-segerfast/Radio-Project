import { createStore } from 'vuex'
import axios from 'axios'

const actions = {
  async actionWithValue (store, data) {
    console.log(data)

    // await fetch data from backend
    // store.commit('setData', data)
  }
}

export default createStore({
  state: {
    episodes: String,
    loggedInUser: null
  },
  mutations: {
    setEpisodes (state, payload) {
      state.episodes = payload
    },
    setLoggedInUser (state, user) {
      state.loggedInUser = user
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
