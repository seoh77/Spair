import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BoardView from '@/views/BoardView.vue'
import JoinView from '@/views/JoinView.vue'
import LoginView from '@/views/LoginView.vue'
import MapView from '@/views/MapView.vue'

import BoardList from '@/components/board/BoardList.vue'
import BoardCreate from '@/components/board/BoardCreate.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/board',
      name: 'board',
      component: BoardView,
      children: [
        {
          path: '', 
          name: 'boardList',
          component: BoardList
        },
        {
          path: 'create', 
          name: 'boardCreate',
          component: BoardCreate
        },
      ]
    },
    {
      path: '/join',
      name: 'join',
      component: JoinView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/map',
      name: 'map',
      component: MapView
    },
    
  ]
})

export default router
