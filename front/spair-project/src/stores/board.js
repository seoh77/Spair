import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_BOARD_API = 'http://localhost:8080/api/board'
export const useBoardStore = defineStore('board', () => {
  
  // 게시판리스트 조회 관련 axios
  const boardList = ref([])
  const getBoardList = function(){
    axios.get(REST_BOARD_API)
    .then((response) => {
      boardList.value = response.data
    })
  }
  
  const comments = ref([
    {
      post_id: 1,
      comment_id: 1,
      writer: "자바짱",
      content: "안녕하세요. 저는 자바짱입니다. Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
      created_date: "2024.05.16",
      isEditing: false
    },
    {
      post_id: 1,
      comment_id: 2,
      writer: "양쌤",
      content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
      created_date: "2024.05.16",
      isEditing: false
    },
    {
      post_id: 1,
      comment_id: 3,
      writer: "에구구",
      content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
      created_date: "2024.05.15",
      isEditing: false
    },
    {
      post_id: 2,
      comment_id: 4,
      writer: "안녕",
      content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
      created_date: "2024.05.15",
      isEditing: false
    },
    {
      post_id: 2,
      comment_id: 5,
      writer: "핼로",
      content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
      created_date: "2024.05.15",
      isEditing: false
    },
    {
      post_id: 3,
      comment_id: 6,
      writer: "TESTER",
      content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
      created_date: "2024.05.12",
      isEditing: false
    },
    {
      post_id: 4,
      comment_id: 7,
      writer: "관리자",
      content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
      created_date: "2024.05.15",
      isEditing: false
    },
    {
      post_id: 5,
      comment_id: 8,
      writer: "멍멍이",
      content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
      created_date: "2024.05.15",
      isEditing: false
    },
    {
      post_id: 6,
      comment_id: 9,
      writer: "너무 많다",
      content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
      created_date: "2024.05.17",
      isEditing: false
    },
    {
      post_id: 7,
      comment_id: 10,
      writer: "진짜 많다",
      content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.",
      created_date: "2024.05.13",
      isEditing: false
    },
  ])

  return { boardList, getBoardList, comments }
})
