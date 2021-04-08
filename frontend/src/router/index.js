import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import ChannelPrograms from '../views/ChannelPrograms.vue'
import Program from '../views/Program.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/programs/:channelId',
    name: 'ChannelPrograms',
    component: ChannelPrograms
  },
  {
    path: '/program/:programId',
    name: 'Program',
    component: Program
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkExactActiveClass: 'is-active'
})

export default router
