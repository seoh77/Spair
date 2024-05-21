import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BoardView from '@/views/BoardView.vue'
import JoinView from '@/views/JoinView.vue'
import LoginView from '@/views/LoginView.vue'
import MapView from '@/views/MapView.vue'
import CenterBoard from '@/views/CenterBoardView.vue'

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
      component: JoinView,
      beforeEnter(to, from, next){
        const isAuthenticated = !!localStorage.getItem('loginUserInfo')
        if (to.name !== 'join' && !isAuthenticated) {
          next({ name: 'join' }); // 회원가입 페이지로 이동
        } else if (to.name === 'join' && isAuthenticated) {
            alert("유효하지 않은 요청입니다.")
            next({ name: 'home' }); // 이미 로그인한 사용자는 홈 페이지로 이동
        } else {
            next(); // 로그인이 필요하지 않은 페이지로 이동
        }
      }
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
      beforeEnter(to, from, next){
        const isAuthenticated = !!localStorage.getItem('loginUserInfo')
        if (to.name !== 'login' && !isAuthenticated) {
          next({ name: 'login' }); // 로그인 페이지로 이동
        } else if (to.name === 'login' && isAuthenticated) {
            alert("유효하지 않은 요청입니다.")
            next({ name: 'home' }); // 이미 로그인한 사용자는 홈 페이지로 이동
        } else {
            next(); // 로그인이 필요하지 않은 페이지로 이동
        }
      }
    },
    {
      path: '/map',
      name: 'map',
      component: MapView
    },
    {
      path:'/center/:centerId',
      name: 'centerboard',
      component: CenterBoard
    }
  ]
})

import { useBoardStore } from '@/stores/board'


router.beforeEach((to, from, next) => {
  const isAuthenticated = !!localStorage.getItem('loginUserInfo');
  if (!isAuthenticated && to.name !== 'login' && to.name !== 'home' && to.name !== 'join') {
    console.log('Navigation Guard:', { to: to.name, from: from.name, isAuthenticated });
    alert('로그인이 필요합니다.');
    next({ name: 'login' });
  } else {
      if (isAuthenticated && to.name === 'boardList') {
        const store = useBoardStore();
        store.getBoardList();
      }
      next();
  }
});

export default router
