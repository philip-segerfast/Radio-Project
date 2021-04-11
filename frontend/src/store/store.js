import axios from 'axios'
import { createStore } from 'vuex'

export default createStore({
  state: {
    searchString: '',
    programs: []
  },

  mutations: {
    setprograms (state, payload) {
      state.programs = payload
    }
  },

  actions: {
    async fetchPrograms () {
      await axios.get('/rest/programs/search/' + this.searchString)
        .then(response => (this.programs = response.data))
        .catch(error => console.log(error))
    }
  },

  getters: {
    getPrograms (state) {
      return state.programs
    }
  },

  modules: {
  }
})
