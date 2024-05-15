import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useBoardStore = defineStore('board', () => {
   // API 연결 전 임시 데이터 
   const boardList = ref([
    {
      id: 1,
      title: "test1111111111111111111111111111111111111111111111111",
      writer: "tester1",
      status: false,
      recruitment_num: 2,
      gender: 1,
      exercise_type: "PT",
      price: 70000,
      road_address: "서울특별시 강남구 테헤란로123-1",
      created_date: "2024.05.11",
      content: "역삼동에 위치한 SSAFY헬스장에서 그룹PT 같이 하실 분 모집합니다.Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta. "
    },
    {
      id: 2,
      title: "2번테스틈ㄴㅇㄻㄴㅇㄻㅇㄻㅇㄴㄹ",
      writer: "22222",
      status: false,
      recruitment_num: 3,
      gender: 2,
      exercise_type: "필라테스",
      price: 40000,
      road_address: "서울특별시 금천구",
      created_date: "2024.05.12",
      content: "가산디지털단지에서 필라테스 그룹 수업 2인 같이해요. Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta. "
    },
    {
      id: 3,
      title: "3번 태스형!",
      writer: "태스형",
      status: false,
      recruitment_num: 4,
      gender: 1,
      exercise_type: "기타",
      price: 20000,
      road_address: "경기도 부천시 원미구",
      created_date: "2024.05.13",
      content: "부천에서 배드민턴 같이해요!~~ Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta.Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta."
    },
    {
      id: 4,
      title: "꽁꽁 얼어붙은 한강위로 고양이가 걸어다닙니다. ",
      writer: "고양이",
      status: true,
      recruitment_num: 2,
      gender: 3,
      exercise_type: "기타",
      price: 30000,
      road_address: "경기도 고양시",
      created_date: "2024.05.11",
      content: "고양이 최고 Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta."
    },
    {
      id: 5,
      title: "버려도 되는 더미 데이터 ㅇ오오오오옹오 ",
      writer: "오오옹",
      status: true,
      recruitment_num: 2,
      gender: 3,
      exercise_type: "기타",
      price: 30000,
      road_address: "경기도 경기경기",
      created_date: "2024.05.16",
      content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta."
    },
    {
      id: 6,
      title: "삭제 테스트ㅜ용 데이턽ㅌㄹㄾㄹ ",
      writer: "와아앙ㅇ",
      status: true,
      recruitment_num: 2,
      gender: 3,
      exercise_type: "기타",
      price: 30000,
      road_address: "부산광역시 수영구",
      created_date: "2024.05.16",
      content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta."
    },
    {
      id: 7,
      title: "delete용 데이터 ",
      writer: "호에에에에",
      status: true,
      recruitment_num: 2,
      gender: 3,
      exercise_type: "기타",
      price: 30000,
      road_address: "부산광역시 수영구",
      created_date: "2024.05.16",
      content: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Est distinctio autem sequi laboriosam. Ea vero voluptatum exercitationem repellat dolores tempora quisquam ab excepturi impedit non cupiditate, nihil odit nam dicta."
    },
  ])

  return { boardList }
})
