import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Channel from '../views/Channel.vue'
import Program from '../views/Program.vue'
import Login from '../views/Login.vue'
import ProgramCategories from '../views/ProgramCategories.vue'

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
    path: '/programs/category/:categoryId',
    name: 'ProgramCategories',
    component: ProgramCategories
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkExactActiveClass: 'is-active'
})

export default router
