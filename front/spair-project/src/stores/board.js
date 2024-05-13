import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useBoardStore = defineStore('board', () => {
   // API 연결 전 임시 데이터 
   const boardList = ref([
    {
      id: 1,
      title: "test1111111111111111111111111111111111111111111111111",
      writer: "tester1",
      regDate: "2024.05.11",
      status: false,
    },
    {
      id: 2,
      title: "제목이 길 때 ... 으로 표시될 고양",
      writer: "nyang",
      regDate: "2024.05.12",
      status: true,
    },
    {
      id: 3,
      title: "test3343333333333333333333",
      writer: "작성자도 길면 어떻게 되는거지",
      regDate: "2024.05.13",
      status: true,
    }
  ])

  return { boardList }
})
