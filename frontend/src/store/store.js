import { createStore } from 'vuex'

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
    loggedInUser: null,
    clickedProgram: null,
    programs: null
  },

  mutations: {
    setEpisodes (state, payload) {
      state.episodes = payload
    },
    setLoggedInUser (state, user) {
      state.loggedInUser = user
    },
    setClickedProgram (state, payload) {
      state.clickedProgram = payload
    },
    setPrograms (state, payload) {
      state.programs = payload
    }

  },

  actions: {
  },

  modules: {
  }
})
