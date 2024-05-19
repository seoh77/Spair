import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = 'http://localhost:8080/api/board'
const REST_COMMENT_API = 'http://localhost:8080/api/comment'
// const router = useRouter()
export const useBoardStore = defineStore('board', () => {
  

  // 전체(전국) 게시물 리스트 조회 관련 axios
  const boardListTotal = ref([])
  const getBoardListTotal = function(){
    axios.get(REST_BOARD_API)
    .then((response) => {
      console.log(response.data)
      boardListTotal.value = response.data
    })
  }


  // 우리동네 게시물 리스트 조회 관련 axios
  const boardList = ref([])
  const getBoardList = function(){
    // axios.get(REST_BOARD_API)
    const userInfoStr = localStorage.getItem('loginUserInfo')
    // console.log(userInfoStr)
    if(userInfoStr){

      const userIdInfo = JSON.parse(userInfoStr)
      // console.log(userIdInfo)
      const userId = userIdInfo.userId
      // console.log(userId)
      axios.get(`http://localhost:8080/api/board/town?userId=${userId}`)
      .then((response) => {
        boardList.value = response.data
      })
    }
  }

  // 게시물 상세조회 관련 axios
  const board = ref({})
  const user = ref({})
  const sportsCenter = ref({})

  const getBoard = function(postId){
    axios.get(`http://localhost:8080/api/board/${postId}`)
    .then((response) => {
        board.value = response.data
        user.value = board.value.user
        sportsCenter.value = board.value.sportsCenter
    })
  }

  // 게시물 수정 관련 axios
  const updateBoard = function(postId){
    axios.put(`http://localhost:8080/api/board/${postId}`, {
      "postId": board.value.postId,
      "title": board.value.title,
      "content": board.value.content,
      "status": board.value.status,
      "price" : board.value.price,
      "gender": board.value.gender,
      "recruitmentNum": board.value.recruitmentNum
    })
    .then((response) => {
      console.log(response)
      router.push({name: 'boardList'})
    })
  }


  // 게시물 등록 관련 axios 
  const createBoard = function(post){
    console.log(post)
    console.log(post.title)
    console.log(post.userId)
    axios({
      url: REST_BOARD_API,
      method: 'POST',
      data: {
        "post": {
          "userId": 1,
          "title": post.title,
          "content": post.content,
          "status": post.status,
          "exerciseType": post.exerciseType,
          "price": post.price,
          "gender": post.gender,
          "recruitmentNum": post.recruitmentNum,
        },
        "sportsCenter": {
          "roadAddress": "경기 부천시 원미구 석천로183번길 39 라인제작소",
          "localAddress": "경기 부천시 중동 1166-3 라인제작소",
          "latitude": 37.502384,
          "longitude": 126.770496
        }
      }
    })
    .then(() => {
        router.push({ name: 'boardList'})
    })
  }


  

  return { board, user, sportsCenter, getBoard, boardList, getBoardList, updateBoard, createBoard, boardListTotal, getBoardListTotal }
})
