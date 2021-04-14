import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Channel from '../views/Channel.vue'
import Program from '../views/Program.vue'
import SearchView from '../views/SearchView'
import Login from '../views/Login.vue'
import ProgramCategories from '../views/ProgramCategories.vue'
import Tableau from '../views/Tableau'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/channels/:channelId',
    name: 'Channel',
    component: Channel
  },
  {
    path: '/program/:programId',
    name: 'Program',
    component: Program
  },
  {
    path: '/search/:searchString',
    name: 'SearchView',
    component: SearchView
  },
  {
    path: '/programs/category',
    name: 'ProgramCategories',
    component: ProgramCategories
  },
  {
    path: '/tableau/:channelId',
    name: 'Tableau',
    component: Tableau
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkExactActiveClass: 'is-active'
})

export default router
