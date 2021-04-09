import { createStore } from 'vuex'

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
  },

  modules: {
  }
})
