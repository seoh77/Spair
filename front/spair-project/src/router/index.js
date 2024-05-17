import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BoardView from '@/views/BoardView.vue'
import JoinView from '@/views/JoinView.vue'
import LoginView from '@/views/LoginView.vue'
import MapView from '@/views/MapView.vue'

// board
import BoardList from '@/components/board/BoardList.vue'
import BoardCreate from '@/components/board/BoardCreate.vue'
import BoardDetail from '@/components/board/BoardDetail.vue'
import BoardUpdate from '@/components/board/BoardUpdate.vue'
import BoardSearchKeyword from '@/components/board/BoardSearchKeyword.vue'

// comment
import CommentCreate from '@/components/comment/CommentCreate.vue'
import CommentList from '@/components/comment/CommentList.vue'


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
        {
          path: 'search', 
          name: 'boardSearchKeyword',
          component: BoardSearchKeyword
        },
        {
          path: 'update/:postId', 
          name: 'boardUpdate',
          component: BoardUpdate
        }, 
        {
          // Pathvariable처럼 동적 라우팅 :id
          path: ':postId', 
          name: 'boardDetail',
          component: BoardDetail,
          children: [
            {
              path: '', 
              name: 'commentList',
              component: CommentList
            },
            { path: 'create',
              name: 'commentCreate',
              component: CommentCreate
            },
          ]
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
