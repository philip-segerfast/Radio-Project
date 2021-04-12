import { createStore } from 'vuex'
import axios from 'axios'

export default createStore({
  state: {
    episodes: String,
    loggedInUser: null,
    clickedProgram: null,
    programs: null,
    categories: []
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
    setCategories (state, payload) {
      state.categories = payload
    }

  },

  actions: {
    async fetchUsers () {
      await axios.get('rest/categories')
        .then(response => {
          this.commit('setUsers', response.data)
        })
    }
  },

  getters: {
    getCategories (state) {
      return state.categories
    }
  },

  modules: {
  }
})
