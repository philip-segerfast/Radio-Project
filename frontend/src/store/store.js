import { createStore } from 'vuex'

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
