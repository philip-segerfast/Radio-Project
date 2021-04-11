import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Channel from '../views/Channel.vue'
import Program from '../views/Program.vue'
import SearchView from '../views/SearchView'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/programs/:channelId',
    name: 'Channel',
    component: Channel
  },
  {
    path: '/program/:programId',
    name: 'Program',
    component: Program
  },
  {
    path: '/search',
    name: 'SearchView',
    component: SearchView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkExactActiveClass: 'is-active'
})

export default router
