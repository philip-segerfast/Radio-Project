import { createStore } from 'vuex'

export default createStore({
  state: {
    loggedInUser: null,
    clickedProgram: null,
    programs: null,
    selectedCategory: 'unselected'
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
    },
    setSelectedCategory (state, payload) {
      state.selectedCategory = payload
    }
  },

  getters: {
    getSelectedCategory: state => {
      return state.selectedCategory
    }
  },

  actions: {

  },

  modules: {
  }
})
