import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = 'http://localhost:8080/api/board'
const REST_COMMENT_API = 'http://localhost:8080/api/comment'
// const router = useRouter()
export const useBoardStore = defineStore('board', () => {
  

  // 게시판리스트 조회 관련 axios
  const boardList = ref([])
  const getBoardList = function(){
    axios.get(REST_BOARD_API)
    .then((response) => {
      boardList.value = response.data
    })
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
    // console.log(board.value)
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
    // axios.post(REST_BOARD_API, post)
    // .then(() => {
    //   router.push({ name: 'boardList'})
    // })
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


  // 특정 게시글 댓글 조회 관련 axios
  // const commentList = ref([])
  // const getCommentList = function(){

  //   axios.get(`http://localhost:8080/api/comment/${route.params.postId}`)
  //   .then(response => {
  //     commentList.value = response.data
  //     console.log(response.data)
  //   })
  //   .catch(error => {
  //     console.error(error)
  //   })
  // }
  // const commentList = ref([])
  // const getCommentList = function(postId){
  //   console.log("이거 안되고 있나?")
  //   console.log(postId)
  //   axios.get(`http://localhost:8080/api/comment/${postId}`)
  //   .then((response) => {
  //     console.log(response)
  //     commentList.value = response.data
  //   })
  // }



  // const comments = ref([
  //   {
  //     post_id: 1,
  //     comment_id: 1,
  //     writer: "자바짱",
  //     content: "안녕하세요. 저는 자바짱입니다. Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
  //     created_date: "2024.05.16",
  //     isEditing: false
  //   },
  //   {
  //     post_id: 1,
  //     comment_id: 2,
  //     writer: "양쌤",
  //     content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
  //     created_date: "2024.05.16",
  //     isEditing: false
  //   },
  //   {
  //     post_id: 1,
  //     comment_id: 3,
  //     writer: "에구구",
  //     content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
  //     created_date: "2024.05.15",
  //     isEditing: false
  //   },
  //   {
  //     post_id: 2,
  //     comment_id: 4,
  //     writer: "안녕",
  //     content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
  //     created_date: "2024.05.15",
  //     isEditing: false
  //   },
  //   {
  //     post_id: 2,
  //     comment_id: 5,
  //     writer: "핼로",
  //     content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
  //     created_date: "2024.05.15",
  //     isEditing: false
  //   },
  //   {
  //     post_id: 3,
  //     comment_id: 6,
  //     writer: "TESTER",
  //     content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
  //     created_date: "2024.05.12",
  //     isEditing: false
  //   },
  //   {
  //     post_id: 4,
  //     comment_id: 7,
  //     writer: "관리자",
  //     content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
  //     created_date: "2024.05.15",
  //     isEditing: false
  //   },
  //   {
  //     post_id: 5,
  //     comment_id: 8,
  //     writer: "멍멍이",
  //     content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
  //     created_date: "2024.05.15",
  //     isEditing: false
  //   },
  //   {
  //     post_id: 6,
  //     comment_id: 9,
  //     writer: "너무 많다",
  //     content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
  //     created_date: "2024.05.17",
  //     isEditing: false
  //   },
  //   {
  //     post_id: 7,
  //     comment_id: 10,
  //     writer: "진짜 많다",
  //     content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
  //     created_date: "2024.05.13",
  //     isEditing: false
  //   },
  // ])

  return { board, user, sportsCenter, getBoard, boardList, getBoardList, updateBoard, createBoard,  }
})
